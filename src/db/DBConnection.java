package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	public Connection conn = null;

	public void newConnectDB() {
		try {

			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/staff", "postgres", "root");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void disconnectDB() throws Exception {
		conn.close();
	}

}
