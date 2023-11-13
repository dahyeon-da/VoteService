package tukorea.web.club.persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tukorea.web.club.domain.memberVO;

public class memberDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	String jdbc_driver = "com.mysql.cj.jdbc.Driver";
	String jdbc_url = "jdbc:mysql://localhost/jspdb?allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC";

	void connect() {
		try {
			Class.forName(jdbc_driver);
			conn = DriverManager.getConnection(jdbc_url, "spring", "6692");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void disconnect() {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean addMember(memberVO vo) {
		connect();
		String sql = "insert into dahyeon_free_member values (?, ?, ?, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPasswd());
			pstmt.setString(3, vo.getUsername());
			pstmt.setString(4, vo.getNickname());
			pstmt.setString(6, vo.getMobile());
			pstmt.setString(7, vo.getEmail());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}
		return true;
	}

	public ArrayList<memberVO> getStudentList() {
		connect();
		ArrayList<memberVO> studentlist = new ArrayList<memberVO>();
		String sql = "select * from mvcdbstudent";
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				StudentVO vo = new StudentVO();
				vo.setId(rs.getString("id"));
				vo.setPasswd(rs.getString("passwd"));
				vo.setUsername(rs.getString("username"));
				vo.setSnum(rs.getString("snum"));
				vo.setDepart(rs.getString("depart"));
				vo.setMobile(rs.getString("mobile"));
				vo.setEmail(rs.getString("email"));
				studentlist.add(vo);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return studentlist;
	}

	public StudentVO read(String studentID) {
	    // 데이터베이스 연결 설정
	    connect();
	    
	    // 학생 정보를 담을 객체 생성
	    StudentVO studentupdate = new StudentVO();

	    // SQL 쿼리 작성
	    String sql = "SELECT * FROM mvcdbstudent WHERE id = ?";

	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, studentID);

	        // 쿼리 실행
	        ResultSet rs = pstmt.executeQuery();

	        // 학생 정보 읽어오기
	        studentupdate = extractStudentInfo(rs);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // 데이터베이스 연결 및 리소스 해제
	        disconnect();
	    }
	    return studentupdate;
	}

	private StudentVO extractStudentInfo(ResultSet rs) throws SQLException {
	    StudentVO studentupdate = new StudentVO();

	    if (rs.next()) {
	        studentupdate.setId(rs.getString("id"));
	        studentupdate.setPasswd(rs.getString("passwd"));
	        studentupdate.setUsername(rs.getString("username"));
	        studentupdate.setSnum(rs.getString("snum"));
	        studentupdate.setDepart(rs.getString("depart"));
	        studentupdate.setMobile(rs.getString("mobile"));
	        studentupdate.setEmail(rs.getString("email"));
	    }

	    return studentupdate;
	}
	
	public boolean update(StudentVO studentVO) {
	    connect();
	    boolean success = false;

	    try {
	        // SQL UPDATE 쿼리 작성
	        String sql = "UPDATE mvcdbstudent SET passwd=?, username=?, snum=?, depart=?, mobile=?, email=? WHERE id=?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, studentVO.getPasswd());
	        pstmt.setString(2, studentVO.getUsername());
	        pstmt.setString(3, studentVO.getSnum());
	        pstmt.setString(4, studentVO.getDepart());
	        pstmt.setString(5, studentVO.getMobile());
	        pstmt.setString(6, studentVO.getEmail());
	        pstmt.setString(7, studentVO.getId());

	        // UPDATE 쿼리 실행
	        int rowsAffected = pstmt.executeUpdate();

	        if (rowsAffected > 0) {
	            // 업데이트 성공
	            success = true;
	            System.out.println("학생 정보 업데이트 성공");
	        } else {
	            // 업데이트 실패
	            System.out.println("학생 정보 업데이트 실패");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        disconnect();
	    }
	    return success;
	}



	public void delete(String strId) {
		connect();

		try {
			// SQL DELETE 쿼리 작성
			String sql = "DELETE FROM mvcdbstudent WHERE id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, strId);
			int rowsAffected = pstmt.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("학생 정보 삭제 성공");
			} else {
				System.out.println("학생 정보 삭제 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

}
