package com.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dto.CartDTO;
import com.dto.GoodsDTO;
import com.dto.MemberDTO;
import com.dto.OrderDTO;
import com.service.GoodsService;
import com.service.MemberService;

@Controller
public class GoodsController {
	@Autowired
	GoodsService service;
	@Autowired
	MemberService mService;
	
	@RequestMapping("/loginCheck/orderConfirm")
	public String orderConfirm(@RequestParam("num") int num, HttpSession session, 
			RedirectAttributes attr) {
		MemberDTO mDTO=(MemberDTO)session.getAttribute("login");
		String userid= mDTO.getUserid();
		mDTO= mService.myPage(userid); //사용자 정보 가져오기 
		CartDTO cDTO= service.orderConfirmByNum(num); //장바구니 정보가져오기 
		attr.addFlashAttribute("mDTO", mDTO);  //request에 회원정보저장
		attr.addFlashAttribute("cDTO", cDTO); //request에 카트정보저장	
		return "redirect:../orderConfirm"; //servlet-context에 등록
	}
	
	@RequestMapping(value = "/goodsList")
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
	
	@RequestMapping(value = "/goodsRetrieve") //페이지는 goodsRetrieve.jsp
	@ModelAttribute("goodsRetrieve") //goodsRetrieve가 키가 됨
	public GoodsDTO goodsRetrieve(@RequestParam("gCode") String gCode) { //리턴타입 주의
		GoodsDTO dto = service.goodsRetrieve(gCode);
		return dto; //request.setAttribute("goodsRetrieve", dto);
	}
	
	@RequestMapping(value = "/loginCheck/cartAdd")
	public String cartAdd(CartDTO dto, HttpSession session) { //파싱
		MemberDTO mdto = (MemberDTO)session.getAttribute("login"); //세션에서 회원정보얻기
		String userid = mdto.getUserid(); //회원정보에서 id를 꺼내 cartDTO에 넣어주기
		dto.setUserid(userid);
		session.setAttribute("mesg", dto.getgCode());
		service.cartAdd(dto); // db insert
		return "redirect:../goodsRetrieve?gCode=" + dto.getgCode(); //리다이렉션
	}
	
	@RequestMapping(value = "/loginCheck/cartList")
	public String cartList(RedirectAttributes attr, HttpSession session) {
		MemberDTO dto = (MemberDTO)session.getAttribute("login");
		String userid = dto.getUserid();
		List<CartDTO> list = service.cartList(userid);
		attr.addFlashAttribute("cartList", list);
		return "redirect:../cartList"; //servlet-context에 등록
	}
	
	@RequestMapping(value = "/loginCheck/cartUpdate")
	@ResponseBody
	public void cartUpdate(@RequestParam Map<String, String> map) {
		System.out.println(map);
		service.cartUpdate(map);
	}
	
	@RequestMapping(value = "/loginCheck/cartDel")
	@ResponseBody
	public void cartDel(@RequestParam("num") int num) {
		service.cartDel(num);
	}
	
	@RequestMapping(value = "/loginCheck/delAllCart")
	public String delAllCart(@RequestParam("check") ArrayList<String> list) {
		System.out.println(list);
		service.delAllCart(list);
		return "redirect:../loginCheck/cartList";
	}
	
	@RequestMapping("/loginCheck/orderDone")
	public String orderDone(OrderDTO oDTO, int orderNum, HttpSession session, RedirectAttributes xxx) {
		MemberDTO dto = (MemberDTO)session.getAttribute("login");
		oDTO.setUserid(dto.getUserid());
		service.orderDone(oDTO, orderNum);
		xxx.addFlashAttribute("oDTO", oDTO);
		return "redirect:../orderDone";
	}
	
}
