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