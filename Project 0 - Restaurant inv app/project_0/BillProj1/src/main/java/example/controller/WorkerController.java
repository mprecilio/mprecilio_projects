package example.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import example.model.RequestRecords;
import example.model.User_profile;
import example.dao.EmployeeServices;
import example.dao.CustomConnectionFactory;






public class WorkerController{
	
	public static void worker(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		String myPath = "/resources/html/worker.html";
		
		req.getRequestDispatcher(myPath).forward(req, res);
	}
	
	public static void approver(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		String myPath = "/resources/html/approver.html";
		
		req.getRequestDispatcher(myPath).forward(req, res);
	}
	
	public static void get_by_status(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		String myPath = "/resources/html/select_status.html";
		
		req.getRequestDispatcher(myPath).forward(req, res);
	}
	
	
	public static void insert(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
        String myPath = "/resources/html/insert_into_table.html";
		
		req.getRequestDispatcher(myPath).forward(req, res);
	
	}
	
	public static void insert_a_record(HttpServletRequest req, HttpServletResponse res)
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
		
		String username = req.getParameter("username");
		String Amount = req.getParameter("Amount");
		String Description = req.getParameter("Description");
		String Class_i_fication = req.getParameter("Class_i_fication");
		
		System.out.println("The user name = " + username);
		System.out.println("The amount entered was " + Amount);
		System.out.println("The reason for spending was = " + Description);
		System.out.println("The classification code =" + Class_i_fication);
		
		EmployeeServices bill = new EmployeeServices();
		
		bill.Add_a_requests_for_person(username,Amount, Description,Class_i_fication );
	}
	
	
	
	
	
	public static void get_all_records(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		String username = new String();
		System.out.println("You are in the get_all_records.");
		String myPath = null;
		try {
			CustomConnectionFactory.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpSession session = req.getSession();
       // username =(String)session.getAttribute("loggedInUsername");
		User_profile my_user = (User_profile) session.getAttribute("current_user");
		
		System.out.println("Username = "+ my_user.ers_username);
		EmployeeServices bill = new EmployeeServices();
		//RequestRecords records = new RequestRecords();
		ArrayList<RequestRecords> all_records = new ArrayList<RequestRecords>();
		all_records = bill.get_all_requests_for_person(my_user.ers_username);
		
		
		PrintWriter printer = res.getWriter();
		printer.write(new ObjectMapper().writeValueAsString(all_records));
		
//		myPath = "/resources/html/home.html";
//		System.out.println("You are going to the home.html page.");
//		
//		req.getRequestDispatcher(myPath).forward(req, res);
	}
	
	public static void filter_all_records(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		String username = new String();
		System.out.println("You are in the filter_get_all_records.");
		String myPath = null;
		try {
			CustomConnectionFactory.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpSession session = req.getSession();
	       // username =(String)session.getAttribute("loggedInUsername");
		User_profile my_user = (User_profile) session.getAttribute("current_user");
		
		ObjectMapper mapper = new ObjectMapper();
		String Status  = mapper.readValue(req.getInputStream(), String.class);
		

		
		
		
		//String Status = req.getParameter("Status");
		System.out.println("*********************status selected = "+ Status);
		EmployeeServices bill = new EmployeeServices();
		//RequestRecords records = new RequestRecords();
		ArrayList<RequestRecords> all_records = new ArrayList<RequestRecords>();
		all_records = bill.get_all_requests_for_person(my_user.ers_username);
		/* At this point, we have ALL the non-manager records.*/
		
		System.out.println(all_records.toString());
		
		int k = 0;
		while (k < all_records.size()) {
			RequestRecords temp = new RequestRecords();
			String status_code = new String();
			temp = all_records.get(k);
			System.out.println("Here is the value of Status =" + temp.status);
			k = k + 1;
			
		}
		
		
		//PrintWriter printer = res.getWriter();
		//printer.write(new ObjectMapper().writeValueAsString(all_records));
		
//		myPath = "/resources/html/home.html";
//		System.out.println("You are going to the home.html page.");
//		
//		req.getRequestDispatcher(myPath).forward(req, res);
	}
	
	
}