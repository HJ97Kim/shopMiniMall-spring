package com.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dto.CartDTO;
import com.dto.GoodsDTO;
import com.dto.MemberDTO;
import com.service.GoodsService;

@Controller
public class GoodsController {
	@Autowired
	GoodsService service;
	
	@RequestMapping("/goodsList")
	public ModelAndView goodsList(@RequestParam("gCategory") String gCategory) {
		if(gCategory == null) {
			gCategory = "top";
		}
		List<GoodsDTO> list = service.goodsList(gCategory);
		ModelAndView mav = new ModelAndView();
		mav.addObject("goodsList", list);
		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping("/goodsRetrieve") //페이지는 goodsRetrieve.jsp
	@ModelAttribute("goodsRetrieve") //goodsRetrieve가 키가 됨
	public GoodsDTO goodsRetrieve(@RequestParam("gCode") String gCode) { //리턴타입 주의
		GoodsDTO dto = service.goodsRetrieve(gCode);
		return dto; //request.setAttribute("goodsRetrieve", dto);
	}
	
	@RequestMapping("/loginCheck/cartAdd")
	public String cartAdd(CartDTO dto, HttpSession session) { //파싱
		MemberDTO mdto = (MemberDTO)session.getAttribute("login"); //세션에서 회원정보얻기
		String userid = mdto.getUserid(); //회원정보에서 id를 꺼내 cartDTO에 넣어주기
		dto.setUserid(userid);
		session.setAttribute("mesg", dto.getgCode());
		service.cartAdd(dto); // db insert
		return "redirect:../goodsRetrieve?gCode=" + dto.getgCode(); //리다이렉션
	}
	
	@RequestMapping("/loginCheck/cartList")
	public String cartList(RedirectAttributes attr, HttpSession session) {
		MemberDTO dto = (MemberDTO)session.getAttribute("login");
		String userid = dto.getUserid();
		List<CartDTO> list = service.cartList(userid);
		attr.addFlashAttribute("cartList", list);
		return "redirect:../cartList"; //servlet-context에 등록
	}
	
}
