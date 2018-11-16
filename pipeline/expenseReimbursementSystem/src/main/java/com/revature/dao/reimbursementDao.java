package com.revature.dao;

import java.util.ArrayList;

import com.expenseReimbursementSystem.beans.reimbursement;

public interface reimbursementDao {
	public boolean checkReimbursement(reimbursement reim);
	public boolean insertReimbursement(reimbursement reim);
	public boolean updateReimbursement(reimbursement reim);
	public ArrayList<reimbursement> getAllReimbursement();
	public reimbursement getReimbursement(int id);
}
