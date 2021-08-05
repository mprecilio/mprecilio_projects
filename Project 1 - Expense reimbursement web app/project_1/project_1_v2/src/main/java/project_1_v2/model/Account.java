package project_1_v2.model;

import java.util.Arrays;

public class Account {
	private int userId;
	private String username;
	private String password;
	private byte[] salt = new byte[1];
	private String fName;
	private String lName;
	private String email;
	private String role;

	public Account() {
		super();
	}

	public Account(String username, String role) {
		super();
		this.username = username;
		this.role = role;
	}
	
	public Account(int userId, String username, String password, byte[] salt, String fName, String lName, String email,
			String role) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.salt = salt;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.role = role;
	}

	public int getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte[] getSalt() {
		return salt;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Account [userId=" + userId + ", username=" + username + ", password=" + password + ", salt="
				+ Arrays.toString(salt) + ", fName=" + fName + ", lName=" + lName + ", email=" + email + ", role="
				+ role + "]";
	}

	
}
