package com.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@RequestMapping(value = "/loginCheck/memberUpdate")
	public String memberUpdate(MemberDTO m) {
		System.out.println(m);
		service.memberUpdate(m);
		return "redirect:../loginCheck/myPage";
	}
	
	@RequestMapping(value = "/loginCheck/myPage")
	public String myPage(HttpSession session) {
		//인터셉터에서 로그인 인증 후 페이지 이동
		MemberDTO dto = (MemberDTO) session.getAttribute("login");
		String userid = dto.getUserid();
		dto = service.myPage(userid);
		session.setAttribute("login", dto);
		System.out.println(dto);
		return "redirect:../myPage"; //주소에 해당하는 페이지를 servlet-context에 등록사용
	}
	
	@RequestMapping(value = "/idDuplicateCheck", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String idDuplicateCheck(@RequestParam("id") String userid) {
		MemberDTO dto = service.myPage(userid);
		System.out.println("idDuplicateCheck====" + userid);
		String mesg = "아이디 사용가능";
		if(dto != null) {
			mesg = "아이디 중복";
		}
		return mesg; //메세지 전달 //리턴페이지가 아님
	}
	
}
