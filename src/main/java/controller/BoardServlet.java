package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.BoardFactory;
import dao.BoardDAO;
import mvc.Action;
import mvc.ActionFactory;
import vo.BoardVO;

@WebServlet("/board")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String actionName = request.getParameter("action");
		ActionFactory af = new BoardFactory();
		Action action = af.getAction(actionName);
		action.execute(request, response);
		
		// TODO Auto-generated method stub
//		response.setContentType("text/html; charset=UTF-8");
//		request.setCharacterEncoding("UTF-8");
//		String target = "/jsp/mainpage.jsp";
//		String action = request.getParameter("action"); // 게시판 작업(수정, 삭제, 조회, 검색, 추가)
//		String no = request.getParameter("boardNo"); // 게시글의 번호
//		String word = request.getParameter("word");  // 검색에 입력된 단어
//		String condition = request.getParameter("condition"); //검색에 필요한 조건
//		String page = request.getParameter("page"); // 페이징 처리 번호
//		String id = request.getParameter("id"); // 유저의 아이디
//		String title = request.getParameter("title"); // 게시판 제목
//		String content = request.getParameter("content"); // 게시판 내용
//		String rno = request.getParameter("replyNo"); // 댓글 번호
//		
////		String actionName = request.getParameter("a");
////		ActionFactory af = new GuestbookActionFactory();
////		Action action = af.getAction(actionName);
////		action.execute(request, response);
//		
//		int boardNo = 0;
//		int replyNo = 0;
//		BoardDAO dao = new BoardDAO();
//		BoardVO vo = new BoardVO();
//		ReplyDAO rdao = new ReplyDAO();
//		ReplyVO rvo = new ReplyVO();
//		boolean result=false;	
//		
//		// 페이징 처리를 미리 해준다.
//		if(no!=null) {
//			boardNo = Integer.valueOf(request.getParameter("boardNo")); // 게시글 번호 받아오기
//			page = pageCheck(dao,boardNo); // 게시판에 있는 내용을 확인하고 돌아갔을 때 그 시점에 맞는 페이지 번호로 돌아가야 한다.
//		}
//		
//		// 게시글 조회하기
//		if(action!=null && action.equals("read")) {
//			vo = dao.selectOne(boardNo);
//			vo.setReadCount(vo.getReadCount()+1); // 게시물을 볼때마다 조회수를 1개 증가시킨다.
//			result = dao.readUpdate(vo); // 증가시킨 조회수를 update해서 db 데이터를 수정해준다.
//			List<ReplyVO> dataList = rdao.selectReply(boardNo);
//			request.setAttribute("readvo", vo);
//			request.setAttribute("reply", dataList);
//			target = "/jsp/readpage.jsp";	
//		}
//		
//		// 게시판 글 삽입하기
//		if(action!=null && action.equals("insert")) {		
//			vo.setUserID(id);
//			vo.setContent(content);
//			vo.setTitle(title);
//			result = dao.insert(vo);
//		}
//
//		// 데이터 삭제하기
//		if(action!=null && action.equals("delete")) {	
//			result = dao.delete(boardNo);
//		}
//		
//		// 데이터 편집하기
//		if(action!=null && action.equals("update")) {
//			vo.setBoardNO(boardNo);
//			vo.setContent(content);
//			vo.setTitle(title);
//			result = dao.update(vo);
//		}
//		
//		// 게시판 검색 기능
//		if(action!=null && action.equals("search")) {
//			List<BoardVO> dataList = new ArrayList<>();
//			if(condition!=null && condition.equals("subtitle")) { // 제목 기준으로 검색
//				dataList = dao.search(word, "title");
//			}
//			else if(condition!=null && condition.equals("subcontent")) { // 내용 기준으로 검색
//				dataList = dao.search(word, "content");
//			}
//			else if(condition!=null && condition.equals("subid")) { // 작성자 기준으로 검색
//				dataList = dao.searchUser(word);
//			}
//			request.setAttribute("data", dataList);
//			request.setAttribute("totalpage", new ArrayList<BoardVO>());		
//			RequestDispatcher rd = request.getRequestDispatcher(target);
//			rd.forward(request, response);						
//		}
//			
//		// 댓글 추가하기
//		if(action!=null && action.equals("reply")) {			
//			rvo.setBoardNo(boardNo);
//			rvo.setReplyer(id);
//			rvo.setReplyContent(content);
//			result = rdao.insert(rvo);
//			
//			// 댓글을 달았으므로 댓글 수를 초기화 해준다.
//			List<ReplyVO> replyList = rdao.selectReply(boardNo);
//			vo = dao.selectOne(boardNo);
//			vo.setReplyCount(replyList.size());
//			result = dao.replyUpdate(vo);						
//		}
//		
//		// 댓글이 편집됬는지 먼저 확인한다.
//		if(rno!=null) {
//			replyNo = Integer.valueOf(rno);
//		}
//		
//		// 댓글 삭제하기
//		if(action!=null && action.equals("replyDelete")) {
//			vo = dao.selectOne(boardNo); // 특정 게시판 번호의 vo 객체 생성
//			result = rdao.replyDelete(replyNo); // 댓글삭제
//			List<ReplyVO> replyList = rdao.selectReply(boardNo); // 나머지 댓글들 조회
//			vo.setReplyCount(replyList.size()); // 댓글 수 설정하기
//			dao.replyUpdate(vo); // 수정된 댓글 수의 값을 update 해준다
//		}
//		
//		// 댓글 수정하기
//		if(action!=null && action.equals("replyUpdate")) {
//			rvo.setReplyNo(replyNo);
//			rvo.setReplyContent(content);
//			result = rdao.replyUpdate(rvo);
//		}
//		
//		// 게시판 글 전체 조회
//		List<BoardVO> dataList = dao.selectAll();
//		request.setAttribute("totalpage", dataList);
//	    // 페이징 시작 번호
//		int startPage;
//		if(page==null) {
//			startPage = 1;			
//		}
//		else if(page.equals("1")) {
//			startPage=1;		
//		}
//		else {
//			startPage = Integer.valueOf(page);
//			startPage = ((startPage-1)*10)+1;
//		}
//		// 페이징에 맞는 게시판 목록 조회
//		List<BoardVO> limitList = dao.selectPage(startPage-1, 10);
//		request.setAttribute("data", limitList);
//		RequestDispatcher rd = request.getRequestDispatcher(target);
//		rd.forward(request, response);				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	
		}
		
	}

	

		
	


