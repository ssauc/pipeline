package com.expenseReimbursement.test;

import org.junit.Test;

import com.expenseReimbursementSystem.beans.employee;
import com.expenseReimbursementSystem.beans.reimbursement;
import com.revature.dao.employeeDaoImpl;
import com.revature.dao.reimbursementDaoImpl;

import junit.framework.TestCase;

public class expenseReimbursementTest extends TestCase {
	//User test start here
	
	@Test
	public void testCheckUser() {
		employee emp = new employee();
		emp.setEmp_UserName("user2");
		assertTrue(employeeDaoImpl.getInstance().checkEmployee(emp));
	}
	
	public void testGetAllUsers() {
		assertTrue(0<employeeDaoImpl.getInstance().getAllEmployee().size());
	}
	
	public void testGetUser() {
		assertEquals("user2", employeeDaoImpl.getInstance().getEmployee("user2").getEmp_UserName());
	}
	
	public void testLogin() {
		assertEquals("user2",employeeDaoImpl.getInstance().loginEmployee("user2", "1234").getEmp_UserName());
	}
	
	public void testGetAllStatus() {
		assertTrue(0<employeeDaoImpl.getInstance().selectAllStatus().size());
	}
	
	//Reimbursement Start here
	
	public void testReimcheck() {
		reimbursement reim = new reimbursement();
		reim.setReim_id(261);
		assertTrue(reimbursementDaoImpl.getInstance().checkReimbursement(reim));
	}
	
	public void testReimGetAll() {
		assertTrue(0<reimbursementDaoImpl.getInstance().getAllReimbursement().size());
	}
	
	public void testReimGetUserReim() {
		employee emp2 = new employee();
		emp2.setEmp_UserName("bwayne");
		assertTrue(0<reimbursementDaoImpl.getInstance().getUserReimbursement(emp2).size());
	}
	
	public void testReimGetReim() {
		reimbursement reim1 = new reimbursement();
		reim1.setReim_id(261);
		assertEquals(reim1.getReim_id(), reimbursementDaoImpl.getInstance().getReimbursement(261));
	}
}
