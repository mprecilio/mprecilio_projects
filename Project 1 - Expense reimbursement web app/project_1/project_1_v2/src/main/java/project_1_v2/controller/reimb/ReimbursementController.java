package project_1_v2.controller.reimb;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import project_1_v2.dao.reimbursement.ReimbursementDaoImpl;
import project_1_v2.model.NewReimb;
import project_1_v2.model.Reimbursement;
import project_1_v2.service.ServiceReimbursements;

public class ReimbursementController {
	
	public static void reimbursement(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		String myPath = "/resources/html/reimbursements.html";
		
		req.getRequestDispatcher(myPath).forward(req, res);
	}
	
	
	
	public static void getAllUserReimbursements(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		String user = (String) req.getSession().getAttribute("logedInUsername");
		System.out.println(user);
		List<Reimbursement> allReimbs = new ReimbursementDaoImpl().getAllEmployeeReimbs(user);

		PrintWriter printer = res.getWriter();
		printer.write(new ObjectMapper().writeValueAsString(allReimbs));	
	}
	
	public static void getAllReimbursements(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		String user = (String) req.getSession().getAttribute("logedInUsername");
//		System.out.println(user);
		List<Reimbursement> allReimbs = new ServiceReimbursements().getAllReimbs();
		
		PrintWriter printer = res.getWriter();
		printer.write(new ObjectMapper().writeValueAsString(allReimbs));	
	}
	
	
	
	public static void newReimbRequest(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
//		System.out.println("In newReimbRequest.");
		
		ObjectMapper mapper = new ObjectMapper();
		Reimbursement myReimb = mapper.readValue(req.getInputStream(), Reimbursement.class);
		int author = (Integer) req.getSession().getAttribute("logedInUserId");
		String authorStr = "" + author;
		myReimb.setReimbAuthor(authorStr);
		
		new ServiceReimbursements().newReimb(myReimb);
		
//		String myPath = "/resources/html/reimbursements.html";
//		req.getRequestDispatcher(myPath).forward(req, res);		
	}
	
	public static void updateReimbStatus(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
//		System.out.println("In updateReimbStatus.");
		
		ObjectMapper mapper = new ObjectMapper();
		Reimbursement myReimb = mapper.readValue(req.getInputStream(), Reimbursement.class);
		int resolover = (Integer) req.getSession().getAttribute("logedInUserId");
		String resolverStr = "" + resolover;
		myReimb.setReimbResolver(resolverStr);
		new ServiceReimbursements().updateReimbStatus(myReimb);
		
//		System.out.println(myReimb.toString());
		
//		String myPath = "/resources/html/reimbursements.html";
//		req.getRequestDispatcher(myPath).forward(req, res);		
		
	}
	
}
