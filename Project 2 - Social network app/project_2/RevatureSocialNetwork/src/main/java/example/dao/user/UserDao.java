package example.dao.user;

import java.security.NoSuchAlgorithmException;

import example.model.user.User;

public interface UserDao {
	
	////////////////////////////////////////////Create\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	/**
	 * @author 
	 * 
	 * Will check if the username and email is in use. If the username and email is not in use, will create a new account
	 * with the provided information
	 * 
	 * ***NOTE: all usernames should be stored in the database as LOWER CASER***
	 * 
	 * @param
	 * @return New user object will all info
	 */
	User userReg(User newUser);
	
	
	////////////////////////////////////////////Read\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	/**
	 * @author 
	 * 
	 * This is used for login. Checks if the login is valid
	 * 
	 * @param User object containing only username and pass
	 * @return User object containing all user info
	 * @throws NoSuchAlgorithmException 
	 */
	
	User userLogin(User currUser);
	
	
	/**
	 * @author 
	 * 
	 * This will fetch the profile info of current or another user.
	 * Used for the 'view others profile' feature.
	 * 
	 * @param User object
	 * @return User object - contains the User info (without sensitive information) of the 
	 * 						 profile you would like to view
	 */
	User userViewProfile(User userProfile);
	
	/**
	 * @author Matthew
	 * 
	 * gets user by username
	 * 
	 * 
	 * @param currUser
	 * @return
	 */
	User findUserTwo(User currUser);
	
	
	
	////////////////////////////////////////////Update\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	/**
	 * @author Ernest
	 * 
	 * This will update a user's profile info
	 * 
	 * @param User object
	 * @return boolean - true for success, false for fail (this is mostly for testing)
	 */
	
	User userUpdate(User currUser);
	
	/**
	 * @author Matthew
	 * 
	 * Adds token to user table
	 * 
	 * @param userWInfo
	 * @param token
	 */
	void storeToken(User userWInfo, String token);
	
	/**
	 * @author Matthew
	 * 
	 * updates user info
	 * 
	 * @param userWInfo
	 * @return
	 */
	User updateCurrUser(User userWInfo);

	
	
	////////////////////////////////////////////Reset Password\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	/**
	 * @author Ernest
	 * 
	 * This will reset a user's password.
	 * 
	 * @param User object
	 * @return String - "Password changed!" for success, "Same Password!" for fail (this is mostly for testing)
	 */
	
	
	User verifyingEmail(User currUser);
	String resetPassword(User currUser);


	////////////////////////////////////////////Profile Photo\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	/**
	 * @author Ernest
	 * 
	 *         This will get and return profile photo.
	 * 
	 * @param User object
	 * @return String - "Photo" for success, "null" for fail
	 */
	User setUserProfile(User currUser);

	String getUserProfile(User currUser);
	
	
	/**
	 * @author Ernest
	 * 
	 * This will find a username
	 * 
	 * @param currUser
	 * @return
	 */
	//////////////////////////////////////////// Find User////////////////////////////////////////////
	public User findUser(User currUser);
}
