package project_1_v2.service;

import java.util.List;

import project_1_v2.dao.reimbursement.ReimbursementDaoImpl;
import project_1_v2.model.Reimbursement;

public class ServiceReimbursements {
	public List<Reimbursement> getAllReimbs() {
		List<Reimbursement> myReimbs = new ReimbursementDaoImpl().getAllReimbs();
		return myReimbs;
		
	}
	
	public void updateReimbStatus(Reimbursement reimb) {
		new ReimbursementDaoImpl().updateReimbStatus(reimb);
	}
	
	public void newReimb(Reimbursement reimb) {
		new ReimbursementDaoImpl().newReimb(reimb);
	}
}
