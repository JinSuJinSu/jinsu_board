package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BoardDAO;
import mvc.Action;
import util.MvcUtil;
import vo.BoardVO;

public class MainAction implements Action{
	
	// 페이징 처리를 위해 필요한 함수
	protected String pageCheck(BoardDAO dao, int boardNumber) {
		List<BoardVO> dataList = dao.selectAll();
		int startNum=1;
		for(BoardVO value : dataList) {
			if(value.getBoardNO()==boardNumber) {
				break;
			}
			else {
				startNum+=1;
			}
		}
		int rangeNumber = (int)Math.ceil((double)startNum/10);
		return String.valueOf(rangeNumber);
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no =  request.getParameter("boardNo"); // 게시글의 번호
		String page = request.getParameter("page"); // 페이징 처리 번호
		String arrow = request.getParameter("arrow"); // 화살표 클릭 체크
		String position = request.getParameter("position"); // 페이지 번호 클릭 체크
		
		BoardVO vo = new BoardVO(); // 게시판 vo 객체 생성
		BoardDAO dao = new BoardDAO(); // 게시판 dao 객체 생성
		int boardNo;
		
		// 페이징 처리 전에 조회수 session을 삭제해버린다.
				HttpSession session = request.getSession();
				if((int[])session.getAttribute("read")!=null) {
					session.removeAttribute("read"); // 조회가 끝나면 해당 세션을 제거해준다.
				}
					
				if (no != null) {
					boardNo = Integer.valueOf(no); // 게시글 번호 받아오기
					page = pageCheck(dao, boardNo); // 게시판에 있는 내용을 확인하고 돌아갔을 때 그 시점에 맞는 페이지 번호로 돌아가야 한다.
				}

				// mysql limit 시작 포인트
				int startPoint;
				dao = new BoardDAO();
				List<BoardVO> list = dao.selectAll();
				request.setAttribute("list", list);

				// 페이징에 맞는 게시판 목록 조회
				if (page == null || page.equals("1")) {
					startPoint = 1;
				}
				 else {
					startPoint = Integer.valueOf(page);
					if (startPoint < 1) {
						startPoint = 1;
					} else {
						startPoint = ((startPoint - 1) * 10) + 1;
						if (Integer.valueOf(page) > Math.ceil((double) list.size() / 10)) {// 페이징 화살표 처리시 startpoint가 list의 범위를 벗어날 경우
							if(list.size()%10==0) { // 전체 개시물의 수가 5로 나눴을 때 딱 맞을 경우
								startPoint = list.size()-9;
							}
							else {
								startPoint = 10*(list.size() / 10)+1;
							}			
						}
					}
				}
				int startPage = 1;
				int endPage = 10;

				
				if((arrow==null || arrow.equals(""))) {
					session.setAttribute("point",new int[]{startPoint}); // point 세션을 만들어 mysql limit 시작점을 저장해준다.
					if((position!=null)) {
						startPage = Integer.valueOf(position); 
						// 페이지 1개당 글 10개고 화살표를 클릭했을 때 startPage는 10이 증가한다. 
						endPage = startPage + 9; // 화살표 양옆으로 10개씩 페이지가 나오므로 endpage는 stratpage에 9를 더해준다.
						if (endPage >= Math.ceil((double) list.size() / 10)) {
							endPage = (int) Math.ceil((double) list.size() / 10); // 예들 들어 게시글의 개수가 216개일 때 최대 페이지 번호는 22이고 그보다 크면 범위를 벗어나므로 최대페이지 번호로 고정시킨다.
						}
					}			
				}
				else {
					startPage = Integer.valueOf(page);  // 화살표를 클릭했을 시 시작페이지는 (1,5,10) 증가한다.
					endPage = startPage + 9; 
					if (endPage >= Math.ceil((double) list.size() / 10)) {
						endPage = (int) Math.ceil((double) list.size() / 10); 
					}
					int[] point = (int[])session.getAttribute("point"); 
					startPoint=point[0];
				}
				
				List<BoardVO> limitList = dao.selectPage(startPoint - 1, 10); // mysql limit 시작은 1이 아닌 0부터이다.
				request.setAttribute("data", limitList); // 게시판 글목록 request 객체
				request.setAttribute("paging", new int[] { startPage, endPage }); // 페이징 reuqest 객체
				MvcUtil.forward("mainpage", request, response);
	}

}
