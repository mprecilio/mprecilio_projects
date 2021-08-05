package cc.myantics;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cc.myantics.dao.login.LoginDao;

public class GetUserPass extends HttpServlet{
	
	public static final Logger loggy = Logger.getLogger(GetUserPass.class);
	
	public static void main(String[] args) {
		String myString = "983529857";
//		String hashString = myString.
	}
	
	
	public void service(HttpServletRequest req, HttpServletResponse res) 
	{
		byte[] salt = ToEncrypted.createSalt();
				
		String user = req.getParameter("username");
		String pass = req.getParameter("password");
		String newPass = "";
		System.out.println(new LoginDao().validLogin(user, pass));
		
		try {
			newPass = ToEncrypted.generateHash(pass, salt);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.print("Username: \t\t" + user + "\nPassword: \t\t" + pass + "\nEncrypted Password:\t" + newPass + "\nSalt:                   ");
		for(byte temp : salt) {
			System.out.print(temp + " ");
		}
		System.out.println();
	}
}
