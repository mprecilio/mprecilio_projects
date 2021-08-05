package project_1_v2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyControllerFactory {
	
	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Good luck now....");
		}
	}

	public static String url = "jdbc:postgresql://revdb.cf8zdqrcckgg.us-east-2.rds.amazonaws.com/expense_reimbursement";
	public static String username = "revdb";
	public static String password = "iZ074B5Lxw1r";

	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url, username, password);
	}

}
