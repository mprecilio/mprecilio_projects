package example.dao.user;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import example.model.user.User;
import example.model.user.UserGender;
import example.model.user.UserRole;
import example.utilities.ToEncrypted;

@Transactional
@Repository("userDao")
public class UserDaoImpl implements UserDao {
	
	private SessionFactory ses;
	
	
	@Autowired
	public UserDaoImpl(SessionFactory ses) {
		super();
		this.ses = ses;
	}
	

	public UserDaoImpl() {
		super();
	}


	public SessionFactory getSes() {
		return ses;
	}

	public void setSes(SessionFactory ses) {
		this.ses = ses;
	}

	////////////////////////////////////////////Create\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	@Override
	public User userReg(User newUser) {
		
		ses.getCurrentSession().save(newUser);
		System.out.println(newUser.getUsername() + " has registered successfully!");
		List<User> userList = ses.getCurrentSession().createQuery("from User as u where u.username = ?1").setParameter(1,newUser.getUsername()).list();
		return userList.get(0);
	}

	
	
	////////////////////////////////////////////Read\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	@Override
	public User userLogin(User currUser){
		String loginUsername = currUser.getUsername().toLowerCase();
		String unencryptPass = currUser.getPassword();
		
		List<User> listUser = ses.getCurrentSession().createQuery("from User as u where u.username = ?1").setParameter(1,loginUsername).list();
		if(listUser.size() == 0)return null;
		
		User matchingUser = listUser.get(0);
		UserGender myGender = ses.getCurrentSession().get(UserGender.class, matchingUser.getUserGender().getGenderId()); 
		System.out.println(myGender);
		
		matchingUser.setUserGender(myGender);
		String expectedPassword = matchingUser.getPassword();
		byte[] salt = matchingUser.getSalt();
		String loginPassword = null;
		try {
			loginPassword = ToEncrypted.generateHash(unencryptPass, salt);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		if(expectedPassword.equals(loginPassword)) {
			System.out.println("Login success!!");
			System.out.println("Welcome " + matchingUser.getFname());
			SimpleDateFormat sf = new SimpleDateFormat("MMM-dd-yyyy");
			matchingUser.setDateJoinedToStr(sf.format(matchingUser.getDateJoined()).toString());
			System.out.println(sf.format(matchingUser.getDateJoined()));
			return matchingUser;
		}

		
		System.out.println("Login failed!!");
		return null;
	}
	
	public UserGender userGenderById(int id) {
		UserGender myGender = ses.getCurrentSession().get(UserGender.class, id); 
		return myGender;
	}
	public UserRole userRoleById(int id) {
		UserRole myRole = ses.getCurrentSession().get(UserRole.class, id); 
		return myRole;
	}
	
	@Override
	public User userViewProfile(User userProfile) {
		// TODO Auto-generated method stub
		return null;
	}
	
	////////////////////////////////////////////Update\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	public User userUpdate(User currUser) {

		ses.getCurrentSession().update(currUser);
		return ses.getCurrentSession().get(User.class, currUser.getUserID());

	}
	@Override
	public void storeToken(User userWInfo, String token) {
		userWInfo.setToken(token);
		ses.getCurrentSession().update(userWInfo);
	}
	
	@Override
	public User updateCurrUser(User userWInfo) {
		ses.getCurrentSession().update(userWInfo);
		return ses.getCurrentSession().get(User.class, userWInfo.getUserID());
	}
	
	
	////////////////////////////////////////////Reset Password\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	/////This method VERIFIES YOUR EMAIL in the database BEFORE sending reset password link
	public User verifyingEmail(User currUser) {
		System.out.println("Verifying Email");
		
		String emailVerify = currUser.getUserEmail();
		
		List<User> listUser = ses.getCurrentSession().createQuery("from User as u where u.userEmail = ?1").setParameter(1,emailVerify).list();
		if(listUser.size() == 0) {
			System.out.println("Dang it!! found none!!!!!!!!!!!!!!!!");
			return null;
		}
		User matchingUser = listUser.get(0);
		String email = matchingUser.getUserEmail();
		System.out.println("this is matching email!!!!!!!!!!: " + email);
		
		
		return matchingUser;
	}
	
	
	////////////This finally RESETS the password
	
	public String resetPassword(User currUser) {
		
		System.out.println("In Final Reset Password");

		//////////Email received from FrontEnd
		String emailReceived = currUser.getUserEmail();

		///////////Password received from FrontEnd
		String unencryptPass = currUser.getPassword();
		System.out.println(unencryptPass);
		
		List<User> listUser = ses.getCurrentSession().createQuery("from User as u where u.userEmail = ?1").setParameter(1,emailReceived).list();

		if(listUser.size() == 0) {
			System.out.println("Dang it!! found none!!!!!!!!!!!!!!!!");
			return null;
		}
		
		/////////////Gets information related to the email received
		User matchingUser = listUser.get(0);
		String email = matchingUser.getUserEmail();
		System.out.println("this is matching email!!!!!!!!!!: " + email);
		
		
		/////////////Getting current Password in the database
		String expectedPassword = matchingUser.getPassword();
		
		System.out.println(expectedPassword);
		
		byte[] salt = matchingUser.getSalt();
		String newPassword = null;
		try {
			newPassword = ToEncrypted.generateHash(unencryptPass, salt);
			
			System.out.println(newPassword);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		/////////////Checking if the CURRENT Password is the same as the NEW Password
		if(expectedPassword.equals(newPassword)) {
			System.out.println("Yoooo they matched!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			
			///////returns a hard-coded String for now
			return "same password!";
		}
		
		
		/////////////Resets the password if not the same as the CURRENT one
		Query myPasswordChange =
				ses.getCurrentSession().createQuery("update User set sn_password=:Password "
						+ "where sn_email=:Email");
				myPasswordChange.setParameter("Password", newPassword);
				myPasswordChange.setParameter("Email", currUser.getUserEmail());
		
		myPasswordChange.executeUpdate();

		/////////////returns success....kinda!
		return "password changed!";
	}

	
	////////////////////////////////////////////Profile Picture\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

	public User setUserProfile(User currUser) {

		Query myUserInfo = ses.getCurrentSession()
				.createQuery("update User set sn_profile_photo=:Photo " + "where sn_username=:Username");
		myUserInfo.setParameter("Photo", currUser.getProfilePhoto());
		myUserInfo.setParameter("Username", currUser.getUsername());

		myUserInfo.executeUpdate();

		return currUser;

	}

	public String getUserProfile(User currUser) {
		System.out.println("Getting Profile");

		String username = currUser.getUsername();

		List<User> listUser = ses.getCurrentSession().createQuery("from User as u where u.username = ?1")
				.setParameter(1, username).list();
		if (listUser.size() == 0) {
			System.out.println("Dang it!! found none!!!!!!!!!!!!!!!!");
			return "Default.png";
		}
		User matchingUser = listUser.get(0);
		String photo = matchingUser.getProfilePhoto();
		System.out.println("this is profile picture!!!!!!!!!!: " + photo);

		return photo;
	}

	/////////////////////////////////////////////////Find User\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	public User findUser(User currUser) {

		System.out.println("In Finding User");

		////////// username received from FrontEnd
		String username = currUser.getUsername();
		@SuppressWarnings("unchecked")
		List<User> listUser = ses.getCurrentSession().createQuery("from User as u where u.username = ?1")
				.setParameter(1, username).list();
		if (listUser.size() == 0) {
			System.out.println("Dang it!! found none!!!!!!!!!!!!!!!!");
			return null;
		}
		User matchingUser = listUser.get(0);
		return matchingUser;
	}
	
	@Override
	public User findUserTwo(User currUser) {

		System.out.println("In Finding User");

		////////// username received from FrontEnd
		String username = currUser.getUsername();
		@SuppressWarnings("unchecked")
		List<User> listUser = ses.getCurrentSession().createQuery("from User as u where u.username = ?1")
				.setParameter(1, username).list();
		if (listUser.size() == 0) {
			System.out.println("Dang it!! found none!!!!!!!!!!!!!!!!");
			return null;
		}
		User matchingUser = listUser.get(0);
		return matchingUser;
	}

	
}
