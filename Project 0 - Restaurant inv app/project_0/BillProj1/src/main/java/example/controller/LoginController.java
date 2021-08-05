package example.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import example.model.User_profile;
import example.dao.EmployeeServices;
import example.dao.CustomConnectionFactory;
public class LoginController {
	
	public static void login(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		String myPath = null;
		try {
			CustomConnectionFactory.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		if(!req.getMethod().equals("POST")){
			myPath = "/index.html";
			req.getRequestDispatcher(myPath).forward(req, res);
			return;
		}
		
		
		
		//extracting the form data
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		EmployeeServices bill = new EmployeeServices();
		User_profile person= new User_profile();
		person = bill.Login_to_AWS(username, password);
		System.out.println("The first name = " + person.user_first_name);
		System.out.println("The last name = " + person.user_last_name);
		System.out.println("The UserID = " + person.ers_user_id);
		System.out.println("Role = " + person.user_role_id);
		System.out.println("Username ="+ person.ers_username);
		
		//check to see if the user has the correct username & password
		//you'll actually go to th database to get the user's username and password
			 if (!person.user_first_name.equals("")) {
				 System.out.println("You have logged in as a requestor.");
				 /*
					 * you probably will have a user object that you put into the session
					 * that contains ther username & password and some other information....THIS is just an example
					 * 
					 * 
					 */
				 
				    User_profile temp_user = new User_profile();
				    temp_user.setErs_username(username);
				    req.getSession().setAttribute("current_user", temp_user);
					//req.getSession().setAttribute("loggedInUsername", username);
					//req.getSession().setAttribute("user_role", person.user_role_id);
					//req.getSession().setAttribute("userID", person.user_role_id);
					
					if (person.user_role_id == 1)
					  myPath = "/forwarding/worker";
					else
						myPath = "/forwarding/approver";
					req.getRequestDispatcher(myPath).forward(req, res);
					return;
				 
				 
			 }
			 else {
				 myPath = "/forwarding/incorrectcredentials";
				 req.getRequestDispatcher(myPath).forward(req, res);
					return;
			 }
		
		
		
		
	}

}
