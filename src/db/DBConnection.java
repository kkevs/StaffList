package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	public static Connection conn=null;

	public static Connection newConnectDB() {
		try {

			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/staff", "postgres", "root");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void disconnectDB() throws Exception {
		conn.close();
	}

}
