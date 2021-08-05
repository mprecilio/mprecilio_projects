package example.utilities;


//import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.SecureRandom;
import java.time.LocalDateTime;

//import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import example.dao.post.PostDao;
import example.dao.user.UserDao;
import example.model.user.User;
import example.utilities.ToEncrypted;


public class UnitTest {

	private UserDao ud;
	private PostDao pd;
	
	public UnitTest() {
		super();
	}
	
	
	@Autowired
	public UnitTest(UserDao ud, PostDao pd) {
		super();
		this.ud = ud;
		this.pd = pd;
	}
	
	
	public UserDao getUd() {
		return ud;
	}


	public void setUd(UserDao ud) {
		this.ud = ud;
	}


	public PostDao getPd() {
		return pd;
	}


	public void setPd(PostDao pd) {
		this.pd = pd;
	}


//	@Test
//	public void testingReg() {
//		User regUser = new User();
//		String r1 = LocalDateTime.now().toString();
//		String r2 = LocalDateTime.now().toString();
//		regUser.setUsername("junit" + r1);
//		regUser.setPassword("junit");
//		regUser.setSalt(ToEncrypted.createSalt());
//		regUser.setFname("bob");
//		regUser.setLname("bob");
//		regUser.setUserEmail(r2+ "@orngodg.com");
//		User accAdded = ud.userReg(regUser);
//        assertTrue((accAdded!=null), "We got the expected output");
//    }
	
	@Test
	public void testingLogin() {
		System.out.println("Im here");
		User regUser = new User();
		regUser.setUsername("admin");
		regUser.setPassword("admin");
		User userLogged = ud.userReg(regUser);
        assertTrue((userLogged!=null), "We got the expected output");
    }
//	@Test
//    public void testLoginSuccess() {
//        Account noSuchAccount = loginDao.validLogin("emp01","123456");
//        assertTrue(noSuchAccount.getUsername().equals("emp01"), "We got the expected output");
//    }
//	
//	/*----------------------------------------------------------------------------------------------------
//	reimbursement dao tests
//	*/
//    public static ReimbursementDaoImpl reimbDao = new ReimbursementDaoImpl();
//	@Test
//    public void testEmpReimbSuccess() { 
//        List<Reimbursement> reimbList = reimbDao.getAllEmployeeReimbs("emp01");
//        String expected = "Reimbursement [reimbId=2, reimbAmount=0.0, reimbSubmitted=2021-06-25 00:00:00.0, reimbResolved=2021-07-11 17:38:24.922794, reimbDescription=Trip to Tokyo, reimbAuthor=emp01, reimbResolver=admin, reimbStatus=Approved, reimbType=Travel]";
//        assertTrue(reimbList.get(reimbList.size()-2).toString().equals(expected), "We got the expected output");
//    }
//	
//	@Test
//    public void testManageReimbSuccess() {
//		List<Reimbursement> reimbList = reimbDao.getAllReimbs();
//        String expected = "Reimbursement [reimbId=2, reimbAmount=0.0, reimbSubmitted=2021-06-25 00:00:00.0, reimbResolved=2021-07-11 17:38:24.922794, reimbDescription=Trip to Tokyo, reimbAuthor=emp01, reimbResolver=admin, reimbStatus=Approved, reimbType=Travel]";
//        assertTrue(reimbList.get(reimbList.size()-2).toString().equals(expected), "We got the expected output");
//    }

}
