package example.model.user;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "sn_user")
@JsonIgnoreProperties(value = { "salt" })
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sn_user_id")
	private Integer userID;

	@Column(name = "sn_username", unique = true, nullable = false)
	private String username;

	@Column(name = "sn_password", nullable = false)
	private String password;

	@ElementCollection
	@OrderColumn(name = "sn_user_salt", nullable = false)
	private byte[] salt;

	@Column(name = "sn_fname", nullable = false)
	private String fname;

	@Column(name = "sn_mname")
	private String mname;

	@Column(name = "sn_lname", nullable = false)
	private String lname;

	@Column(name = "sn_email", unique = true, nullable = false)
	private String userEmail;
	
	@CreationTimestamp
	@Column(name = "sn_user_date_joined")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateJoined;

	@JoinColumn(name = "sn_gender_id", nullable = false)
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private UserGender userGender;

	@JoinColumn(name = "sn_role_id", nullable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	private UserRole userRole;

	@Column(name = "sn_profile_photo")
	private String profilePhoto;

	@Column(name = "sn_profile_background_photo")
	private String profileBackgroundPhoto;
	
	@Transient
	private String dateJoinedToStr;
	@Transient
	private int userGenderInt;
	@Transient
	private int userRoleInt;
	
	private String token;

	public User() {
		super();
	}

	//	public User(String userEmail) {
//		super();
//		this.userEmail = userEmail;
//	}
	
	public User(String username) {
		super();
		this.username = username;
	}

	public User(String username, String password, byte[] salt, String fname, String mname, String lname,
			String userEmail, Date dateJoined, UserGender userGender, UserRole userRole) {
		super();
		this.username = username;
		this.password = password;
		this.salt = salt;
		this.fname = fname;
		this.mname = mname;
		this.lname = lname;
		this.userEmail = userEmail;
		this.dateJoined = dateJoined;
		this.userGender = userGender;
		this.userRole = userRole;
	}

	public User(String username, String profilePhoto) {
		super();
		this.username = username;
		this.profilePhoto = profilePhoto;
	}
	
	
	public Integer getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
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

	public void setSalt(byte[] salt) {
		this.salt = salt;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getDateJoinedToStr() {
		return dateJoinedToStr;
	}

	public void setDateJoinedToStr(String dateJoinedToStr) {
		this.dateJoinedToStr = dateJoinedToStr;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Date getDateJoined() {
		return dateJoined;
	}

	public void setDateJoined(Date dateJoined) {
		this.dateJoined = dateJoined;
	}

	public UserGender getUserGender() {
		return userGender;
	}

	public void setUserGender(UserGender userGender) {
		this.userGender = userGender;
	}

	public int getUserGenderInt() {
		return userGenderInt;
	}

	public void setUserGenderInt(Integer intUserGender) {
		if (intUserGender == null) {
			this.userGender = null;
			return;
		}
		switch (intUserGender) {
		case 1:
			System.out.println("Im here------------------------");
			this.userGender = new UserGender(1, "Male", "He/Him");
			System.out.println(this.userGender + "---------------------------");
			break;
		case 2:
			this.userGender = new UserGender(2, "Female", "She/Her");
			break;
		default:
			this.userGender = new UserGender(3, "Other", "They/Them");
			break;
		}
	}

	public void setUpdateUserGender(Integer intUserGender) {
		if (intUserGender == null) {
			this.userGender = null;
			return;
		}
		switch (intUserGender) {
		case 1:
			System.out.println("Im here------------------------");
			this.userGender = new UserGender(1, "Male", "He/Him");
			System.out.println(this.userGender + "---------------------------");
			break;
		case 2:
			this.userGender = new UserGender(2, "Female", "She/Her");
			break;
		default:
			this.userGender = new UserGender(3, "Other", "They/Them");
			break;
		}
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public int getUserRoleInt() {
		return userRoleInt;
	}

	public void setUserRoleInt(Integer intUserRole) {
		switch(intUserRole){
		case 1:
			this.userRole = new UserRole(1, "Admin");
			break;
		case 2:
			this.userRole = new UserRole(2, "Moderator");
			break;
		case 3:
			this.userRole = new UserRole(3, "Advertiser");
			break;
		default:
			this.userRole = new UserRole(4, "Member");
	
		}
	}

	public void setUpdateFname(String fname) {
		if (fname == null) {
			return;
		} else {
			this.fname = fname;
		}
	}

	public void setUpdateMname(String mname) {
		if (mname == null) {
			return;
		} else {
			this.mname = mname;
		}
	}

	public void setUpdateLname(String lname) {
		if (lname == null) {
			return;
		} else {
			this.lname = lname;
		}

	}
	
	
	public String getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
	}
	

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", username=" + username + ", password=" + password + ", salt="
				+ Arrays.toString(salt) + ", fname=" + fname + ", mname=" + mname + ", lname=" + lname + ", userEmail="
				+ userEmail + ", dateJoined=" + dateJoined + ", userGender=" + userGender + ", userRole=" + userRole
				+ ", profilePhoto=" + profilePhoto + ", dateJoinedToStr=" + dateJoinedToStr + ", token=" 
				+ token + "]";
	}

}
