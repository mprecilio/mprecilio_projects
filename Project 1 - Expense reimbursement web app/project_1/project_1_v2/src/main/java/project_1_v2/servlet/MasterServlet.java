package project_1_v2.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="MasterServlet", urlPatterns= {"/forwarding/*","/json/*"})
public class MasterServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException{
//		System.out.println("In MasterServlet doGet");
		Dispatcher.myVirtualRouter(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
//		System.out.println("In MasterServlet doPost");
		Dispatcher.myVirtualRouter(req, res);
	}

}
