package com.expenseReimbursementSystem.delegate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.expenseReimbursementSystem.beans.employee;
import com.expenseReimbursementSystem.beans.reimbursement;
import com.expenseReimbursementSystem.services.reimbursementServices;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReimbursementDelegate {
	final static Logger Log = Logger.getLogger(ReimbursementDelegate.class);
	
	public ArrayList<reimbursement> getUserReimbursements(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Log.info("A user is retrieving user reimbursements");
		employee usr = (employee) req.getSession().getAttribute("user");
		 ArrayList<reimbursement> reimlst = reimbursementServices.getUserReimbursement(usr);
		 
		 return reimlst;
	}
	
	public ArrayList<reimbursement> getSpecificUserReimbursements(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Log.info("Retrieving a specific users reimbursements.");
		employee usr = new ObjectMapper().readValue(req.getReader(), employee.class);
		System.out.println(usr);
		 ArrayList<reimbursement> reimlst = reimbursementServices.getUserReimbursement(usr);
		 
		 return reimlst;
	}
	
	public HashMap<String,Integer> getReimStatusCount(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		Log.info("Retrieving the reimbursements status count");
		employee usr = (employee) req.getSession().getAttribute("user");
		HashMap<String,Integer>  reimStatusCount = reimbursementServices.getUserReimbursementCount(usr);
		
		return reimStatusCount;
	}
	
	public void createReimbursement(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		Log.info("Creating a new reimbursements");
		employee user = (employee) req.getSession().getAttribute("user");
		reimbursement reim = new ObjectMapper().readValue(req.getReader(), reimbursement.class);
		reim.setRas_id(1);
		reim.setEmp_id(user.getEmp_id());
		reim.setEmp_username(user.getEmp_UserName());
		
		reimbursementServices.createReimbursement(reim);
	}
	
	public ArrayList<reimbursement> getAllReimbursements(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		Log.info("retrieving all reimbursements");
		return reimbursementServices.getAllReim();
	}
	
	public boolean approveReimbursements(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		Log.info("changing the reimbursement approval status");
		employee user = (employee) req.getSession().getAttribute("user");
		reimbursement reim = new ObjectMapper().readValue(req.getReader(), reimbursement.class);
		reim.setApprovalMgr_id(user.getEmp_id());
		return reimbursementServices.approveReimbursements(reim);
	}

}
