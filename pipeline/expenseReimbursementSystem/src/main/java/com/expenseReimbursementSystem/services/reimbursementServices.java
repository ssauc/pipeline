package com.expenseReimbursementSystem.services;

import java.util.ArrayList;
import java.util.HashMap;

import com.expenseReimbursementSystem.beans.employee;
import com.expenseReimbursementSystem.beans.reimbursement;
import com.revature.dao.reimbursementDaoImpl;

public class reimbursementServices {
private static reimbursementServices reimService;
	
	public static reimbursementServices getReimbursementService() {
		if(reimService == null) {
			reimService = new reimbursementServices();
		}
		return reimService;
	}
	
	public static ArrayList<reimbursement> getUserReimbursement(employee emp){
		return reimbursementDaoImpl.getInstance().getUserReimbursement(emp);
	}
	
	public static HashMap<String,Integer>  getUserReimbursementCount(employee emp){
		return reimbursementDaoImpl.getInstance().getUserReimbursementCount(emp);
	}
	
	public static boolean createReimbursement(reimbursement reim) {
		return reimbursementDaoImpl.getInstance().insertReimbursement(reim);
	}
	
	public static ArrayList<reimbursement> getAllReim(){
		return reimbursementDaoImpl.getInstance().getAllReimbursement();
	}
	
	public static boolean approveReimbursements(reimbursement reim) {
		return reimbursementDaoImpl.getInstance().updateReimbursement(reim);
	}

}
