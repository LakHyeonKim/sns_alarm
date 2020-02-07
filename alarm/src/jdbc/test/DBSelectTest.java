package jdbc.test;

import java.sql.SQLException;
import java.util.List;

import jdbc.common.Database;
import vo.Alarm;

public class DBSelectTest {

	public static void main(String[] args) throws SQLException {
		Database database = new Database();
		printList(database.getAllAlarm(5L));
	}
	
	private static void printList(List<Alarm> alarms) {
		for(Alarm alarm : alarms) {
			System.out.println(alarm);
		}
		System.out.println();
	}

}
