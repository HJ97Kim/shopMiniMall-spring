package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dto.GoodsDTO;
import com.service.GoodsService;

@Controller
public class MainController {

	@Autowired
	GoodsService service;
	
	@RequestMapping("/")
	public ModelAndView goodsList() {
		List<GoodsDTO> list = service.goodsList("top"); //top카테고리 목록 가져오기
		ModelAndView mav = new ModelAndView();
		mav.addObject("goodsList", list);
		//request.setAttribute("goodsList", list); 동일
		mav.setViewName("main"); //main.jsp => goodsList.jsp
		return mav;
	}
	
}