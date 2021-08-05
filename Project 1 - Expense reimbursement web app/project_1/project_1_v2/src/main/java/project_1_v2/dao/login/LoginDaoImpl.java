package project_1_v2.dao.login;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import project_1_v2.dao.MyControllerFactory;
import project_1_v2.model.Account;
import project_1_v2.utilities.EventLogger;
import project_1_v2.utilities.ToEncrypted;

public class LoginDaoImpl implements LoginDao {

	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
//			System.out.println("Good luck now....");
		}
	}

	/*
	 * ********************Read methods***************************
	 * 
	 * @author Matthew Precilio
	 * 
	 * @param username and password from HttpServletRequest
	 * 
	 * @result 0 for manager, 1 if username matches an employee, null if invalid
	 */
	@Override
	public Account validLogin(String userWeb, String passWeb) {
//		System.out.println(userWeb + " " + passWeb);
		byte[] bytes = new byte[1];
		try (Connection conn = MyControllerFactory.getConnection()) {

			String sql = "SELECT ers_users_id, ers_users_username, ers_users_password, "
					+ "user_salt, user_first_name, user_last_name, user_email, eur.user_role " + "FROM ers_users eu "
					+ "INNER JOIN ers_user_roles eur " + "ON eu.user_role_id  = eur.ers_user_role_id "
					+ "WHERE ers_users_username = ?;";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userWeb);
			ResultSet rs = ps.executeQuery(); // <--query not update
			while (rs.next()) {
				int dbUserId = rs.getInt(1);
				String dbUser = rs.getString(2);
				String dbPass = rs.getString(3);
				byte salt = (byte) rs.getInt(4);
				bytes[0] = salt;
				String dbFName = rs.getString(5);
				String dbLName = rs.getString(6);
				String dbEmail = rs.getString(7);
				String dbRole = rs.getString(8);
				String passWebEnc = "";
				try {
					passWebEnc = ToEncrypted.generateHash(passWeb, bytes);
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
					EventLogger.loggy.error("A NoSuchAlgorithmException was thrown in LoginDaoImpl : validLogin: ", e);
				}
				if (dbPass.equals(passWebEnc)) {
					String logMsg = userWeb+" logged in.";
					System.out.println(logMsg);
					Account currAccount = new Account(dbUserId, dbUser, passWebEnc, bytes, dbFName, dbLName, dbEmail,
							dbRole);
//					System.out.println(currAccount.toString());
					return currAccount;
				} else {
					System.out.println("---------------LOGIN FAILED---------------");
					System.out.println(dbUser);
					System.out.println(dbPass);
					System.out.println(bytes[0]);
					return null;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			EventLogger.loggy.error("A SQL exception was thrown in LoginDaoImpl : validLogin: ", e);
		}

		return null;
	}

}
