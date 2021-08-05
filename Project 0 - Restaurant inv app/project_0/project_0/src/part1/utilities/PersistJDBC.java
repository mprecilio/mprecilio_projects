package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
	 * JDBC needs a SPECIFIC format to the URL string so that the DriverManager
	 * can understand WHICH driver you're asking to use.
	 * 
	 * For Postgresql, the URL format is:
	 * 		jdbc:postgresql://[endpoint]/[databasename]
	 * 
	 * For OracleSQL, the URL format is:
	 * 		jdbc:oracle:thin:@[endpoint]:[port]:[dbname]
	 * 
	 */
	public static String url = "jdbc:postgresql://datachan.c4wt8faaxlgp.us-east-2.rds.amazonaws.com/pokemonDB";
	public static String username= "datachan";
	public static String password="p4ssw0rd";

	/////////////////////////////////////////////////
	//////////////MAIN METHOD////////////////////////
	public static void main(String[] args) {
		//statementExample(1050, "Space Monkey", "fighting", "ice");
		//preparedStatementExample(10520, "Mudkip", "water", null);
		//preparedStatementPrimaryKeyExample(1075, "Picturemon", "wood", "ice");
		
		System.out.println(selectAllExample());
		
		System.out.println("done");
	}
	
	////////////////////////////////////////////////////////////////
	////////////MY METHODS THAT USE JDBC////////////////////////////
	
	/////STATEMENT
	public static void statementExample(int p_id, String p_name, String p_type, String p_typetwo) {
		
		try(Connection conn = DriverManager.getConnection(url, username, password)){
			
			String sql = "INSERT INTO pokemon VALUES("+p_id+",'"+p_name+"','"+p_type+"','"+
					p_typetwo+"')";
			
			/*
			 * Is there anything stopping me from making p_typetwo equal
			 * 
			 * 	p_typetwo = "'); DROP TABLE pokemon;";
			 * 
			 * String sql = "INSERT INTO pokemon VALUES(5000, 'asdf', 'asfa', ''); DROP TABLE pokemon;');
			 * 
			 * SQL Injection. This is where a user can type in valid sql syntax that will be
			 * 	passed to your database then executed.
			 */
			
			Statement statement = conn.createStatement();
			int numOfRowsChanged = statement.executeUpdate(sql);
			
			System.out.println("The # of rows changed: "+numOfRowsChanged);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	////PREPARED STATEMENT
	public static void preparedStatementExample(int p_id, String p_name, String p_type, String p_typetwo) {
		
		try(Connection conn = DriverManager.getConnection(url, username, password)){
			
			String sql = "INSERT INTO pokemon VALUES(?,?,?,?)";
			
			/*
			 * Prepared Statements are precompiled. Because of this the prepared
			 * statement will guard against sql injection AND it is faster
			 * 
			 * ALSO, you can hardcode values into your sql statement
			 * 
			 * String sql = "INSERT INTO pokemon VALUES(null,?,'normal',?)";
			 * 	NOW there are only two "?"s...indices 1 and 2
			 */
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, p_id); //the 1st "?"
			ps.setString(2, p_name); //the 2nd "?"
			ps.setString(3, p_type); //the 3rd "?"
			ps.setString(4, p_typetwo); //the 4th "?"
			
			ps.executeUpdate(); //THIS line is what sends the information to the DB
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	////PREPARED STATEMENT W/ PK return
	public static void preparedStatementPrimaryKeyExample(int p_id, String p_name, String p_type, String p_typetwo) {
		
		try(Connection conn = DriverManager.getConnection(url, username, password)){
			
			String sql = "INSERT INTO pokemon VALUES(?,?,?,?)";
			
			/*
			 * Prepared Statements are precompiled. Because of this the prepared
			 * statement will guard against sql injection AND it is faster
			 */
															//necessary to return the PK
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, p_id); //the 1st "?"
			ps.setString(2, p_name); //the 2nd "?"
			ps.setString(3, p_type); //the 3rd "?"
			ps.setString(4, p_typetwo); //the 4th "?"
			
			ps.executeUpdate(); //THIS line is what sends the information to the DB
			
			//this section is necessary to return the PK of the inserted record
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				System.out.println("the returned PK: "+rs.getInt("pokemon_id"));
			}
			//////
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	////SELECT * FROM TABLE example
	public static List<Pokemon> selectAllExample() {
		//my arraylist
		List<Pokemon> pokes = new ArrayList<>();
		
		try(Connection conn = DriverManager.getConnection(url, username, password)){
			
			String sql = "SELECT * FROM pokemon";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery(); //<--query not update
			
			while(rs.next()) {
				pokes.add(new Pokemon(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString("pokemon_secondtype")));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return pokes;
	}

}


