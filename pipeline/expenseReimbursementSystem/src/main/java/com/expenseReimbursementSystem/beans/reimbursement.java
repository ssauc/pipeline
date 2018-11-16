package com.expenseReimbursementSystem.beans;

public class reimbursement {
	int reim_id;
	int rtype_id;
	String rtype_descr;
	String reim_descr;
	int ras_id;
	String ras_descr;
	int emp_id;
	String emp_username;
	float reim_amount;
	int approvalMgr_id;
	public reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}
	public reimbursement(int reim_id, int rtype_id, String rtype_descr, String reim_descr, int ras_id, String ras_descr,
			int emp_id, String emp_username, float reim_amount, int approvalMgr_id) {
		super();
		this.reim_id = reim_id;
		this.rtype_id = rtype_id;
		this.rtype_descr = rtype_descr;
		this.reim_descr = reim_descr;
		this.ras_id = ras_id;
		this.ras_descr = ras_descr;
		this.emp_id = emp_id;
		this.emp_username = emp_username;
		this.reim_amount = reim_amount;
		this.approvalMgr_id = approvalMgr_id;
	}
	@Override
	public String toString() {
		return "reimbursement [reim_id=" + reim_id + ", rtype_id=" + rtype_id + ", rtype_descr=" + rtype_descr
				+ ", reim_descr=" + reim_descr + ", ras_id=" + ras_id + ", ras_descr=" + ras_descr + ", emp_id="
				+ emp_id + ", emp_username=" + emp_username + ", reim_amount=" + reim_amount + ", approvalMgr_id="
				+ approvalMgr_id + "]";}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + approvalMgr_id;
		result = prime * result + emp_id;
		result = prime * result + ((emp_username == null) ? 0 : emp_username.hashCode());
		result = prime * result + ((ras_descr == null) ? 0 : ras_descr.hashCode());
		result = prime * result + ras_id;
		result = prime * result + Float.floatToIntBits(reim_amount);
		result = prime * result + ((reim_descr == null) ? 0 : reim_descr.hashCode());
		result = prime * result + reim_id;
		result = prime * result + ((rtype_descr == null) ? 0 : rtype_descr.hashCode());
		result = prime * result + rtype_id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		reimbursement other = (reimbursement) obj;
		if (approvalMgr_id != other.approvalMgr_id)
			return false;
		if (emp_id != other.emp_id)
			return false;
		if (emp_username == null) {
			if (other.emp_username != null)
				return false;
		} else if (!emp_username.equals(other.emp_username))
			return false;
		if (ras_descr == null) {
			if (other.ras_descr != null)
				return false;
		} else if (!ras_descr.equals(other.ras_descr))
			return false;
		if (ras_id != other.ras_id)
			return false;
		if (Float.floatToIntBits(reim_amount) != Float.floatToIntBits(other.reim_amount))
			return false;
		if (reim_descr == null) {
			if (other.reim_descr != null)
				return false;
		} else if (!reim_descr.equals(other.reim_descr))
			return false;
		if (reim_id != other.reim_id)
			return false;
		if (rtype_descr == null) {
			if (other.rtype_descr != null)
				return false;
		} else if (!rtype_descr.equals(other.rtype_descr))
			return false;
		if (rtype_id != other.rtype_id)
			return false;
		return true;
	}
	public int getReim_id() {
		return reim_id;
	}
	public void setReim_id(int reim_id) {
		this.reim_id = reim_id;
	}
	public int getRtype_id() {
		return rtype_id;
	}
	public void setRtype_id(int rtype_id) {
		this.rtype_id = rtype_id;
	}
	public String getRtype_descr() {
		return rtype_descr;
	}
	public void setRtype_descr(String rtype_descr) {
		this.rtype_descr = rtype_descr;
	}
	public String getReim_descr() {
		return reim_descr;
	}
	public void setReim_descr(String reim_descr) {
		this.reim_descr = reim_descr;
	}
	public int getRas_id() {
		return ras_id;
	}
	public void setRas_id(int ras_id) {
		this.ras_id = ras_id;
	}
	public String getRas_descr() {
		return ras_descr;
	}
	public void setRas_descr(String ras_descr) {
		this.ras_descr = ras_descr;
	}
	public int getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}
	public String getEmp_username() {
		return emp_username;
	}
	public void setEmp_username(String emp_username) {
		this.emp_username = emp_username;
	}
	public float getReim_amount() {
		return reim_amount;
	}
	public void setReim_amount(float reim_amount) {
		this.reim_amount = reim_amount;
	}
	public int getApprovalMgr_id() {
		return approvalMgr_id;
	}
	public void setApprovalMgr_id(int approvalMgr_id) {
		this.approvalMgr_id = approvalMgr_id;
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}
	
	
	

}
