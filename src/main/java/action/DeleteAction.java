package action;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import mvc.Action;
import util.MvcUtil;
import vo.BoardVO;


public class DeleteAction implements Action{

	

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String no =  request.getParameter("boardNo"); // 게시글의 번호
		int boardNo=0; //정수 타입의 게시글 번호를 먼저 선언해준다.
		
		BoardDAO dao = new BoardDAO(); // 게시판 dao 객체 생성
		boolean result=false; // 삭제 기능을 위해 필요한 변수
		
		if(no!=null) {
			boardNo = Integer.valueOf(no);
		}
		result = dao.delete(boardNo);
		if(result){ // 삭제 성공시
				MvcUtil.redirect(request.getContextPath() + "/board?boardNo=" + no,request,response);
		}	
	}

}
