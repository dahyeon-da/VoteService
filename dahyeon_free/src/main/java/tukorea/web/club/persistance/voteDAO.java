package tukorea.web.club.persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tukorea.web.club.domain.voteVO;

public class voteDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	String jdbc_driver = "com.mysql.cj.jdbc.Driver";
	String jdbc_url = "jdbc:mysql://localhost/jspdb?allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC";

	void connect() {
		try {
			Class.forName(jdbc_driver);
			conn = DriverManager.getConnection(jdbc_url, "", "");
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

	public boolean addVote(voteVO vo) {
		connect();

		// Get the next value for voteNum
		int nextVoteNum = maxVoteNum() + 1;

		String sql = "INSERT INTO dahyeon_free_vote (voteNum, voteTitle, voteContent) VALUES (?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nextVoteNum); // Set the next value of voteNum
			pstmt.setString(7, vo.getVoteTitle());
			pstmt.setString(2, vo.getVoteContent());
			pstmt.executeUpdate(); // Use executeUpdate for INSERT queries
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}
		return true;
	}

	public ArrayList<voteVO> getvoteList() {
		connect();
		ArrayList<voteVO> votelist = new ArrayList<voteVO>();
		String sql = "select * from dahyeon_free_vote";
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				voteVO vo = new voteVO();
				vo.setVoteNum(rs.getInt("voteNum"));
				vo.setVoteTitle(rs.getString("voteTitle"));
				vo.setVoteContent(rs.getString("voteContent"));
				vo.setMemberID(rs.getString("memberID"));
				vo.setVoteDate(rs.getDate("voteDate"));
				vo.setBadCount(rs.getInt("bad"));
				vo.setGoodCount(rs.getInt("good"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return votelist;
	}

	public voteVO voteRead(String memberID) {
		connect();

		voteVO voteupdate = new voteVO();

		String sql = "SELECT * FROM dahyeon_free_vote WHERE memberId = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(3, memberID);

			// 쿼리 실행
			ResultSet rs = pstmt.executeQuery();

			// 학생 정보 읽어오기
			voteupdate = extractmemberInfo(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 데이터베이스 연결 및 리소스 해제
			disconnect();
		}
		return voteupdate;
	}

	private voteVO extractmemberInfo(ResultSet rs) throws SQLException {
		voteVO voteupdate = new voteVO();

		if (rs.next()) {
			voteupdate.setVoteTitle(rs.getString("voteTitle"));
			voteupdate.setVoteContent(rs.getString("voteContent"));
			voteupdate.setMemberID(rs.getString("memberID"));
			voteupdate.setGoodCount(rs.getInt("good"));
			voteupdate.setBadCount(rs.getInt("bad"));
			voteupdate.setVoteDate(rs.getDate("voteDate"));
		}

		return voteupdate;
	}

	public boolean updateVote(voteVO vo) {
		connect();
		boolean success = false;

		try {
			String sql = "UPDATE dahyeon_free_vote SET voteTitle=?, voteContent=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(2, vo.getVoteContent());
			pstmt.setString(7, vo.getVoteTitle());

			int rowsAffected = pstmt.executeUpdate();

			if (rowsAffected > 0) {
				success = true;
				System.out.println("투표 정보 업데이트 성공");
			} else {
				System.out.println("투표 정보 업데이트 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return success;
	}

	public int maxVoteNum() {
		connect();

		try {
			String sql = "SELECT max(voteNum) FROM dahyeon_free_vote";
			pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				return rs.getInt(1);
			} else {
				return -1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			disconnect();
		}
	}
}
