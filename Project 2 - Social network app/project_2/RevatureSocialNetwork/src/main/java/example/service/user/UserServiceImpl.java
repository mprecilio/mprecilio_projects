package example.service.user;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.dao.user.UserDao;
import example.model.user.User;
import example.utilities.ToEncrypted;

@Service
public class UserServiceImpl implements UserService {
	
	private UserDao ud;
	private User myUser;
	
	
	@Autowired
	public UserServiceImpl(UserDao ud) {
		super();
		this.ud = ud;
	}

	////////////////////////////////////////////Create\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	@Override
	public User serviceUserReg(User currUser) {
		byte[] salt = ToEncrypted.createSalt();
		currUser.setSalt(salt);
		String unencryptedPass = currUser.getPassword();
		currUser.setUsername(currUser.getUsername().toLowerCase());
		try {
			currUser.setPassword(ToEncrypted.generateHash(unencryptedPass, salt));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return ud.userReg(currUser);
	}

	
	
	////////////////////////////////////////////Read\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	@Override
	public User serviceUserLogin(User currUser) {
		return ud.userLogin(currUser);
	}
	
	@Override
	public User searchUser(User currUser) {
		return ud.findUser(currUser);
	}
	
	@Override
	public User searchUserTwo(User currUser) {
		return ud.findUser(currUser);
	}
	
	
	////////////////////////////////////////////Update\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	public User serviceUserUpdate(User currUser) {
		return ud.userUpdate(currUser);
	}

	@Override
	public User verifyingEmail(User currUser) {
		return ud.verifyingEmail(currUser);
	}

	@Override
	public String resetPassword(User currUser) {
		return ud.resetPassword(currUser);
	}
	
	@Override
	public void storeTokenEmail(User userWInfo, String token) {
		ud.storeToken(userWInfo, token);
	}

	@Override
	public User updateCurrUser(User userWInfo) {
		return ud.updateCurrUser(userWInfo);
	}
	
	
	////////////////////////////////////////////Profile Photo\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	@Override
	public User setUserProfile(User currUser) {
		return ud.setUserProfile(currUser);
	}

	@Override
	public String getUserProfile(User currUser) {
		return ud.getUserProfile(currUser);
	}



	
	

	
	
	
	////////////////////////////////////////////Delete\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

}
