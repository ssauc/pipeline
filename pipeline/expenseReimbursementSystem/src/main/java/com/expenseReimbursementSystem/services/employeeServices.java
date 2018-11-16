package com.expenseReimbursementSystem.services;

import java.util.ArrayList;

import com.expenseReimbursementSystem.beans.employee;
import com.expenseReimbursementSystem.beans.status;
import com.revature.dao.employeeDaoImpl;

public class employeeServices {
	private static employeeServices empService;
	
	public static employeeServices getEmployeeService() {
		if(empService == null) {
			empService = new employeeServices();
		}
		return empService;
	}
	
	
	public static employee login(String username, String password) {
		//System.out.println("login");
		return employeeDaoImpl.getInstance().loginEmployee(username, password);
	}
	
	public static boolean updateUserInfo(employee emp) {
		return employeeDaoImpl.getInstance().updateEmployee(emp);
	}
	
	public static ArrayList<employee> getAllEmployees(){
		return employeeDaoImpl.getInstance().getAllEmployee();
	}
	
	public static employee getEmployee(employee emp) {
		return employeeDaoImpl.getInstance().getEmployee(emp.getEmp_UserName());
				
	}
	
	public static void insertStatus(String descr, String type) {
		 employeeDaoImpl.getInstance().insertStatus(descr,type);
	}
	
	public static ArrayList<status> getStatus(){
		return employeeDaoImpl.getInstance().selectAllStatus();
	}
	
	public static boolean insertEmployee(employee emp) {
		return employeeDaoImpl.getInstance().insertEmployee(emp);
	}
	
	public static boolean checkUserName(employee emp) {
		return employeeDaoImpl.getInstance().checkEmployee(emp);
	}
}
