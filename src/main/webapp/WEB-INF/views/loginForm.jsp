<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
   String mesg = (String)session.getAttribute("mesg");
   if(mesg!=null){
%>
   <script type="text/javascript">
     alert('<%=mesg%>');
   </script>
<%
   }
%>
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