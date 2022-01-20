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
<script>
function revision(){ // 게시글 수정 메소드
	document.querySelector("#revision").style.display='block'; // 수정 작업에 필요한 버튼들을 보여준다.
	document.querySelector("#post").style.display='block'; // 수정 작업에 필요한 추가 버튼들
	document.querySelector("#rbtn").style.display='none'; // 수정 버튼을 숨긴다.
	document.querySelector("#dbtn").style.display='none'; // 삭제 버튼을 숨긴다.
	document.querySelector("#backbtn").style.display='none'; // 목록 돌아가기 버튼을 숨긴다.
	document.querySelector("#title").readOnly = false // readonly를 false로 바꿔 수정가능하게 함
	document.querySelector("#content").readOnly = false // readonly를 false로 바꿔 수정가능하게 함
}

function revision_cancel(){ // 수정 취소 메소드
	window.location.href = "/jinsu_board/board?boardNo=${readvo.boardNO}"
}

function elimination(){// 게시글 삭제 메소드
	window.location.href = "/jinsu_board/board?action=delete&boardNo=${readvo.boardNO}""
	alert("삭제가 완료되었습니다.")	
}

function back(){ // 게시글 되돌아가기 메소드
	window.location.href = "/jinsu_board/board?action=back&boardNo=${readvo.boardNO}"
}

function reply(){ // 댓글 달기(댓글 입력창을 표시해준다.)
	document.querySelector("#replyform").style.display='block';
	document.querySelector("#reply").style.display='none';	
}

function reject(){ // 댓글 작성을 위해 댓글 입력창을 숨긴다.
	document.querySelector("#replyform").style.display='none';
	document.querySelector("#reply").style.display='block';	
}

function dataReset(){ // 댓글을 완전히 새로 쓰고 싶을 때 쓰는 메소드(모든 글을 지워준다.)
	document.querySelector("#title").value=""
	document.querySelector("#content").value=""	
}

function deleteimg(){ // 이미지를 지워주고 싶을 때 쓰는 메소드
	document.querySelector("#img").src=""
}

function reply_edit(index){ // 댓글 수정 메소드
	 document.querySelector('#btns' + String(index)).style.display='block'
	 document.querySelector('#cancelbtn' + String(index)).style.display='block'
	 document.querySelector('#resetbtn' + String(index)).style.display='block'
	 document.querySelector('#editbtn' + String(index)).style.display='none'
	 document.querySelector('#deletebtn' + String(index)).style.display='none'
	 document.querySelector('.replycontent' + String(index)).readOnly = false
}

function reply_cancel(index){ // 댓글 수정 취소 메소드
	 document.querySelector('#btns' + String(index)).style.display='none'
	 document.querySelector('#cancelbtn' + String(index)).style.display='none'
	 document.querySelector('#resetbtn' + String(index)).style.display='none'
	 document.querySelector('#editbtn' + String(index)).style.display='block'
	 document.querySelector('#deletebtn' + String(index)).style.display='block'
	 document.querySelector('.replycontent' + String(index)).readOnly = true
}

function reply_delete(index){// 댓글 삭제 메소드
	let reply_number = document.querySelector('.replyno' + String(index)).value
	console.log(reply_number)
	alert("삭제가 완료되었습니다.")
	location.href="/jinsu_board/board?action=replyDelete&boardNo=${readvo.boardNO}&replyNo=" + reply_number
}

function reply_reset(index){ // 댓글 수정 시 글 전비 지워주는 메소드
	document.querySelector('.replycontent' + String(index)).value = ""
}
</script>
<%
List<String> userList = (List<String>)session.getAttribute("user");
List<ReplyVO> replyList = (List<ReplyVO>)request.getAttribute("reply"); // 댓글을 단 사람들이 저장되어 있는 데이터 리스트
BoardVO vo = (BoardVO)request.getAttribute("readvo");
%>

<h1>게시판 조회</h1>
<hr>
<h2 id="user">로그인 유저 : <%=userList.get(0)%></h2>
<form id = "form" method="post" action="<%= request.getContextPath()%>/board">
<input id = "board" type="hidden" name = "boardNo" value = "${readvo.boardNO}">
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