<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Arrays" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
List<String> userList = (List<String>)session.getAttribute("user");

%>
<h1>삭제를 원하시면 밑에다가 회원탈퇴를 입력하십시오</h1>
<h2>회원 탈퇴 후 관련된 데이터는 전부 지워집니다.</h2>

<form method="post" action="<%= request.getContextPath() %>/user?action=userout">
<input type="text" name="id" value='<%= userList.get(0)%>' style="display:none">
<input type="text" name="userout" placeholder="회원탈퇴">
<input type="submit" value="입력완료">
</form>
<button onclick="location.href='<%= request.getContextPath()%>/board'">회원탈퇴 취소</button>
</body>
</html>