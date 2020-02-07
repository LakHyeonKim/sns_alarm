package config;
/*
 * DB 서버 정보에 대한 상수 값을 저장
 */
public interface ServerInfo {
	//jdbc
	String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	String URL = "jdbc:mysql://34.80.99.159:3306/coco?serverTimezone=UTC&useUnicode=yes&caracterEncoding=UTF-8";
	String USER = "root";
	String PASS = "1234";
	
	
	String QUERY = "select idalarm, member_caller, member_receiver, post_id, like_id, follow_id, is_read, access"
			+ " from coco.alarm"
			+ " where member_receiver = ?";
	
	//alarm server
	int ALARM_SERVER_PORT = 4040; 
}
