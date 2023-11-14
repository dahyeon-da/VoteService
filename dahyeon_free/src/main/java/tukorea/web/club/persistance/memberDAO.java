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
		String sql = "insert into dahyeon_free_member values (?, ?, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPasswd());
			pstmt.setString(3, vo.getUsername());
			pstmt.setString(4, vo.getNickname());
			pstmt.setString(5, vo.getMobile());
			pstmt.setString(6, vo.getEmail());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}
		return true;
	}

	public ArrayList<memberVO> getmemberList() {
		connect();
		ArrayList<memberVO> memberlist = new ArrayList<memberVO>();
		String sql = "select * from dahyeon_free_mamber";
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				memberVO vo = new memberVO();
				vo.setId(rs.getString("id"));
				vo.setPasswd(rs.getString("passwd"));
				vo.setUsername(rs.getString("username"));
				vo.setNickname(rs.getString("nickname"));
				vo.setMobile(rs.getString("mobile"));
				vo.setEmail(rs.getString("email"));
				memberlist.add(vo);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return memberlist;
	}

	public memberVO read(String studentID) {
	    connect();
	    
	    memberVO studentupdate = new memberVO();

	    // SQL 쿼리 작성
	    String sql = "SELECT * FROM dahyeon_free_member WHERE id = ?";

	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, studentID);

	        // 쿼리 실행
	        ResultSet rs = pstmt.executeQuery();

	        // 학생 정보 읽어오기
	        studentupdate = extractmemberInfo(rs);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // 데이터베이스 연결 및 리소스 해제
	        disconnect();
	    }
	    return studentupdate;
	}

	private memberVO extractmemberInfo(ResultSet rs) throws SQLException {
		memberVO memberupdate = new memberVO();

	    if (rs.next()) {
	    	memberupdate.setId(rs.getString("id"));
	    	memberupdate.setPasswd(rs.getString("passwd"));
	    	memberupdate.setUsername(rs.getString("username"));
	    	memberupdate.setNickname(rs.getString("nickname"));
	    	memberupdate.setMobile(rs.getString("mobile"));
	    	memberupdate.setEmail(rs.getString("email"));
	    }

	    return memberupdate;
	}
	
	public boolean update(memberVO memberVO) {
	    connect();
	    boolean success = false;

	    try {
	        // SQL UPDATE 쿼리 작성
	        String sql = "UPDATE dahyeon_free_member SET passwd=?, username=?, nickname=?, mobile=?, email=? WHERE id=?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, memberVO.getPasswd());
	        pstmt.setString(2, memberVO.getUsername());
	        pstmt.setString(3, memberVO.getNickname());
	        pstmt.setString(4, memberVO.getMobile());
	        pstmt.setString(5, memberVO.getEmail());
	        pstmt.setString(6, memberVO.getId());

	        // UPDATE 쿼리 실행
	        int rowsAffected = pstmt.executeUpdate();

	        if (rowsAffected > 0) {
	            success = true;
	            System.out.println("회원 정보 업데이트 성공");
	        } else {
	            System.out.println("회원 정보 업데이트 실패");
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
			String sql = "DELETE FROM dahyeon_free_member WHERE id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, strId);
			int rowsAffected = pstmt.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("회원 정보 삭제 성공");
			} else {
				System.out.println("회원 정보 삭제 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

}
