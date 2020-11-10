<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:if test="${empty login}"> <!-- 세션에서 로그인 정보 검사 로그인 안된 경우 -->
	<a href="loginForm">로그인</a>&nbsp; <!-- xml설정 O -->
	<a href="CartListServlet">장바구니</a>&nbsp;
	<a href="memberForm">회원가입</a>&nbsp; <!-- xml설정 O -->
</c:if>
<c:if test="${!empty login}"> <!-- 세션에서 로그인 정보 검사 로그인 된 경우 -->
	안녕하세요? ${login.username} 님!<br>
	<a href="LoginServlet">로그아웃</a>&nbsp;
	<a href="CartListServlet">마이페이지</a>&nbsp;
	<a href="MemberUIServlet">장바구니</a>&nbsp;
</c:if>