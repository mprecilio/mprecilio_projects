package cc.myantics.dao.login;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import cc.myantics.GetUserPass;
import cc.myantics.ToEncrypted;

public class LoginDao implements LoginDaoInterface {

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

	/*
	 * @author Matthew Precilio
	 * 
	 * @param username and password from HttpServletRequest
	 * 
	 * @result 0 for manager, 1 if username matches an employee, null if invalid
	 */
	@Override
	public Integer validLogin(String userWeb, String passWeb) {
		byte[] bytes = new byte[1];
		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			String sql = "SELECT ers_users_username, ers_users_password, user_salt, user_role_id FROM ers_users WHERE ers_users_username=?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userWeb);
			ResultSet rs = ps.executeQuery(); // <--query not update
			while (rs.next()) {
				String dbUser = rs.getString(1);
				String dbPass = rs.getString(2);
				byte salt = (byte) rs.getInt(3);
				int role = rs.getInt(4);
				bytes[0] = salt;
				String passWebEnc = "";
				try {
					passWebEnc = ToEncrypted.generateHash(passWeb, bytes);
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (dbPass.equals(passWebEnc)) {
					System.out.println("Login success");
					String loggyLog = "Login by user: " + dbUser;
					GetUserPass.loggy.info(loggyLog);
					return role;
				} else {
					System.out.println("---------------LOGIN FAILED---------------");
					System.out.println(dbUser);
					System.out.println(dbPass);
					System.out.println(bytes[0]);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			GetUserPass.loggy.error("A SQL exception was thrown: ", e);
		}

		return null;
	}

}
