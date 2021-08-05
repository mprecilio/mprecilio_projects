package cc.myantics.dao.login;

public interface LoginDaoInterface {
	
	/*
	 * @author Matthew Precilio
	 * @param  username and password from HttpServletRequest
	 * @result 0 for manager, 1 if username matches an employee, null if invalid
	 */
	Integer validLogin(String username, String password);

}
