package project_1_v2.utilities;
import static org.junit.jupiter.api.Assertions.*;



import java.util.List;

import org.junit.jupiter.api.Test;

import project_1_v2.dao.login.LoginDaoImpl;
import project_1_v2.dao.reimbursement.ReimbursementDaoImpl;
import project_1_v2.model.Account;
import project_1_v2.model.Reimbursement;

public class UnitTest {
	/*----------------------------------------------------------------------------------------------------
		login dao tests
	*/
	public static LoginDaoImpl loginDao = new LoginDaoImpl();
	
	@Test
    public void testFailedLogin() {
        Account noSuchAccount = loginDao.validLogin("em9853","123456");
        assertTrue(noSuchAccount  == null, "We got the expected output");
    }
	
	@Test
    public void testLoginSuccess() {
        Account noSuchAccount = loginDao.validLogin("emp01","123456");
        assertTrue(noSuchAccount.getUsername().equals("emp01"), "We got the expected output");
    }
	
	/*----------------------------------------------------------------------------------------------------
	reimbursement dao tests
	*/
    public static ReimbursementDaoImpl reimbDao = new ReimbursementDaoImpl();
	@Test
    public void testEmpReimbSuccess() { 
        List<Reimbursement> reimbList = reimbDao.getAllEmployeeReimbs("emp01");
        String expected = "Reimbursement [reimbId=2, reimbAmount=0.0, reimbSubmitted=2021-06-25 00:00:00.0, reimbResolved=2021-07-11 17:38:24.922794, reimbDescription=Trip to Tokyo, reimbAuthor=emp01, reimbResolver=admin, reimbStatus=Approved, reimbType=Travel]";
        assertTrue(reimbList.get(reimbList.size()-2).toString().equals(expected), "We got the expected output");
    }
	
	@Test
    public void testManageReimbSuccess() {
		List<Reimbursement> reimbList = reimbDao.getAllReimbs();
        String expected = "Reimbursement [reimbId=2, reimbAmount=0.0, reimbSubmitted=2021-06-25 00:00:00.0, reimbResolved=2021-07-11 17:38:24.922794, reimbDescription=Trip to Tokyo, reimbAuthor=emp01, reimbResolver=admin, reimbStatus=Approved, reimbType=Travel]";
        assertTrue(reimbList.get(reimbList.size()-2).toString().equals(expected), "We got the expected output");
    }

}
