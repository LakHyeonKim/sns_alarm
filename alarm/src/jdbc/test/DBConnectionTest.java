package jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import config.ServerInfo;

public class DBConnectionTest {

	public DBConnectionTest() {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			// 1. 서버 정보를 가지고 있는 드라이버를 클라이언트 메모리상에 로딩
			Class.forName(ServerInfo.DRIVER_NAME); // FQCN (fully qualified class name)
			System.out.println("! 드라이버 로딩 성공 !");

			// 2. DB 서버와 연결
			String url = ServerInfo.URL;
			String user = ServerInfo.USER;
			String pass = ServerInfo.PASS;

			conn = DriverManager.getConnection(url, user, pass);
			System.out.println("! DB 연결 성공 !");

		} catch (ClassNotFoundException e) {
			System.out.println("! 드라이버 로딩 실패 !");
		} catch (SQLException e) {
			System.out.println("! DB 연결 실패 !");
		} finally {
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	public static void main(String[] args) {
		new DBConnectionTest();
	}

}
