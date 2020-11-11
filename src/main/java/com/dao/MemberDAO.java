package com.dao;

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
}
