package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import dao.ReplyDAO;
import mvc.Action;
import util.MvcUtil;
import vo.BoardVO;
import vo.ReplyVO;

public class readAction implements Action {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			String no =  request.getParameter("boardNo"); // 게시글의 번호
			int boardNo=0; //정수 타입의 게시글 번호를 먼저 선언해준다.
		
			BoardVO vo = new BoardVO(); // 게시판 vo 객체 생성
			BoardDAO dao = new BoardDAO(); // 게시판 dao 객체 생성
			boolean result = false; // 조회수 증가 메소드를 위해 필요한 변수
			ReplyDAO rdao = new ReplyDAO(); // 댓글 dao 객체 생성
			
			if(no!=null) {
				boardNo = Integer.valueOf(no); // 문자열을 정수 타입으로 변환
			}
			
			vo = dao.selectOne(boardNo);
			vo.setReadCount(vo.getReadCount()+1); // 게시물을 볼때마다 조회수를 1개 증가시킨다.
			result = dao.readUpdate(vo); // 증가시킨 조회수를 update해서 db 데이터를 수정해준다.
			List<ReplyVO> dataList = rdao.selectReply(boardNo); // 해당 게시판 번호에 해당하는 답글 리스트를 받아온다.
			request.setAttribute("readvo", vo);
			request.setAttribute("reply", dataList);
			MvcUtil.forward("readpage",request,response); // readpage.jsp 파일로 이동
			
		}

	}


