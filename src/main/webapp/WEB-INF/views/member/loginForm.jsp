<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:if test="${! empty mesg}">
	<script>
		alert('${mesg}');
	</script>
</c:if>
<form action="login" method="get"><!-- 주소 수정 -->
	아이디:<input type="text" name="userid" id="userid"><br>
	비밀번호:<input type="text" name="passwd" id="passwd"><br> 
	<input type="submit" value="로그인"> 
	<input type="reset" value="취소">
</form>
