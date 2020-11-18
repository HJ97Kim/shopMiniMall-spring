package com.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.CartDTO;
import com.dto.GoodsDTO;
import com.dto.OrderDTO;

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

	public void cartDel(int num) {
		int n = template.delete("CartMapper.cartDel", num);
		System.out.println("장바구니 삭제 성공 ? (true:1/false:0) = " + n);
	}
	
	public void delAllCart(ArrayList<String> list) {
		int n = template.delete("CartMapper.cartAllDel", list);
		System.out.println("장바구니 전체 삭제 성공 ? (true:1/false:0) = " + n);
	}
	
	public CartDTO orderConfirmByNum(int num) {
		CartDTO dto = template.selectOne("CartMapper.cartbyNum", num);
		return dto;
	}

	public void orderDone(OrderDTO oDTO, int orderNum) {
		int n = template.insert("CartMapper.orderDone", orderNum);
		System.out.println("장바구니 주문 성공 ? (true:1/false:0) = " + n);
	}
	
}
