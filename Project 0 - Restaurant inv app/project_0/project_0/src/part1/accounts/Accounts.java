package part1.accounts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import part1.fridge.Fridge;

//NOTE: Check if setters are needed


/*
 * Accounts class will be parent to any types of accounts created including 
 * Restaurant, HealthInspector, and any future account types added
 */

public abstract class Accounts implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 13764821L;
	private final String username;
	private String password;

	
	public Accounts(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	
	//checks if the account username matches the username given
	public boolean checkUsername(String inputUser) {
		String usernameLower = this.username.toLowerCase();
		return usernameLower.equals(inputUser.toLowerCase());
	}
	
	//checks if the correct password was given
	public final boolean checkPassword(String inputPass) {
		return password.equals(inputPass);
	}
	

	
	


	
	
	


	


}
