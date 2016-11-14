package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.primefaces.event.RowEditEvent;

import dao.StaffDaoImpl;
import model.Staff;

@ManagedBean(name = "manager")
@SessionScoped
public class PropertyManager {

	
	
	
	//Codes
	List<Staff> cacheList;
	private Staff staff = new Staff();
	StaffDaoImpl staffDaoImpl = new StaffDaoImpl();
	private Staff selectedStaff;

	public PropertyManager() throws Exception {
		cacheList = new ArrayList<Staff>();
		cacheList = staffDaoImpl.getAllStaffList();
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public List<Staff> getCacheList() {
		return cacheList;
	}

	public void setCacheList(List<Staff> cacheList) {
		this.cacheList = cacheList;
	}

	public Staff getSelectedStaff() {
		return selectedStaff;
	}

	public void setSelectedStaff(Staff selectedStaff) {
		this.selectedStaff = selectedStaff;
	}

	public void save() throws Exception {
		cacheList.add(staff);
		staffDaoImpl.saveStaff(staff);
	}

	public void clear() throws Exception {
		cacheList.clear();
		staffDaoImpl.clearAllStaff(staff);
	}

	public void deleteStaff() throws Exception {
		System.out.println("asda");
		staffDaoImpl.deleteStaff(selectedStaff);
		selectedStaff = null;
		cacheList = staffDaoImpl.getAllStaffList();
	}

	public void onRowEdit(RowEditEvent event) throws Exception {
		System.out.println("asdasd");
		staffDaoImpl.updateStaff(event);
		FacesMessage msg = new FacesMessage("Staff Edited", ((Staff) event.getObject()).getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) throws Exception {
		FacesMessage msg = new FacesMessage("Staff Canceled", ((Staff) event.getObject()).getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void postProcessXLS(Object document) {
		HSSFWorkbook wb = (HSSFWorkbook) document;
		HSSFSheet sheet = wb.getSheetAt(0);
		CellStyle style = wb.createCellStyle();
		style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());

		for (Row row : sheet) {
			for (Cell cell : row) {
				cell.setCellValue(cell.getStringCellValue().toUpperCase());
				cell.setCellStyle(style);
			}
		}
	}

}