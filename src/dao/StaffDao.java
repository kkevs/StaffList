package dao;

import java.util.List;

import org.primefaces.event.RowEditEvent;

import model.Staff;

public interface StaffDao {
	public void saveStaff(Staff staff) throws Exception;

	public void deleteStaff(Staff staff) throws Exception;

	public void clearAllStaff(Staff staff) throws Exception;

	public List<Staff> getAllStaffList() throws Exception;

	public void updateStaff(RowEditEvent event) throws Exception;
}
