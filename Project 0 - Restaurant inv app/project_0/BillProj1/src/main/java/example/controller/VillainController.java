package example.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import example.model.SuperVillain;
import example.service.SuperVillainService;
import example.service.SuperVillainServiceImpl;

public class VillainController {

	
	public static void allFinder(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JsonProcessingException {
		
		/*
		 * THIS IS WHERE YOU'D GO TO THE DATABASE TO GET THE OBJECTS TO SEND TO THE CLIENT
		 */

		SuperVillainService myServ = new SuperVillainServiceImpl();
		List<SuperVillain> myVillList = myServ.getAllVillains();
		System.out.println("here is the list: "+myVillList);
		
		/*
		 * in your project 1 you MAY already have the user's information in their session...no
		 * need to go to the database in that case
		 * You can just extract the reimbursments doing a
		 * HttpSession session = req.getSession();
		 * session.getAttribute(".....
		 */
		
		PrintWriter printer = res.getWriter();
		printer.write(new ObjectMapper().writeValueAsString(myVillList));
	}
}
