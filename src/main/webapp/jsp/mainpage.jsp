<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.BoardVO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.lang.Math" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
</style>
</head>
<body>
<script>
function dislpayDiv(number){ // 새로운 글을 작성하는 함수
	let value = (document.getElementById("userinfo")) //사용자에 대한 정보를 받아온다.
	if(number==1){
		if(value.innerText==='사용자 : 비회원'){
			alert("로그인 하셔야 작성이 가능합니다.") // 비회원일 경우 사용 불가능
		}
		else{
			// 비회원일 경우 글 작성 가능, write라는 id를 가진 태그를 보여주고 add라는 id를 가진 태그를 숨긴다.
			document.getElementById("write").style.display='block';
			document.getElementById("add").style.display='none';	
		}
	}
}

function reject(){ // 글 작성 취소
	document.getElementById("write").style.display='none';
	document.getElementById("add").style.display='block';	
}

function reload(){ // 새로고침 함수 : 조회수 최신 반영이 안되어 있을 경우 실행
	let value = (document.getElementById("userinfo"))
	if(value.innerText==='사용자 : 비회원'){
		alert("로그인 하셔야 사용 가능합니다.")
	}
	else{
		window.location.reload();
	}
}
</script>

<%
List<String> userList = new ArrayList<String>();
if(session.getAttribute("user") == null){ // 사용자 세션 정보가 없으면 비회원으로 처리
	userList = new ArrayList<String>(Arrays.asList("비회원","B"));
}
else{
	userList = (List<String>)session.getAttribute("user"); // 사용자 세션 정보 얻기
}

%>	
<h3 id="userinfo">사용자 : <%=userList.get(0)%></h3>
<%
if(userList.get(0).equals("비회원")){
%>
<h3><a href="<%= request.getContextPath() %>/jsp/login.jsp">로그인</a></h3>
<h3><a href="<%= request.getContextPath() %>/jsp/join.jsp">회원가입</a></h3>
<%
}
else{
%>
<h3><a href="<%= request.getContextPath() %>/user?action=logout">로그아웃</a></h3>
<h3><a href="<%= request.getContextPath() %>/jsp/userout.jsp">회원탈퇴</a></h3>

<%
}
%>
<h2><a href="<%= request.getContextPath() %>/jsp/firstpage.jsp">메인 화면으로</a></h2>
<button onClick="reload()" display="block">조회수 최신반영</button>
<table border="1">
<tr><th>글번호</th><th>작성자</th><th>글제목</th><th>조회수</th><th>댓글수</th><th>작성 날짜</th></tr>  

<%
  List<BoardVO> list = (List<BoardVO>)request.getAttribute("data");
  for (BoardVO value : list){// request객체로 받아온 게시판 데이터들을 for문으로 돌리면서 하나씩 받아온다.
%>
		 <tr><td><%= value.getBoardNO()%></td>
		 <td><%= value.getUserID()%></td>
<% 
		 if(userList.get(0).equals("비회원")){ // 로그인이 아직 안되어 있으면 열람은 불가능하다.
%> 		 
		<td><a href="<%= request.getContextPath() %>/jsp/login.jsp"><%= value.getTitle()%></a></td>		 
<%
		 }
		 else{
%> 			<!-- 제목을 클릭하면 상세내용 조회가 가능하다. -->
			<td><a href="<%= request.getContextPath() %>/board?boardNo=<%= value.getBoardNO() %>&action=read" style="text-decoration-line:none";>
			<%= value.getTitle()%></a></td>			
<%
		 }		 
%>
		 <td><%= value.getReadCount()%></td>
		 <td><%= value.getReplyCount()%></td>
		 <td><%= value.getWriteDate()%></td></tr> 
<%	
	}
 %>
</table>
		<c:if test = "${paging[0]>10}">
		<a href="${pageContext.servletContext.contextPath}/board?arrow=arrow&page=${paging[0]-10}" style="font-size:20px; text-decoration-line:none; color:red;" >◀◀◀</a>
		</c:if>
		<c:if test = "${paging[0]>5}">
		<a href="${pageContext.servletContext.contextPath}/board?arrow=arrow&page=${paging[0]-5}" style="font-size:20px; text-decoration-line:none; color:green;">◀◀</a>
		</c:if>
		<c:if test = "${paging[0]>1}">
		<a href="${pageContext.servletContext.contextPath}/board?arrow=arrow&page=${paging[0]-1}" style="font-size:20px; text-decoration-line:none; color:black;">◀</a>
		</c:if>
		
		<c:forEach  begin="${paging[0]}" end="${paging[1]}"  step="1" var="page">
			<a href="${pageContext.servletContext.contextPath}/board?position=${paging[0]}&page=${page}" style="font-size:20px; text-decoration-line:none;">${page}</a>
		</c:forEach>
		
		<c:if test = "${Math.ceil(list.size()/10) - paging[0]>=1}">
		<a href="${pageContext.servletContext.contextPath}/board?arrow=arrow&page=${paging[0]+1}" style="font-size:20px; text-decoration-line:none; color:black;">▶</a>
		</c:if>
		<c:if test = "${Math.ceil(list.size()/10) - paging[0]>=5}">
		<a href="${pageContext.servletContext.contextPath}/board?arrow=arrow&page=${paging[0]+5}" style="font-size:20px; text-decoration-line:none; color:green;">▶▶</a>
		</c:if>
		<c:if test = "${Math.ceil(list.size()/10) - paging[0]>=10}">
		<a href="${pageContext.servletContext.contextPath}/board?arrow=arrow&page=${paging[0]+10}" style="font-size:20px; text-decoration-line:none; color:red;">▶▶▶</a>
		</c:if>
		
		


<h3>총 글의 개수 : ${totalpage.size()}</h3>
<form method=get action="<%= request.getContextPath() %>/board"> 
<input type="hidden" name="action" value="search">
<select name="condition">
    <option value="" disabled>검색</option>
    <option value="subtitle">제목</option>
    <option value="subcontent">내용</option>
    <option value="subid">작성자</option>
</select>
<input name = "word" placeholder="글자를 입력하세요">

</form>
<button id ="add" onclick="dislpayDiv(1);"> 게시판 작성</button>
<div id="write"  style="display:none">
<h1>글을 작성하세요</h1>
<form method = "post" action="<%= request.getContextPath() %>/board"> <!-- 서블릿으로 request -->
<input type = "hidden" name="action" value="insert"><!-- 삽입을 수행한다. -->
아이디 : <input name="id" value="<%=userList.get(0)%>" readonly><br>
글 제목 : <input name="title" type="text" required maxlength="20" placeholder="제목은 최대 20자까지 작성 가능합니다."><br>
글의 내용 : <br>
<textarea name="content" rows="10" cols="40" required maxlength="300"
placeholder="텍스트는 최대 300까지 작성 가능합니다."></textarea>
<br>
이미지 : <input type="file" name="file" >
<input type="submit" value="등록" onclick="alert('글이 추가되었습니다.')">
<input type="reset" value="재작성">
<input type="submit" value="작성취소" onclick="reject();">
</form>
</div>

</body>
</html>