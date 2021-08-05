package example.service.user;

import example.model.user.User;

public interface UserService {
	
	////////////////////////////////////////////Create\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	/**
	 * @author Matthew
	 * 
	 * Accepts User and passes it to the dao layer to validate registration
	 * 
	 * @param Accepts user object input
	 * @return User object; null if invalid reg
	 */
	User serviceUserReg(User currUser);
	
	
	////////////////////////////////////////////Read\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	/**
	 * @author Matthew
	 * 
	 * Accepts User and passes it to the dao layer to validate login
	 * 
	 * @param Accepts user object input
	 * @return User object: Null if invalid login, full if valid login 
	 */
	User serviceUserLogin(User currUser);
	
	/**
	 * @author Ernest
	 * 
	 * Finds a user by username
	 * 
	 * @param currUser
	 * @return
	 */
	public User searchUser(User currUser);
	
	
	/**
	 * @author Matthew
	 * 
	 * Finds a user by username
	 * 
	 * @param currUser
	 * @return
	 */
	public User searchUserTwo(User currUser);
	
	
	
	////////////////////////////////////////////Update\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	/**
	 * @author Ernest
	 * 
	 * Accepts User and passes it to the dao layer to validate login
	 * 
	 * @param Accepts user object input
	 * @return User object: Null if invalid login, full if valid login 
	 */
	User serviceUserUpdate(User currUser);
	
	/**
	 * @author Matthew Precilio
	 * 
	 * Saves token to userWInfo in db
	 * 
	 * @param userWInfo
	 * @param token
	 */
	void storeTokenEmail(User userWInfo, String token);
	
	/**
	 * @author Matthew
	 * 
	 * Updates info of curr user
	 * 
	 * @param userWInfo
	 * @return
	 */
	User updateCurrUser(User userWInfo);
	
	
	/**
	 * @author Ernest
	 * 
	 * Resets a user's password
	 * 
	 * @param Accepts user object input
	 * @return User object: Null if invalid login, full if valid login 
	 */
	String resetPassword(User currUser);

	/**
	 * @author Ernest
	 * 
	 * Calls dao layer to verify that email exists
	 * 
	 * 
	 * @param currUser
	 * @return
	 */
	User verifyingEmail(User currUser);


	////////////////////////////////////////////Profile Photo\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	/**
	 * @author Ernest
	 * 
	 *         Set and Finds a user's profile photo
	 * 
	 * @param Accepts user object input
	 * @return String : null if null, photo if photo key is present
	 */
	User setUserProfile(User currUser);
	String getUserProfile(User currUser);


}
