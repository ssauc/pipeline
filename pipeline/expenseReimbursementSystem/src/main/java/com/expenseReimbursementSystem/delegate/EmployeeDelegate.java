package com.expenseReimbursementSystem.delegate;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.expenseReimbursementSystem.beans.employee;
import com.expenseReimbursementSystem.beans.status;
import com.expenseReimbursementSystem.services.employeeServices;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EmployeeDelegate {
	
	public boolean updateUserInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException{

		employee cuser = (employee) req.getSession().getAttribute("user");
		employee emp = new ObjectMapper().readValue(req.getReader(), employee.class);
		emp.setEmp_id(cuser.getEmp_id());
		//System.out.println(emp);
		return employeeServices.updateUserInfo(emp);
	}
	
	public ArrayList<employee> getAllEmployees(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		
		return employeeServices.getAllEmployees();
	}
	
	public employee getEmployee(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		employee emp = new ObjectMapper().readValue(req.getReader(), employee.class);
		return employeeServices.getEmployee(emp);
	}
	
	public void insertStatus(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		status st = new ObjectMapper().readValue(req.getReader(), status.class);
		//System.out.println(st);
		employeeServices.insertStatus(st.getSt_descr(), st.getSt_type());
	}
	
	public ArrayList<status> selectAllStatus(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		return employeeServices.getStatus();
	}
	
	public boolean insertEmployee(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		employee emp = new ObjectMapper().readValue(req.getReader(), employee.class);
		//System.out.println(emp);
		return employeeServices.insertEmployee(emp);	
	}
	
	public boolean checkUserName(HttpServletRequest req, HttpServletResponse resp) throws IOException{
//		employee usr = (employee) req.getSession().getAttribute("user");
		employee emp = new ObjectMapper().readValue(req.getReader(), employee.class);
		//System.out.println(usr.getEmp_UserName());
		//System.out.println(emp.getEmp_UserName());
//		if(usr.getEmp_UserName().equals(emp.getEmp_UserName())) {
//			return false;
//		}
//		else {
		return employeeServices.checkUserName(emp);
//		}
	}

}
