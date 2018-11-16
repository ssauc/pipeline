package com.revature.dao;

import java.util.ArrayList;

import com.expenseReimbursementSystem.beans.employee;

public interface employeeDao {
	public boolean checkEmployee(employee emp);
	public boolean insertEmployee(employee emp);
	public boolean updateEmployee(employee emp);
	public ArrayList<employee> getAllEmployee();
	public employee getEmployee(String username);
}
