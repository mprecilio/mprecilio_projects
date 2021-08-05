package example.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * for the urlPatterns...not only can we have multiple uris that trigger this servlet...we can also use
 * a wildcard to take in an infinite amount of endpoints
 */
@WebServlet(name="MasterServlet", urlPatterns= {"/master/*", "/forwarding/*", "/json/*", "*.chester"})
public class MasterServlet extends HttpServlet {

	
	/*
	 * THE PURPOSE OF THE FRONT CONTROLLER WILL BE TO IMPLEMENT
	 * ANY AUTHENTIFICATION LOGIC YOU MAY HAVE.
	 * 
	 * It modularizes your security.
	 */
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		System.out.println("MASTER SERVLET: In the doGet method!!!");
		System.out.println("The current URI = " + req.getRequestURI());
		Dispatcher.myVirtualRouter(req, resp); //HERE I am offloading my work to another entity
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		System.out.println("MASTER SERVLET: In the doPost method!!!");
		
		doGet(req, resp);
	}
}
