package com.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.MemberDAO;
import com.dto.MemberDTO;

@Service //component-scan으로 자동 생성
public class MemberService {
	@Autowired //자동주입
	MemberDAO dao;
	
	public void MemberAdd(MemberDTO m) { //회원가입
		dao.memberAdd(m); //DTO전달
	}
	
	public MemberDTO login(Map<String, String> map) {
		MemberDTO dto = dao.login(map);
		return dto;
	}
	
	public MemberDTO myPage(String userid) {
		MemberDTO dto = dao.myPage(userid);
		return dto;
	}

	public void memberUpdate(MemberDTO m) {
		dao.memberUpdate(m);
	}
}
