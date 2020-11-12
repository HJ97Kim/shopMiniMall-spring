package com.controller;

import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.dto.MemberDTO;
import com.service.MemberService;

@Controller
public class LoginController {
	@Autowired
	MemberService service;
	
	@RequestMapping(value = "/login")
	public String login(@RequestParam Map<String, String> map, Model model, HttpSession session) {
		String nextPage = "";
		MemberDTO dto = service.login(map);
		System.out.println("login=="+dto);
		if(dto != null) {
			session.setAttribute("login", dto); //로그인 정보 저장
			nextPage = "main";
		} else {
			model.addAttribute("mesg", "아이디 또는 비번이 잘못되었습니다.");
			nextPage = "loginForm";
		}
		return nextPage;
	}
	
	@RequestMapping(value = "/loginCheck/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:../"; //.xml에 설정 main.jsp ../ 을 이용하여 /loginCheck 의 상위 주소로 이동하여 주소를 사용함
	}
	
}
