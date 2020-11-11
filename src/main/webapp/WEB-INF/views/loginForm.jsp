<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:if test="${!empty mesg}">
	<script>
		alert('${mesg}');
	</script>
</c:if>
</head>
<body>
	<h1>로그인 폼 화면입니다.</h1>
	<!-- include -->
	<jsp:include page="common/top.jsp"></jsp:include><br>
	<jsp:include page="common/menu.jsp"></jsp:include><br>
	<hr>
	<!-- include -->
	<jsp:include page="member/loginForm.jsp"></jsp:include>
</body>
</html>