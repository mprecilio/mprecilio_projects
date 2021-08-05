package example.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import example.controller.WorkerController;
import example.controller.HomeController;
import example.controller.LoginController;
import example.controller.VillainController;

public class Dispatcher {

	public static void myVirtualRouter(HttpServletRequest req, HttpServletResponse res)
			throws JsonProcessingException, IOException, ServletException {
		
		System.out.println("\n\n\t\tIn MyDispatcher (myVirtualRouter())");
		System.out.println("Current URL: "+req.getRequestURL());
		System.out.println("Current URI: "+req.getRequestURI());
		
		
		switch(req.getRequestURI()) {
		case "/SimilarToProject01/forwarding/login":
			System.out.println("case 1");
			LoginController.login(req, res);
			break;
			
		case "/SimilarToProject01/forwarding/home":
			System.out.println("case 2");
			HomeController.home(req, res);
			break;
			
		case "/SimilarToProject01/forwarding/home2":
			System.out.println("case 2");
			HomeController.home2(req, res);
			break;
		case "/SimilarToProject01/forwarding/filter":
			System.out.println("case 3");
			HomeController.home(req, res);
			break;	
			
		case "/SimilarToProject01/forwarding/pullrecords_filteredreq":	
			 System.out.println("You are in pulling filtered records");
			 WorkerController.get_all_records(req, res);
			 WorkerController.filter_all_records(req, res);
			 System.out.println("You are back from filtering records");
			 break;
		case "/SimilarToProject01/forwarding/pullrecords_req":
			System.out.println("case 2");
			WorkerController.get_all_records(req, res);
			System.out.println("You're back from pulling records");
			System.out.println("******************************");
			System.out.println("The current URI = " + req.getRequestURI());
			System.out.println("******************************");
			break;
		case  "/SimilarToProject01/forwarding/filter_records_by_status":	
			 WorkerController.get_all_records(req, res);
		     break;
		case "/SimilarToProject01/forwarding/insertrequest":
			System.out.println("You are in the case for inserting a reimbursement request.");
			WorkerController.insert(req, res);
			System.out.println("You are back from the insert call.");
			
			
			break;
		case "/SimilarToProject01/forwarding/status_request":
			
			 WorkerController.get_by_status(req, res);
			 break;
		case "/SimilarToProject01/forwarding/add_a_record":	
			System.out.println("You have the data to insert.");
			WorkerController.insert_a_record(req, res);
			System.out.println("You are back from the insert call.");
			break;
		case "/SimilarToProject01/forwarding/worker":
			System.out.println("case 3");
			WorkerController.worker(req, res);
			System.out.println("You're back from WorkerController");
			System.out.println("******************************");
			System.out.println("The current URI = " + req.getRequestURI());
			System.out.println("******************************");
			break;
		case "/SimilarToProject01/forwarding/approver":
			System.out.println("case 3");
			WorkerController.approver(req, res);
			System.out.println("You're back from WorkerController");
			System.out.println("******************************");
			System.out.println("The current URI = " + req.getRequestURI());
			System.out.println("******************************");
			break;	
			
		//case "/SimilarToProject01/json/allVills":
			//System.out.println("case 4");
			//VillainController.allFinder(req, res);
			//break;
		
		default:
			System.out.println("Dude, you gave me a bad URI.");
			req.getRequestDispatcher("/resources/html/badlogin.html").forward(req, res);
			return;
			
		}
	}
}
