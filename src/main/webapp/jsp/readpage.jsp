<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="vo.BoardVO" %>
<%@ page import="vo.ReplyVO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/readpage.js"></script>
<%
List<String> userList = (List<String>)session.getAttribute("user");
List<ReplyVO> replyList = (List<ReplyVO>)request.getAttribute("reply"); // 댓글을 단 사람들이 저장되어 있는 데이터 리스트
BoardVO vo = (BoardVO)request.getAttribute("readvo");
%>

<h1>게시판 조회</h1>
<hr>
<h2 id="user">로그인 유저 : <%=userList.get(0)%></h2>
<form id = "form" method="post" action="<%= request.getContextPath()%>/board">
<input type="hidden" name = "boardNo" value = "${readvo.boardNO}">
<input type="hidden" name = action value = "update">
작성자 : <input id="idcheck1" value="${readvo.userID}" readonly><br>
제목 : <input id = "title" name="title" value="${readvo.title}" readonly> <br>
내용물 : <br>
<textarea id = "content" name="content" rows="10" cols="35" readonly>${readvo.content}</textarea><br>
<%
if(vo.getFileurl()!=null){	
%>	
	이미지<br>
	<img src="<%=vo.getFileurl()%>" width="200px" height="200px">
<%	
}
%>
<div id = "post" style="display:none">
<input type="submit" value="수정완료" onclick="alert('수정이 완료되었습니다.')">
<input type="reset" value="다시작성">
</div>
</form>
<button id = "backbtn" onclick="back();">게시판 목록으로</button>
<%
if(userList.get(0).equals(vo.getUserID())){
%>
	<button id = "rbtn" onclick="revision();">수정</button>
	<button id = "dbtn" onclick="elimination();">삭제</button>
	<div id = "revision" style="display:none">
	이미지 : <input type="file" name="file" >
	<br>
	<button onclick="dataReset();">글 전부 지우기</button>
	<button onclick="deleteimg();">이미지 지우기</button>
	<button onclick="revision_cancel();">수정 취소</button>
	</div>
<%		
}
%>

<button id = "replybtn" onclick="reply();">댓글 달기</button>

<h2>댓글 목록</h2>
<div id="replyform" style="display:none">
<form method="post" action="<%= request.getContextPath()%>/board">
<input type="hidden" name = "action" value="reply">
<input type="hidden" name="boardNo" value="${readvo.boardNO}">
<input name="id" type="hidden" value="<%=userList.get(0)%>">
<textarea name="content" rows="5" cols="35" required ></textarea>
<br>
<input type="submit" value="작성완료" onclick="alert(댓글 작성이 완료되었습니다.)"></form>
<button id = "rejectbtn" onclick="reject();">작성 취소</button>
</div>

<%
if(replyList.size()>0){ // for문으로 답글을 단 사람들 정보를 하나씩 출력
	for(int i=0; i<replyList.size(); i++){
%>
		 <form method="post" action="<%= request.getContextPath()%>/board">
		 <input type="hidden" name="action" value = "replyUpdate">
		 <input type="hidden" name="boardNo" value = "${readvo.boardNO}">
		 글번호 : <input name = "replyNo" class="replyno<%=i%>" value = "<%= replyList.get(i).getReplyNo() %>" readonly><br>
		 댓글 작성자 : <input class="replyer<%=i%>" value = "<%= replyList.get(i).getReplyer() %>" readonly><br>
		 <textarea class = "replycontent<%=i%>" name = "content" rows="5" cols="35" readonly>
		 <%= replyList.get(i).getReplyContent() %> </textarea><br>
		 작성 날짜 : <input value="<%= replyList.get(i).getReplyDate() %>"><br>
		 <div id="btns<%=i%>" style="display:none">
		 <input type="submit" value="수정완료" onclick="alert('수정이 완료되었습니다.')">
		 <input type="reset" value="다시작성">
		 </div>
		 </form>
<%
		 if(replyList.get(i).getReplyer().equals(userList.get(0))){
%>			 
			 <button id = "editbtn<%=i%>" onclick="reply_edit(<%=i%>);">수정</button>
		 	 <button id = "deletebtn<%=i%>" onclick="reply_delete(<%=i%>);">삭제</button>	
		 	 <button id = "cancelbtn<%=i%>" onclick="reply_cancel(<%=i%>);" style="display:none">수정 취소</button>
		 	 <button id = "resetbtn<%=i%>" onclick="reply_reset(<%=i%>);" style="display:none">댓글 새로쓰기</button>
<%	
		}
%>
		<br>
		<br>
<%
	}
}
%>
</body>
</html>