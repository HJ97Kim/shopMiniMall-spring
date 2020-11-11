package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dto.MemberDTO;
import com.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	MemberService service;
	
	@RequestMapping(value = "/memberAdd")
	public String memberAdd(MemberDTO m, Model model) { // 폼에서 넘어온 데이터를 MemberDTO에 자동저장
		service.MemberAdd(m); //회원가입 성공
		model.addAttribute("success", "회원가입성공"); //main.jsp에서 출력할 success 문구 저장
		return "main"; // main.jsp
	}
	
}
