package example.controllers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import example.model.user.User;
import example.model.user.UserGender;
import example.service.post.PostService;
import example.service.user.UserService;
import example.utilities.RandomToken;
import example.utilities.SendingMail;
import example.utilities.ToEncrypted;


@RestController
@RequestMapping(value="/user")
@CrossOrigin(origins="http://localhost:3000/", allowCredentials="true")
public class UserController {
	private UserService us;
	private PostService ps;
	static private ObjectMapper mapper = new ObjectMapper();
	
	
	@Autowired
	public UserController(UserService us, PostService ps) {
			super();
			this.us = us;
			this.ps = ps;
		}
	
	private String username;
	
	final static Logger loggy = Logger.getLogger(UserController.class);
	
	{
		loggy.setLevel(Level.ALL);
	}
	////////////////////////////////////////////Create\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	/**
	 * @author Matthew
	 * 
	 * Accepts a HttpServlet request and response. It will pass the req and res to
	 * the service layer. If the registration is valid, it will create a session 
	 * for the user.
	 * 
	 * @param Accepts a HttpServlet request and response
	 * @return User object with all info
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@PostMapping(value="/register")
	public User controllerUserReg(HttpSession session, @RequestBody User currUser) throws JsonParseException, JsonMappingException, IOException {
		System.out.println(currUser);
		currUser.setProfilePhoto("Default.png");
		User validReg = us.serviceUserReg(currUser);
		System.out.println(validReg);
		validReg.setPassword(null);
		username = currUser.getUsername();
		
		loggy.info(validReg.getUsername() + " has created an account with Tangau!!!!");
		
		return validReg;
		//add session logic here
	}
	
	
	////////////////////////////////////////////Read\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	/**
	 * @author Matthew
	 * 
	 * Accepts a HttpServlet request and response. It will pass the req and res to
	 * the service layer. If the login is valid, it will create a session for the user.
	 * 
	 * @param Accepts a HttpServlet request and response
	 * @return void
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@PostMapping(value="/login")
	public User controllerUserLogin(HttpSession session, @RequestBody User currUser) throws JsonParseException, JsonMappingException, IOException {
//		User currUser = mapper.readValue(req.getInputStream(), User.class);
		System.out.println("----------------User login------------------");
		System.out.println(currUser.getUsername());
		User currUserWithInfo = us.serviceUserLogin(currUser);
		session.setAttribute("currUser", currUserWithInfo);
		
		if(!(currUserWithInfo==null)) {
			System.out.println(currUserWithInfo);
			currUserWithInfo.setPassword(null);
			username = currUserWithInfo.getUsername();
			
			
			loggy.info(currUserWithInfo.getUsername() + " has logged into Tangau!!!!");
		}
		
		else {
			System.out.println("Login failed");
			loggy.info(currUser.getUsername() + " failed to login!!!");
			currUserWithInfo.setPassword(null);
			
		}
		
		return currUserWithInfo;
	}
	
	
	
	/**
	 * @author Matthew
	 * 
	 * Gets a user based on a username input
	 * 
	 * @param username
	 * @return
	 */
	@GetMapping(value="/view-profile/{username}")
	public User getUser(@PathVariable(value="username") String username) {
		System.out.println("--------Searching for user----------");
		User seachUser = new User();
		seachUser.setUsername(username);
		User returnUser = us.searchUser(seachUser);
		User secondReturn = new User();
		secondReturn.setUsername("test1");
		if(returnUser==null)return secondReturn;
		System.out.println(returnUser);
		return returnUser;
		
	}
	
	
	
	////////////////////////////////////////////Update\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	/**
	 * @author Ernest
	 * 
	 * Accepts a HttpServlet request and response. It will pass the req and res to
	 * the service layer. If the login is valid, it will create a session for the user.
	 * 
	 * @param Accepts a HttpServlet request and response
	 * @return void
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@PutMapping(value="/update-info")
	public void controllerUserUpdate(@RequestBody User updateUser) throws JsonParseException, JsonMappingException, IOException {
		
		System.out.println(updateUser);
		System.out.println("------------------Trigerring Update-----------------------");
		User currUser = us.searchUser(updateUser);
		
		//initializing update string
		String fname = updateUser.getFname();
		String mname = updateUser.getMname();
		String lname = updateUser.getLname();
		UserGender gender = updateUser.getUserGender();
		
		//making changes to update values
		if(!fname.equals(currUser.getFname())) currUser.setFname(fname);
		if(!mname.equals(currUser.getMname())) currUser.setMname(mname);
		if(!lname.equals(currUser.getLname())) currUser.setLname(lname);
		if(!updateUser.getUserGender().getGender().equals(currUser.getUserGender().getGender())) currUser.setUserGender(gender);
		
		loggy.info(currUser.getUsername() + " has made changes to their Tangau profile!!!!");
		
		//sending to DAO layer
		User currUserWithInfo = us.serviceUserUpdate(currUser);
		
		System.out.println("updated user: " + currUserWithInfo);

	}
	
	
	///////////////////////////////////////////Forget Password\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	/**
	 * @author Ernest
	 * 
	 * Accepts a HttpServlet request and response. It will pass the req and res to
	 * the service layer. If the email is valid, it will send email to the user's email 
	 * containing a link to reset password.
	 * 
	 * @param Accepts a HttpServlet request and response
	 * @return void
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	
	@PostMapping(value="/send-email")
	public String controllerUserEmail(HttpSession session, @RequestBody User currUser) throws JsonParseException, JsonMappingException, IOException {
		
		///////////Getting Email from user
		System.out.println("----------------Sending Email------------------");
		System.out.println("this is email: " +  currUser.getUserEmail());
		User userWInfo = us.verifyingEmail(currUser);
		if(userWInfo == null) return null;
		
		////////Stores the token in a session
		RandomToken.randomToken(session);
		
		String token = (String)session.getAttribute("token_id");
		us.storeTokenEmail(userWInfo, token);
		session.setAttribute("email", currUser.getUserEmail());
		System.out.println("Here's the token!!!!!!!!!!! " + token);
		
		////////////sending Email
		try {
			//Passes email and the session's token to SendEmail class
			//this sends the reset password to the email + the token for security verification
			SendingMail.sendMail(userWInfo.getUserEmail(),token);
			
			loggy.info("A token has been sent to " + userWInfo.getUserEmail());
		} catch (MessagingException e) {
			System.out.println("Error occured");
			loggy.error("Error sending the mail (controllerUserEmail): ", e);
			e.printStackTrace();
		}
		
		
		return userWInfo.getUserEmail();
	}
	
	
	
	/*
	 * This method will finally reset the user's password.
	 * It takes the email stored inside the session, and creates a password received from the user
	 * This resets the password associated to the email it stored inside the session
	 */
	@PostMapping(value="/reset-pass")
	public String controllerPasswordReset(HttpSession session, @RequestBody User currUser) throws JsonParseException, JsonMappingException, IOException, NoSuchAlgorithmException {
		
		System.out.println("----------------Reset Password------------------");
		User userWInfo = us.verifyingEmail(currUser);
		String newHash = ToEncrypted.generateHash(currUser.getPassword(), userWInfo.getSalt());
		if (newHash.equals(userWInfo.getPassword())){ 
			System.out.println("this should be done again!!!!!!!!!!!");
			
			loggy.info("Password change was unsuccessful because user has the same password currently!!!");
			return "same password!!!!!!!!!!!!!!!";
		}
		if(!currUser.getToken().equals(userWInfo.getToken())) return null;
		System.out.println(currUser.getPassword());
		userWInfo.setPassword(newHash);
		//Goes to the service layer	and returns a condition
		User updatedUser = us.updateCurrUser(userWInfo);
		
		//Prints out condition returned from the dao layer
		System.out.println("here the return value: " + updatedUser);
		
		/*
		 * This is a little bit hardcoded
		 * It checks if the password received from the user is the same password currently in the database
		 */

		
		//updateds token in db to random token
		RandomToken.randomToken(session);
		String token = (String)session.getAttribute("token_id");
		us.storeTokenEmail(userWInfo, token);
		
		/*
		 * This invalidates the current session if the password was successfully changed
		 * And eventually removes the email from the session
		 */
		session.removeAttribute("email");
		session.removeAttribute("token");
		
		loggy.info("Password was successfully changed!!!");
		return "Password Changed!!!!!!!!!!!!!!!!!"; 
	}
	
	
	/////////////////////////////////////////////////////Sending Photo Key\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

	@GetMapping(value="/getphoto")
	public String controllerGetUserProfileKey(HttpServletRequest req, HttpServletResponse res) throws JsonParseException, JsonMappingException, IOException {

		System.out.println("--------------------------I am sending the key---------------------------------");

		String getKey = us.getUserProfile(new User(username));

		loggy.info(username + "'s profile picture key was successfully sent!!!");
		return getKey;
	}
	
	@GetMapping(value="/getphoto/{username}")
	public String controllerGetUserProfileKey(@PathVariable(value="username") String username) throws JsonParseException, JsonMappingException, IOException {

		System.out.println("--------------------------I am sending the key---------------------------------");

		String getKey = us.getUserProfile(new User(username));

		loggy.info(username + "'s profile picture key was successfully sent!!!");
		return getKey;
	}
	
	
	/////////////////////////////////////////////////////Receiving Photo Key\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	@PostMapping(value="/postphoto")
	public void controllerPostUserProfileKey(HttpServletRequest req, HttpServletResponse res) throws JsonParseException, JsonMappingException, IOException {

		System.out.println("--------------------------I am receiving the key---------------------------------");
		String key = mapper.readValue(req.getInputStream(), Object.class).toString();

		int length = key.length();
		key = key.substring(5, length-1);

		System.out.println(key);
		//taking session
		User currUser = new User(username,key); //= (User) req.getSession().getAttribute("currUser");

		User setKey = us.setUserProfile(currUser);
		
		loggy.info(username + " has successfully changed their profile picture!!!");

	}
	
	
	////////////////////////////////////////////Finding User\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	@PostMapping(value="/finduser")
	public User controllerindUser(HttpSession session, @RequestBody User currUser) throws JsonParseException, JsonMappingException, IOException {

		System.out.println("----------------Finding User------------------");
		System.out.println(currUser.getUsername());
		User currUserWithInfo = us.searchUser(currUser);
		System.out.println(currUserWithInfo);
		return currUserWithInfo;
	}
	
	
	////////////////////////////////////////////Delete\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	@DeleteMapping(value="/logout")
	public void controllerPasswordReset(HttpSession session) {
		System.out.println("------------Invalidating session-----------");
		session.invalidate();
		
		loggy.info("A user has successfully logged out of the website!!!");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	/*
	 * This will method will trigger when a user clicks on the link that was send to his or her email
	 * It will extract the email and token (from the url parameters)
	 * and store the email in a temporal session until the user resets 
	 * his or her password
	 * 
	 */
//	@GetMapping(value="/receiving-email/{email}/{token}")
//	public String getEmail(HttpSession session, @PathVariable ("email") String email, @PathVariable ("token") String token){
//				
//		////////////////////////Stores the token session
//		String verifyToken = (String)session.getAttribute("token_id");
//		
//		
//		/////////////////////Verifies the token RECEIVED FROM THE EMAIL link with the SESSION'S TOKEN
//		if(token.equals(verifyToken)) {
//			
//			
//			///////////////Removes the token from the session
//			session.removeAttribute("token");
//			
//			
//			/////////////Creates a session for the email
//			session.setAttribute("email", email);
//			
//			
//			///////////Returns the session's email
//			return email;
//			
//		}
//		
//		
//		///////////////Returns bad url if the token are MISMATCHED (OR NOT EQUAL)
//		return "Wrong URL!!!!";
//	}
}
