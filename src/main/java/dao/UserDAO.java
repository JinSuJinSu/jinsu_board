package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import vo.UserVO;

public class UserDAO {
	
	// 회원가입시 아이디 중복 체크
	public List<UserVO> selectAll() {
		Connection conn = JDBC.getConnection();
		List<UserVO> list = new ArrayList<UserVO>();
		try(Statement stmt = conn.createStatement();){
			ResultSet rs = stmt.executeQuery("select user_id from userinfo");
			while(rs.next()) {
				UserVO temp = new UserVO(rs.getString("user_id"));			
				list.add(temp);
				}
			}
			 catch (SQLException se) {
				System.out.println(se.getMessage());
			}
			JDBC.close(conn);		
		return list;
	}
	
	// 회원가입이 성공하면 테이블에 유저 정보 추가
	public boolean insert(UserVO uo) {
		boolean result = false;
		Connection conn = JDBC.getConnection();
		try (PreparedStatement pstmt = conn.prepareStatement("insert into userinfo (user_id, name, password, phone, email) values (?, ?,?,?,?)")) { 
			// 동적파라미터인 ? 를 줄수 있는 개수는 정해지지 않는다. 무한대	
			pstmt.setString(1, uo.getUserID());
			pstmt.setString(2, uo.getName());
			pstmt.setString(3, uo.getPassword());
			pstmt.setString(4, uo.getPhone());
			pstmt.setString(5, uo.getEmail());
			pstmt.executeUpdate();	 //업데이트 필요한 값을 서블릿이 보내준다.	
			result = true;  // 업데이트에 성공하면 TRUE 리턴 한다.
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
		JDBC.close(conn);
		return result;
	}
	
	// 로그인시 아이디와 비밀번호가 일치하는지 확인
	public UserVO selectOne(String id) {
		Connection conn = JDBC.getConnection();
		UserVO vo=null;
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("select user_id, password "
					+ "from userinfo where user_id='" + id + "'");                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
			if (rs.next()) {
				vo = new UserVO();
				vo.setUserID(rs.getString("user_id"));
				vo.setPassword(rs.getString("password"));
			}
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
		JDBC.close(conn);
		return vo;
	}
	
	// 회원 탈퇴 시 유저 삭제
	public boolean userDelete(String user) {
		boolean result = false;
		Connection conn = JDBC.getConnection();
		try (PreparedStatement pstmt = conn.prepareStatement("delete from userinfo where user_id=?")) { 
			pstmt.setString(1, user);
			pstmt.executeUpdate();	 //업데이트 필요한 값을 서블릿이 보내준다.	
			result = true;  // 업데이트에 성공하면 TRUE 리턴 한다.
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
		JDBC.close(conn);
		return result;
	}	
}



	


