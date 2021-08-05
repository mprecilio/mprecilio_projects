package project_1_v2.controller.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import project_1_v2.model.Account;

public class GetCurrentUser {
	
	public static void getCurrentUser(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String userName = (String) req.getSession().getAttribute("logedInUsername");
		String userRole = (String) req.getSession().getAttribute("loggedInRole");
		Account myUser = new Account(userName,userRole);
		
		PrintWriter printer = res.getWriter();
		printer.write(new ObjectMapper().writeValueAsString(myUser));
	}

}
