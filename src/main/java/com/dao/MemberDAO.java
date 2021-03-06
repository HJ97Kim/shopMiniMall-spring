package com.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.MemberDTO;

@Repository //component-scan 자동빈생성
public class MemberDAO {
	@Autowired
	SqlSessionTemplate template; //자동주입(sqlSession 과 동일)
	
	public void memberAdd(MemberDTO m) {
		int n = template.insert("MemberMapper.memberAdd", m);
		System.out.println("회원가입 성공 ? (true:1/false:0) = " + n);
	}

	public MemberDTO login(Map<String, String> map) {
		MemberDTO dto = template.selectOne("MemberMapper.login", map);
		return dto;
	}

	public MemberDTO myPage(String userid) {
		MemberDTO dto = template.selectOne("MemberMapper.mypage", userid);
		return dto;
	}

	public void memberUpdate(MemberDTO m) {
		int n = template.update("MemberMapper.memberUpdate", m);
		System.out.println("업데이트 성공 ? (true:1/false:0) = " + n);
	}
	
}
