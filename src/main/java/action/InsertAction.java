package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import mvc.Action;
import util.MvcUtil;
import vo.BoardVO;

public class InsertAction  implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id"); // 유저의 아이디
		String title = request.getParameter("title"); // 게시판 제목
		String content = request.getParameter("content"); // 게시판 내용
		
		
		BoardVO vo = new BoardVO(); // 게시판 vo 객체 생성
		BoardDAO dao = new BoardDAO(); // 게시판 dao 객체 생성
		boolean result = false; // insert 수행을 위해 필요한 변수
	
		if(id!=null && title!=null && content!=null) {
			vo.setUserID(id);
			vo.setContent(content);
			vo.setTitle(title);
			result = dao.insert(vo);
		}
			
		if(result){
			// Board Servlet으로 돌아간후 MainAction으로 글을 조회한다.
			MvcUtil.redirect(request.getContextPath() + "/board",request,response);
		}
		
		
	}

}
