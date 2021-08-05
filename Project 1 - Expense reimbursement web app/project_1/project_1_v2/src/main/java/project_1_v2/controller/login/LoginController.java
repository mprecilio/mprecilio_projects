package project_1_v2.controller.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import project_1_v2.dao.login.LoginDaoImpl;
import project_1_v2.model.Account;
import project_1_v2.service.ServiceLogins;
import project_1_v2.utilities.EventLogger;

public class LoginController {
	
	public static void isValidLogin(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		String myPath = null;
		HttpSession session = req.getSession(false);
		if (session == null) {
			if(!req.getMethod().equals("POST")) {
				myPath = "/index.html";
				req.getRequestDispatcher(myPath).forward(req, res);
				return;
			}
			Account currAccount = new ServiceLogins().validLogin(req.getParameter("username"), req.getParameter("password"));
			if(currAccount == null) {
				myPath = "/index.html";
				req.getRequestDispatcher(myPath).forward(req, res);
				return;
			}else {
//				System.out.println(myPath);
				req.getSession().setAttribute("logedInUserId", currAccount.getUserId());
				req.getSession().setAttribute("logedInUsername", currAccount.getUsername());
				req.getSession().setAttribute("loggedInRole", currAccount.getRole());
				myPath ="/forwarding/reimbursement";
//				System.out.println(myPath);
				String logMsg = currAccount.getUsername() +" logged in.";
				EventLogger.loggy.info(logMsg);
				req.getRequestDispatcher(myPath).forward(req, res);
				return;	
			}
			
		}else {
			myPath ="/forwarding/reimbursement";
			req.getRequestDispatcher(myPath).forward(req, res);
			return;	
			
		}

	}
	
	public static void getCurrentUser(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String userName = (String) req.getSession().getAttribute("logedInUsername");
		String userRole = (String) req.getSession().getAttribute("loggedInRole");
		Account myUser = new Account(userName,userRole);
		
		PrintWriter printer = res.getWriter();
		printer.write(new ObjectMapper().writeValueAsString(myUser));
	}

}
