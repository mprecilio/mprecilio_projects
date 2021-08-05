package project_1_v2.dao.reimbursement;

import java.util.List;

import project_1_v2.model.Reimbursement;

public interface ReimbursementDao {
	
	public List<Reimbursement> getAllEmployeeReimbs(String user);
	
	List<Reimbursement> getAllReimbs();
	
	
	Boolean updateReimbStatus(Reimbursement reimb);
	
	void newReimb(Reimbursement reimb);

}
