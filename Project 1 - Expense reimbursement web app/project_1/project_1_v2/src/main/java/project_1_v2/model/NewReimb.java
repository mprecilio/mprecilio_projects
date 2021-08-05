package project_1_v2.model;

public class NewReimb {
	private String reimb_req_amount;
	private String reimb_req_description;
	private String reimb_req_type;
	
	public NewReimb() {
		super();
	}

	public NewReimb(String reimb_req_amount, String reimb_req_description, String reimb_req_type) {
		super();
		this.reimb_req_amount = reimb_req_amount;
		this.reimb_req_description = reimb_req_description;
		this.reimb_req_type = reimb_req_type;
	}




	public String getReimb_req_amount() {
		return reimb_req_amount;
	}

	public void setReimb_req_amount(String reimb_req_amount) {
		this.reimb_req_amount = reimb_req_amount;
	}

	public String getReimb_req_description() {
		return reimb_req_description;
	}

	public void setReimb_req_description(String reimb_req_description) {
		this.reimb_req_description = reimb_req_description;
	}

	public String getReimb_req_type() {
		return reimb_req_type;
	}

	public void setReimb_req_type(String reimb_req_type) {
		this.reimb_req_type = reimb_req_type;
	}

	@Override
	public String toString() {
		return "NewReimb [reimb_req_amount=" + reimb_req_amount + ", reimb_req_description=" + reimb_req_description
				+ ", reimb_req_type=" + reimb_req_type + "]";
	}
	
	
	

}
