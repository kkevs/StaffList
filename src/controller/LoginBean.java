package controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import dao.LoginDao;
import model.Login;

@ManagedBean
@SessionScoped
public class LoginBean {

	Login login = new Login();

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	// validate login
	public String validateUsernamePassword() throws Exception {
		boolean valid = LoginDao.validate(login);
		if (valid) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", login.getUser_name());
			return "staffList?faces-redirect=true";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Incorrect Username and Passowrd", "Please enter correct username and Password"));
			return "login?faces-redirect=true";
		}

	}

	// logout event, invalidate session
	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "/login.xhtml?faces-redirect=true";
	}
}
