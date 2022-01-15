package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import vo.UserVO;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");// 작동(로그아웃, 회원탈퇴)
		HttpSession session = request.getSession();
		List<String> user = (List<String>) session.getAttribute("user");
		if (action != null && action.equals("logout")) {
			session.invalidate(); // 로그아웃 시 유저 정보를 가지고 있는 세션을 삭제해준다.
			out.print("<script>alert('로그아웃에 성공하셨습니다.'); location='/jinsu_board/board'; </script>");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id"); // 유저 아이디
		String password = request.getParameter("password"); // 유저 비밀번호
		String name = request.getParameter("name"); // 유저 이름
		String phone = request.getParameter("phone"); // 유저 핸드폰
		String email = request.getParameter("email"); // 유저 이메일주소
		String action = request.getParameter("action"); // 작동(로그인, 회원가입)
		String userout = request.getParameter("userout"); // 회원탈퇴 조건
		boolean result = false; // dao 메소드를 실행할 때 필요한 변수
		HttpSession session = request.getSession();
		UserDAO dao = new UserDAO();
		UserVO vo = new UserVO();

		if (action != null) {
			if (action.equals("login")) {
				List<String> userList = new ArrayList<>();
				userList.add(id);
				userList.add(password);
				vo = dao.selectOne(id);
				if (vo != null) { // 로그인할 때 아이디와 비밀번호가 일치하는지 체크
					if (userList.get(0).equals(vo.getUserID()) && userList.get(1).equals(vo.getPassword())) {
						if (session.getAttribute("user") == null)
							session.setAttribute("user", userList);
						out.print("<script>alert('로그인에 성공하셨습니다.'); location='/jinsu_board/board'; </script>");
					} else {
						out.print(
								"<script>alert('비밀번호가 일치하지 않습니다.'); location='/jinsu_board/jsp/login.jsp'; </script>");
					}
				} else {
					out.print("<script>alert('아이디가 존재하지 않습니다.'); location='/jinsu_board/jsp/login.jsp'; </script>");
				}
			} else if (action.equals("join")) {
				// 유저 중복 체크
				List<UserVO> userList = dao.selectAll();
				boolean isUser = false;
				for (UserVO user : userList) {
					if (user.getUserID().equals(id)) {
						isUser = true;
						break;
					}
				}
				if (isUser) { // 이미 아이디가 있는 경우
					out.print("<script>alert('중복되는 아이디가 있습니다.'); location='/jinsu_board/jsp/join.jsp'; </script>");
				} else {
					vo.setUserID(id);
					vo.setPassword(password);
					vo.setName(name);
					vo.setPhone(phone);
					vo.setEmail(email);
					dao.insert(vo);
					out.print("<script>alert('회원가입에 성공하셨습니다.'); location='/jinsu_board/jsp/login.jsp'; </script>");
				}
			} else if (action.equals("userout")) {
				if (userout.equals("회원탈퇴")) {
					result = dao.userDelete(id);
					if (result) {
						List<String> user = (List<String>) session.getAttribute("user");
						session.invalidate();
						out.print("<script>alert('회원 탈퇴에 성공하셨습니다.지금까지 이용해주셔서 감사합니다.'); location='/jinsu_board/board'; </script>");
					}
				} else {
					out.print("<script>alert('탈퇴를 원하시면 회원탈퇴 제대로 입력하세요.'); location='/jinsu_board/jsp/userout.jsp'; </script>");
				}
			}
		}

	}
}
