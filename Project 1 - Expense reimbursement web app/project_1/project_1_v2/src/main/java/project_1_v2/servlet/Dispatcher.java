package project_1_v2.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project_1_v2.controller.login.LoginController;
import project_1_v2.controller.reimb.ReimbursementController;
import project_1_v2.utilities.EventLogger;

public class Dispatcher {
	
	public static void myVirtualRouter(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException{
		
		
		switch(req.getRequestURI()) {
		case "/project_1_v2/forwarding/login":
//			System.out.println("Case forwarding.");
			LoginController.isValidLogin(req, res);
			break;
		case "/project_1_v2/forwarding/reimbursement":
//			System.out.println("Case reimbursement.");
			ReimbursementController.reimbursement(req, res);
			break;
		case "/project_1_v2/forwarding/reimb_request":
//			System.out.println("Case create new reimbursement.");
			ReimbursementController.newReimbRequest(req, res);
			break;
		case "/project_1_v2/forwarding/update_reimb_status":
//			System.out.println("Case create new reimbursement.");
			ReimbursementController.updateReimbStatus(req, res);
			break;
		case "/project_1_v2/json/reimbursements":
//			System.out.println("Case manager.");
			ReimbursementController.getAllUserReimbursements(req, res);
			break;
		case "/project_1_v2/json/managerReimbursements":
//			System.out.println("Case manager.");
			ReimbursementController.getAllReimbursements(req, res);
			break;
		case "/project_1_v2/json/curr_user":
//			System.out.println("Getting current user");
			LoginController.getCurrentUser(req, res);
			break;
		case "/project_1_v2/forwarding/signout":
			System.out.println("Terminating session");
			String userName = (String) req.getSession().getAttribute("logedInUsername");
			String logMsg = userName +" logged out.";
			EventLogger.loggy.info(logMsg);
			req.getSession().invalidate();
			break;
		default:
			System.out.println(req.getRequestURI());
			System.out.println("Bad url address.");
			break;
			
		}
		
	}
}
