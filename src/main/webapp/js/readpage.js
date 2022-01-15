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
	window.location.href = "/jinsu_board/board?action=back&boardNo=${readvo.boardNO}"
}

function elimination(){// 게시글 삭제 메소드
	window.location.href = "/jinsu_board/board?action=delete&boardNo=${readvo.boardNO}"
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