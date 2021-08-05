package project_1_v2.service;

import project_1_v2.dao.login.LoginDaoImpl;
import project_1_v2.model.Account;

public class ServiceLogins {
	
	public Account validLogin(String username, String password) {
		System.out.println("In login service layer");
		return new LoginDaoImpl().validLogin(username, password);
	}

}
