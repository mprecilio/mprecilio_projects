package part1.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import part1.accounts.Accounts;
import part1.accounts.HealthInspector;
import part1.accounts.Restaurant;
import part1.fridge.Fridge;

/*
 * NOTE: JDBC's default is auto-commit
 * 
 * Common errors you may have to JDBC
 * 
 * 
 * "No suitable driver found"
 * the two common reasons why you'll see this are:
 *    -you forgot to add the dependency to your pom.xml file
 *    -your url has a typo
 */
public class PersistJDBC {

	/*
	 * JDBC needs a SPECIFIC format to the URL string so that the DriverManager can
	 * understand WHICH driver you're asking to use.
	 * 
	 * For Postgresql, the URL format is:
	 * jdbc:postgresql://[endpoint]/[databasename]
	 * 
	 * For OracleSQL, the URL format is:
	 * jdbc:oracle:thin:@[endpoint]:[port]:[dbname]
	 * 
	 */
	public static String url = "jdbc:postgresql://revdb.cf8zdqrcckgg.us-east-2.rds.amazonaws.com/project0";
	public static String username = "revdb";
	public static String password = "iZ074B5Lxw1r";
	final static Logger loggy = Logger.getLogger(PersistJDBC.class);
	
	{
		loggy.setLevel(Level.ALL);
	}


	/////////////////////////////////////////////////
	////////////// MAIN METHOD////////////////////////
	public static void main(String[] args) {
		// statementExample(1050, "Space Monkey", "fighting", "ice");
		// preparedStatementExample(10520, "Mudkip", "water", null);
		// preparedStatementPrimaryKeyExample(1075, "Picturemon", "wood", "ice");
		// newRestaurant("MatthewP", "1234");
		// newHealthInspector("testingtesing", "1234");
		// newFridge("MatthewP");
		//addFood("Apple", 1);
		//newHealthInspectorFridge("testingtesing", 1);
		PersistData.accountList.addAll(PersistJDBC.selectAllRestaurants());
		PersistData.accountList.addAll(PersistJDBC.selectAllHealthInspectors());
		PersistData.fridgeList.addAll(PersistJDBC.selectAllFridges());
		PersistJDBC.selectAllFood();
		PersistJDBC.selectAllHealthInspectorFridges();
		transferFood("Apples", 2, 3);
	}

//	////////////////////////////////////////////////////////////////
//	////////////MY METHODS THAT USE JDBC////////////////////////////
//	
	//// PREPARED STATEMENT
	public static void newRestaurant(String user, String pass) {

		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			String sql = "INSERT INTO Restaurant VALUES(?,?)";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user); // the 1st "?"
			ps.setString(2, pass); // the 2nd "?"

			ps.executeUpdate(); // THIS line is what sends the information to the DB
			loggy.info("New restaurant account created with the username: " + user);

		} catch (SQLException e) {
			e.printStackTrace();
			loggy.error("A SQL exception was thrown: ", e);
		}
	}

	public static void newHealthInspector(String user, String pass) {

		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			String sql = "INSERT INTO health_inspector VALUES(?,?)";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user); // the 1st "?"
			ps.setString(2, pass); // the 2nd "?"

			ps.executeUpdate(); // THIS line is what sends the information to the DB
			loggy.info("New health inspector account created with the username: " + user);

		} catch (SQLException e) {
			e.printStackTrace();
			loggy.error("A SQL exception was thrown: ", e);
		}
	}

	public static void newHealthInspectorFridge(String health_inspector_username, int fridge_id) {
		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			String sql = "INSERT INTO health_inspector_fridges VALUES(?,?)";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, health_inspector_username); // the 1st "?"
			ps.setInt(2, fridge_id); // the 2nd "?"

			ps.executeUpdate(); // THIS line is what sends the information to the DB
			loggy.info("Health inspector " + health_inspector_username + " was given access to fridge: " + fridge_id);
			
		} catch (SQLException e) {
			e.printStackTrace();
			loggy.error("A SQL exception was thrown: ", e);
		}
	}
	
	//// PREPARED STATEMENT W/ PK return
	public static int newFridge(String owner) {

		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			String sql = "INSERT INTO fridge VALUES(DEFAULT, ?)";

			/*
			 * Prepared Statements are precompiled. Because of this the prepared statement
			 * will guard against sql injection AND it is faster
			 */
			// necessary to return the PK
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, owner); // the 1st "?"

			ps.executeUpdate(); // THIS line is what sends the information to the DB

			// this section is necessary to return the PK of the inserted record
			ResultSet rs = ps.getGeneratedKeys();
			int fridgeId = -1;
			if (rs.next()) {
				fridgeId = rs.getInt("fridge_id");
			}
			loggy.info("New fridge account created by: " + owner);
			return fridgeId;
			//////

		} catch (SQLException e) {
			e.printStackTrace();
			loggy.error("A SQL exception was thrown: ", e);
		}
		return -1;
	}

	public static void addFood(String food, int fridge_id) {

		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			String sql = "INSERT INTO food VALUES(DEFAULT,?,?)";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, food); // the 1st "?"
			ps.setInt(2, fridge_id); // the 2nd "?"

			ps.executeUpdate(); // THIS line is what sends the information to the DB
			loggy.info(food + " was added to fridge: " + fridge_id);

		} catch (SQLException e) {
			e.printStackTrace();
			loggy.error("A SQL exception was thrown: ", e);
		}
	}

	//// SELECT * FROM TABLE example
	public static List<Accounts> selectAllRestaurants() {
		// my arraylist
		List<Accounts> accountList = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			String sql = "SELECT * FROM restaurant";

			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery(); // <--query not update

			while (rs.next()) {
				accountList.add(new Restaurant(rs.getString(1), rs.getString(2)));
				PersistData.userList.add(rs.getString(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			loggy.error("A SQL exception was thrown: ", e);
		}

		return accountList;
	}

	public static List<Accounts> selectAllHealthInspectors() {
		// my arraylist
		List<Accounts> accountList = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			String sql = "SELECT * FROM health_inspector";

			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery(); // <--query not update

			while (rs.next()) {
				accountList.add(new HealthInspector(rs.getString(1), rs.getString(2)));
				PersistData.userList.add(rs.getString(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			loggy.error("A SQL exception was thrown: ", e);
		}

		return accountList;
	}

	public static void selectAllHealthInspectorFridges() {
		// my arraylist
		List<Accounts> accountList = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			String sql = "SELECT * FROM health_inspector_fridges";

			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery(); // <--query not update

			while (rs.next()) {
				for(Accounts temp : PersistData.accountList) {
					if(temp instanceof HealthInspector) {
						if(rs.getString(1).equals(temp.getUsername())) {
							((HealthInspector) temp).addViewableFridge(rs.getInt(2));
						}
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			loggy.error("A SQL exception was thrown: ", e);
		}
	}
	
	public static List<Fridge> selectAllFridges() {
		// my arraylist
		List<Fridge> fridgeList = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			String sql = "SELECT * FROM fridge";

			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery(); // <--query not update

			while (rs.next()) {
				fridgeList.add(new Fridge(rs.getInt(1), rs.getString(2)));
				PersistData.fridgeIdList.add(rs.getInt(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			loggy.error("A SQL exception was thrown: ", e);
		}

		return fridgeList;
	}

	public static void selectAllFood() {
		// my arraylist

		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			String sql = "SELECT * FROM food";

			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery(); // <--query not update

			while (rs.next()) {
				for (Fridge temp : PersistData.fridgeList) {
					if (temp.getUniqueId() == rs.getInt(3)) {
						temp.addFood(rs.getString(2));
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			loggy.error("A SQL exception was thrown: ", e);
		}
	}

	public static void deleteFood(String food, int fridge_id) {
		// my arraylist

		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			String sql = "DELETE FROM food WHERE food_name = ? AND fridge_id = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, food);
			ps.setInt(2, fridge_id);
			ps.executeUpdate(); // <--update
			loggy.info(food + " was deleted from fridge: " + fridge_id);
			
		} catch (SQLException e) {
			e.printStackTrace();
			loggy.error("A SQL exception was thrown: ", e);
		}
	}

	public static void deleteFridge(int fridge_id) {
		// my arraylist

		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			String sql = "DELETE FROM fridge WHERE fridge_id = ?";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, fridge_id);
			ps.executeUpdate(); // <--query not update
			loggy.info("Deleted fridge: " + fridge_id);
			
		} catch (SQLException e) {
			e.printStackTrace();
			loggy.error("A SQL exception was thrown: ", e);
		}
	}

	public static void transferFood(String food, int initial_fridge_id, int new_fridge_id) {
		// my arraylist

		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			
			addFood(food, new_fridge_id);
			deleteFood(food, initial_fridge_id); 
			loggy.info(food + " was transfered from fridge: " + initial_fridge_id + " to fridge: " + new_fridge_id);
//			String sql = "UPDATE food SET fridge_id = ? WHERE fridge_id = ? AND food_name = ?";
//			
//
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ps.setInt(1, new_fridge_id); // the 1st "?"
//			ps.setInt(2, initial_fridge_id); // the 2nd "?"
//			ps.setString(3, food); // the 1st "?"
//			ps.executeUpdate(); // <--query not update

			
		} catch (SQLException e) {
			e.printStackTrace();
			loggy.error("A SQL exception was thrown: ", e);
		}
	}

}
