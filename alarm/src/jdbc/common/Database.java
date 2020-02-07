package jdbc.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.ServerInfo;
import vo.Alarm;

/*
 * Data Access Object (DAO 클래스)
 * DB에 Access하는 로직만 모아놓은 클래스
 * DB에 Access하는 로직 = 비지니스 로직 (Business Logic)
 * ::
 * 비지니스 로직 메소드 마다
 * 1) 고정적인 부분이 반복
 * 		- Connection 반환 (서버 연결 시)
 * 		- 자원 반환 (close)
 * 2) 가변적인 부분이 반복
 * 
 */
public class Database {
	static {
		try {
			Class.forName(ServerInfo.DRIVER_NAME);
			System.out.println("Driver Loading...");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// 고정적인 부분
	public Connection getConnect() throws SQLException {
		Connection conn = DriverManager.getConnection(ServerInfo.URL, ServerInfo.USER, ServerInfo.PASS);
		System.out.println("Driver Loading ... ok");
		return conn;
	}

	public void closeAll(PreparedStatement ps, Connection conn) throws SQLException {
		if(ps != null) ps.close();
		if(conn != null) conn.close();
	}

	// 오버로딩
	public void closeAll(PreparedStatement ps, Connection conn, ResultSet rs) throws SQLException {
		if(ps != null) ps.close();
		if(conn != null) conn.close();
		if(rs != null) rs.close();
	}

	// 비지니스 로직 정의
	public ArrayList<Alarm> getAllAlarm(long idMember) throws SQLException { // SELECT
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Alarm> list = new ArrayList<>();

		try {
			conn = getConnect();
			ps = conn.prepareStatement(ServerInfo.QUERY);
			ps.setLong(1, idMember);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				list.add(new Alarm(rs.getLong("idalarm"), 
						rs.getLong("member_caller"), 
						rs.getLong("member_receiver"), 
						rs.getLong("post_id"), 
						rs.getLong("like_id"), 
						rs.getLong("follow_id"), 
						rs.getLong("is_read"), 
						rs.getLong("access")));
			}
		} finally {
			closeAll(ps, conn, rs);
		}
		return list;
	}
}
