package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBConnection;
import model.Login;

public class LoginDao extends DBConnection {
	public static boolean validate(Login login) throws Exception {
		try {
			newConnectDB();
			String sql = "Select user_name, user_psw from tbl_user where user_name = ? and user_psw = ?";
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, login.getUser_name());
			psmt.setString(2, login.getUser_psw());
			ResultSet resultSet = psmt.executeQuery();
			
			if (resultSet.next()) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Login error -->" + e.getMessage());
			return false;
		} finally {
			disconnectDB();
		}

		return false;
	}

}
