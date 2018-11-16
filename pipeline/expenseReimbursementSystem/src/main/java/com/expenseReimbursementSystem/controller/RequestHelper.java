package com.expenseReimbursementSystem.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.expenseReimbursementSystem.beans.employee;
import com.expenseReimbursementSystem.beans.reimbursement;
import com.expenseReimbursementSystem.beans.status;
import com.expenseReimbursementSystem.delegate.EmployeeDelegate;
import com.expenseReimbursementSystem.delegate.LoginDelegate;
import com.expenseReimbursementSystem.delegate.ReimbursementDelegate;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RequestHelper {
	final static Logger Log = Logger.getLogger(RequestHelper.class);
	private LoginDelegate ld = new LoginDelegate();
	private ReimbursementDelegate rd = new ReimbursementDelegate();
	private EmployeeDelegate ed = new EmployeeDelegate();
	
	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String switchString = req.getRequestURI().substring(req.getContextPath().length()+1);
		while(switchString.indexOf("/")>0) {
			switchString = switchString.substring(0, switchString.indexOf("/"));
		}
		/*System.out.println("before reuqests");
		employee usr = (employee) req.getSession().getAttribute("user");
		if(usr == null && !switchString.equals("login")) {
			resp.sendRedirect("./html/index.html");
			System.out.println("in if");
		}
		
		System.out.println("after if reuqests");
		*/	
		switch (switchString) {
		case "login":
			Log.info("user logging in");
			ld.login(req,resp);
			break;
		case "logout":
			Log.info("user logging out");
			ld.logout(req, resp);
			break;
		case "employee":
			Log.info("Getting the employee");
			employee empSer = (employee) req.getSession().getAttribute("user");
			ObjectMapper mapper = new ObjectMapper();
			resp.setHeader("Content-Type", "applicateion/json");
			mapper.writeValue(resp.getOutputStream(),empSer);
			break;
		case "reimbursements":
			Log.info("Getting the reimbursements");
			ArrayList<reimbursement> arlst = (ArrayList<reimbursement>) rd.getUserReimbursements(req,resp);
			//System.out.println(arlst);
			ObjectMapper reimMapper = new ObjectMapper();
			resp.setHeader("Content-Type", "applicateion/json");
			reimMapper.writeValue(resp.getOutputStream(),arlst);
			break;
		case "countReimbursements":
			Log.info("Counting the reimbursements");
			employee emp = (employee) req.getSession().getAttribute("user");
			HashMap<String,Integer> arReimCount = rd.getReimStatusCount(req, resp);
			//System.out.println(arReimCount);
			ObjectMapper arReimCountMapper = new ObjectMapper();
			resp.setHeader("Content-Type", "application/json");
			arReimCountMapper.writeValue(resp.getOutputStream(), arReimCount);
			break;
		case "insertReimbursement":
			Log.info("Inserting the reimbursements");
			rd.createReimbursement(req, resp);
			break;
		case "updateUserInfo":
			Log.info("Updating the user infor");
			boolean updated = ed.updateUserInfo(req,resp);
			ObjectMapper usi = new ObjectMapper();
			resp.setHeader("Content-Type", "application/json");
			usi.writeValue(resp.getOutputStream(),updated);
			
			req.getSession().invalidate();
			resp.sendRedirect("./html/index.html");
			break;
//		Managers calls
		case "viewAllEmployees":
			Log.info("Viewing all employees");
			ArrayList<employee> emplst = ed.getAllEmployees(req, resp);
			ObjectMapper lstm = new ObjectMapper();
			resp.setHeader("Content-Type", "application/json");
			lstm.writeValue(resp.getOutputStream(), emplst);
			break;
		case "viewAllReimbursements":
			Log.info("Viewing all employees");
			ArrayList<reimbursement> reimlst = rd.getAllReimbursements(req, resp);
			//System.out.println(reimlst);
			ObjectMapper reimm = new ObjectMapper();
			resp.setHeader("Content-Type", "application/json");
			reimm.writeValue(resp.getOutputStream(), reimlst);
			break;
		case "approveReimbursement":
			Log.info("Approve the reimbursements");
			boolean appreim = rd.approveReimbursements(req, resp);
			break;
		case "viewRequestsFromUser":
			Log.info("Viewing the request from the user");
			ArrayList<reimbursement> userReimlst = rd.getSpecificUserReimbursements(req, resp);
			System.out.println(userReimlst);
			ObjectMapper mapuserreimlst = new ObjectMapper();
			mapuserreimlst.writeValue(resp.getOutputStream(), userReimlst);
			break;
		case "viewEmployee":
			Log.info("Viewing the employee");
			employee username = ed.getEmployee(req, resp);
			//System.out.println(username);
			ObjectMapper viewEmployeemap = new ObjectMapper();
			viewEmployeemap.writeValue(resp.getOutputStream(), username);
		case "insertStatus":
			Log.info("Inserting the status");
			ed.insertStatus(req, resp);
			break;
		case "selectAllStatus":
			Log.info("Selecting the status");
			ArrayList<status> stl = ed.selectAllStatus(req, resp);
			//System.out.println(stl);
			ObjectMapper allStatusMapper = new ObjectMapper();
			allStatusMapper.writeValue(resp.getOutputStream(), stl);
			break;
		case "insertEmployee":
			Log.info("Inserting the employee");
			ed.insertEmployee(req, resp);
			break;
		case "checkUserName":
			Log.info("checking the user");
			boolean chk = ed.checkUserName(req, resp);
			System.out.println(chk);
			ObjectMapper checkUser = new ObjectMapper();
			checkUser.writeValue(resp.getOutputStream(), chk);
		default:
			break;
		}
		
	}

}
