package com.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.CartDTO;
import com.dto.GoodsDTO;

@Repository
public class GoodsDAO {
	@Autowired
	SqlSessionTemplate template;

	public List<GoodsDTO> goodsList(String gCategory) {
		List<GoodsDTO> list = template.selectList("GoodsMapper.goodsList", gCategory);
		return list;
	}
	
	public GoodsDTO goodsRetrieve(String gCode) {
		GoodsDTO dto = template.selectOne("GoodsMapper.goodsRetrieve", gCode);
		return dto;
	}

	public void cartAdd(CartDTO dto) {
		int n = template.insert("CartMapper.cartAdd", dto);
		System.out.println("장바구니 담기 성공 ? (true:1/false:0) = " + n);
	}
	
	public List<CartDTO> cartList(String userid) {
		List<CartDTO> list = template.selectList("CartMapper.cartList", userid);
		return list;
	}
	
	public void cartUpdate(Map<String, String> map) {
		int n = template.update("CartMapper.cartUpdate", map);
		System.out.println("장바구니 업데이트 성공 ? (true:1/false:0) = " + n);
	}
}
