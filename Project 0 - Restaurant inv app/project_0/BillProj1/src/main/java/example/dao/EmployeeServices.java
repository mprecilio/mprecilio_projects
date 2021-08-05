package example.dao;
import example.model.RequestRecords;
import example.model.User_profile;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.PrintWriter;
import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/*public class User_profile {

	public int ers_user_id;
	public int user_role_id;
	public String ers_username = new String();
	public String user_first_name = new String();
	public String user_last_name = new String();
}*/
public class EmployeeServices {

	String url = "jdbc:postgresql://datachan.cr57fg5lmu99.us-east-2.rds.amazonaws.com/Project1";
	 String username = "datachan";
	 String password = "p4ssw0rd";
	
	public  User_profile Login_to_AWS(String name, String pword) {
		 ResultSet rs;    // This will hold the items that need to be moved from one fridge to another fridge.
		 ResultSet rs2;   // This will hold the list of refrigs that can receive items.
		 String sql;
		 PreparedStatement ps;
		 int login_success =-5;
		 int person_in_system = 0;
		 
		 User_profile user = new User_profile();
		 try(Connection conn = DriverManager.getConnection(url,username,password)){
			 
			 sql = "select ers_user_id, ers_username, user_role_id, user_first_name, user_last_name  from ers_users eu where ers_username = '" + name + "' and ers_passwork = '" + pword +"'; ";
			 System.out.println("The query is :" + sql);
			 ps = conn.prepareStatement(sql);
			 rs = ps.executeQuery();
			 rs.next();
			 person_in_system = rs.getInt(1);
			 if (person_in_system  > 0) {
				 System.out.println("You successfully logged in!");
				 if (person_in_system == 1) {
					 System.out.println("You have logged in as a requestor.");
					 user.ers_user_id = rs.getInt(1);
					 user.user_role_id = rs.getInt(3);
					 user.ers_username = rs.getString(2);
					 user.user_first_name = rs.getString(4);
				     user.user_last_name  = rs.getString(5);
					 
				 }
				 else {
					 System.out.println("You have logged in as an approver. From the Login_to_AWS method.");
					 user.ers_user_id = rs.getInt(1);
					 user.user_role_id = rs.getInt(3);
					 user.ers_username = rs.getString(2);
					 user.user_first_name = rs.getString(4);
				     user.user_last_name  = rs.getString(5);
				 }
			 }
			 else {
				 login_success = 0;
				 System.out.println("You are not in the system.");
			 }
			 
		 }catch(SQLException e) {
		        e.printStackTrace();
		 }
		return user;
	
	  }	
	
	public ArrayList<RequestRecords> get_all_requests_for_person(String request_user) {
	   String sql;
	   PreparedStatement ps;
	   ResultSet rs; 
	   ArrayList<RequestRecords> records = new ArrayList<RequestRecords>();
	   
	   System.out.println("The value of request_user = " + request_user);
	   try(Connection conn = DriverManager.getConnection(url,username,password)){
		   
		   sql = "select user_role_id from ers_users " +  "where ers_username = '" + request_user + "';";
		   System.out.println("SQL statement = " + sql);
		   ps = conn.prepareStatement(sql);
		   rs = ps.executeQuery(); 
		   rs.next();
		   
		   if (rs.getInt(1) == 2)
			     request_user = "";
		   
		   if (request_user.equals("")) {
			   sql = "create table get_author_number as " +
					   "select b.ers_user_id, b.user_first_name, b.user_last_name,b.user_role_id from ers_users as b where user_role_id = 1;";
			   
			   
		   }
		   else
		   {
			   System.out.println("You're in the query you expect.");
		   sql = "create table get_author_number as " +
				   "select b.ers_user_id, b.user_first_name, b.user_last_name,b.user_role_id from ers_users as b "+
				   "where b.ers_username = '" + request_user + "';";
		   }
		   System.out.println("The query is :" + sql);
		   ps = conn.prepareStatement(sql);
		   ps.execute();
		   
		   sql = "create table get_requests as " +
			  " select a.remimb_id, a.reimb_amount, a.reimb_submitted, " +
			      "a.reimb_resolved, a.reimb_description, b.user_first_name, b.user_last_name, b.user_role_id, " +
			      "a.reimb_resolver, a.reimb_status_id, a.reimb_type_id " +
			      "from get_author_number as b left outer join ers_reimbursement as a on " +
			      "b.ers_user_id = a.reimb_author; ";
		   System.out.println("The query is :" + sql);
		   ps = conn.prepareStatement(sql);
		   ps.execute();
		   
		   sql = "create table final_return as " +
				  " select a.remimb_id, a.reimb_amount, a.reimb_submitted, " +
			      " a.reimb_resolved, a.reimb_description, a.user_first_name, a.user_last_name,b.user_first_name as user_first_name_r, " +
			      " b.user_last_name as user_last_name_r,a.reimb_status_id, a.reimb_type_id " +
			      " from get_requests as a left outer join ers_users as b on " +
			      " a.reimb_resolver = b.ers_user_id; ";
			   System.out.println("The query is :" + sql);
			   ps = conn.prepareStatement(sql);
			   ps.execute();
		   
			   sql = "create table get_code1 as " +
					 "  select a.*, b.REIMB_STATUS as reimbus_status, c.REIMB_TYPE as reimbus_type " +
					 "  from final_return as a left outer join ERS_REIMBURSEMENT_STATUS as b on " +
					 "  a.reimb_status_id = b.REIMB_STATUS_ID " +
					 "  left outer join ERS_REIMBURSEMENT_TYPE as c on " +
					 "  a.reimb_type_id = c.REIMB_TYPE_ID;";
			   
					   System.out.println("The query is :" + sql);
					   ps = conn.prepareStatement(sql);
					   ps.execute();   
		   
					   sql = "select * from get_code1;";
						  System.out.println("The query is :" + sql);
						   ps = conn.prepareStatement(sql);
						   rs=ps.executeQuery();   
						   
						   while(rs.next()) {
							   RequestRecords one_record = new RequestRecords();
							   one_record.request_Id = rs.getInt(1);
							   one_record.amount_requested = rs.getFloat(2);
							   System.out.println("Amount requested = " + one_record.amount_requested);
							   one_record.date_submitted = rs.getString(3);
							   one_record.date_resolved = rs.getString(4);
							   one_record.description = rs.getString(5);
							   one_record.first_name_r =rs.getString(6);
							   one_record.last_name_r =rs.getString(7);
							   one_record.first_name_a = rs.getString(8);
							   one_record.last_name_a = rs.getString(9);
							   one_record.status = rs.getString(10);
							   one_record.reimbursement_type = rs.getString(11);
							   records.add(one_record);		   
					   
						   }
						   
		    /* Delete the existing tables for the next run.*/	   
					   
		   sql = "drop table get_author_number;";
		   System.out.println("The query is :" + sql);
		   ps = conn.prepareStatement(sql);
		   ps.execute();
		   sql = "drop table get_requests;";
		   System.out.println("The query is :" + sql);
		   ps = conn.prepareStatement(sql);
		   ps.execute();
		   sql = "drop table final_return;";
		   System.out.println("The query is :" + sql);
		   ps = conn.prepareStatement(sql);
		   ps.execute();
		   sql = "drop table get_code1;";
		   System.out.println("The query is :" + sql);
		   ps = conn.prepareStatement(sql);
		   ps.execute();
		   
		   // Now I need to write out the arraylist to a json.
		   
		   
	   }catch(SQLException e) {
	        e.printStackTrace();
	 }
	   return records;
	}
	
		
	
	public void Add_a_requests_for_person(String req_name,String Amount, String Description, String Class_i_fication ) {
		String sql;
		   PreparedStatement ps;
		   ResultSet rs; 
		   ArrayList<RequestRecords> records = new ArrayList<RequestRecords>();
		
		 System.out.println("You are in the Add_a_request_for_person");
		 
		 try(Connection conn = DriverManager.getConnection(url,username,password)){
		 sql = "select ers_user_id from ers_users where ers_username = '" + req_name + "';";
		 ps = conn.prepareStatement(sql);
		 rs =  ps.executeQuery();
		 
		 rs.next();
		 
		 System.out.println("The returned value for ers_user_id =" + rs.getInt(1));
		 
		 sql = "insert into ers_reimbursement (reimb_amount,reimb_description,reimb_author,reimb_status_id, reimb_type_id )\n" +
				 "values(" +Amount + ",'" + Description +"'," + rs.getInt(1) + ", " + 3 + "," + Class_i_fication + ");";
				 ps = conn.prepareStatement(sql);
				 ps.execute();
		 
		 
		 }catch(SQLException e) {
		        e.printStackTrace();
		 }
		
		 
		//System.out.println(sql);
	}
	
	
	public ArrayList<RequestRecords> Filter_Records(int status_number){
		
		
		 ArrayList<RequestRecords> temp = new ArrayList<RequestRecords>();
		 ArrayList<RequestRecords> temp2 = new ArrayList<RequestRecords>();
		// temp = get_all_requests_for_person("","");
		 
		 
		 
		
		return temp2; 
	}
}  // This is the end of the class.
