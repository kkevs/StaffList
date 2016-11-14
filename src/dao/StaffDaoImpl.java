package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.event.RowEditEvent;

import db.DBConnection;
import model.Staff;

public class StaffDaoImpl extends DBConnection implements StaffDao {

	@Override
	public List<Staff> getAllStaffList() throws Exception {
		List<Staff> staffList = new ArrayList<Staff>();
		newConnectDB();
		String sql = "SELECT * FROM tbl_staff ORDER BY id DESC";
		PreparedStatement psmt = conn.prepareStatement(sql);
		ResultSet resultSet = psmt.executeQuery();
		Staff staff;
		while (resultSet.next()) {
			staff = new Staff();
			staff.setId(resultSet.getString("id"));
			staff.setName(resultSet.getString("name"));
			staff.setSurname(resultSet.getString("surname"));
			staff.setAge(resultSet.getInt("age"));
			staff.setCity(resultSet.getString("city"));
			staffList.add(staff);
			disconnectDB();
		}

		return staffList;
	}

	@Override
	public void saveStaff(Staff staff) throws Exception {
		newConnectDB();
		String sql = "INSERT INTO tbl_staff(name,surname,age,city) VALUES(?,?,?,?)";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, staff.getName());
		psmt.setString(2, staff.getSurname());
		psmt.setInt(3, staff.getAge());
		psmt.setString(4, staff.getCity());
		psmt.executeUpdate();
		disconnectDB();

	}

	@Override
	public void clearAllStaff(Staff staff) throws Exception {
		newConnectDB();
		String sql = "DELETE FROM tbl_staff";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.executeUpdate();
		disconnectDB();
	}

	@Override
	public void updateStaff(RowEditEvent event) throws Exception {
		Staff staff = (Staff) event.getObject();
		newConnectDB();
		String sql = "UPDATE tbl_staff SET name=?, surname=?, age=?, city=? where id=?";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, staff.getName());
		psmt.setString(2, staff.getSurname());
		psmt.setInt(3, staff.getAge());
		psmt.setString(4, staff.getCity());
		psmt.setInt(5, Integer.valueOf(staff.getId()));
		psmt.executeUpdate();
		disconnectDB();
	}

	@Override
	public void deleteStaff(Staff staff) throws Exception {
		newConnectDB();
		String sql = "DELETE FROM tbl_staff where id=?";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setInt(1, Integer.valueOf(staff.getId()));
		psmt.executeUpdate();
		disconnectDB();
	}

}
