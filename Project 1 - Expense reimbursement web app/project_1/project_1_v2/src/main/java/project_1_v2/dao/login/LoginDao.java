package project_1_v2.dao.login;

import project_1_v2.model.Account;

public interface LoginDao {
	
	/*
	 * @author Matthew Precilio
	 * @param  username and password from HttpServletRequest
	 * @result 0 for manager, 1 if username matches an employee, null if invalid
	 */
	Account validLogin(String username, String password);

}
