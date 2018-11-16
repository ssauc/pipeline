package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.expenseReimbursementSystem.beans.employee;
import com.expenseReimbursementSystem.beans.status;
import com.expenseReimbursementSystem.delegate.LoginDelegate;
import com.expenseReimbursementSystem.util.ConnectionUtil;

public class employeeDaoImpl implements employeeDao{
	final static Logger Log = Logger.getLogger(employeeDaoImpl.class);
	private static employeeDaoImpl empDaoImpl;
	private static ConnectionUtil cu = ConnectionUtil.getInstance();
	
	private employeeDaoImpl() {
	}
	
	public static employeeDaoImpl getInstance() {
		if(empDaoImpl == null) {
			empDaoImpl = new employeeDaoImpl();
		}
		return empDaoImpl;
	}

	public boolean checkEmployee(employee emp) {
		Connection conn = null;
		conn = cu.getConnection();
		int count = 0;
		
		String sql = "Select COUNT(*) as count from employee_table where emp_username like ?";
		
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1,emp.getEmp_UserName());
			
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				Log.info("Checking the status of an employee");
				if(rs.getInt("count") >= 1) {
					return true;
				} else {
					return false;
				}
			} 
		}catch(SQLException e) {
			Log.error("Something  went wrong when checking user");
			e.printStackTrace();
		}
		return false;
	}

public boolean insertEmployee(employee emp) {
		Connection conn = null;
		conn = cu.getConnection();
		
		String proc = "CALL insert_employee(?,?,?,?,?,?)";
		
		try {
			PreparedStatement c = conn.prepareCall(proc);
			
			c.setString(1, emp.getEmp_FirstName());
			c.setString(2,emp.getEmp_LastName());
			c.setString(3,emp.getEmp_UserName());
			c.setString(4, emp.getEmp_Password());
			c.setString(5, emp.getEmp_Email());
			c.setInt(6, emp.getRole_id());
			c.executeQuery();
			
			Log.info("inserting a new employee");
			if(checkEmployee(emp) == true) {
				return true;
			} else {
				Log.info("The employee was not added");
				return false;
			}
		} catch (SQLException e) {
			Log.info("Something went wrong when adding a user");
			e.printStackTrace();
		}
		
		return false;
	}

public boolean updateEmployee(employee emp) {
		Connection conn = null;
		conn = cu.getConnection();
		
		String proc = "CALL UPDATE_ACCOUNT(?,?,?,?,?)";
		try {
			PreparedStatement c = conn.prepareCall(proc);
			c.setInt(1, emp.getEmp_id());
			c.setString(2, emp.getEmp_FirstName());
			c.setString(3, emp.getEmp_LastName());
			c.setString(4, emp.getEmp_UserName());
			c.setString(5, emp.getEmp_Email());
			c.executeQuery();
			
		
			if(checkEmployee(emp) == true) {
				Log.info("Updated an employee");
				return true;
			}else {
				Log.warn("The profile of the user was not able to be updated");
				return false;
			}
		}catch(SQLException e) {
			Log.error("There was an error when updating a user");
			e.printStackTrace();
		}
		return false;
	}
	
public ArrayList<employee> getAllEmployee() {
	Connection conn = null;
	conn = cu.getConnection();
	int count = 0;
	
	String sql = "Select * from employee_table";
	try {
		PreparedStatement p = conn.prepareStatement(sql);
		ResultSet rs = p.executeQuery();
		
		ArrayList<employee> arrEmp = new ArrayList<employee>();
		while(rs.next()) {
			employee emp = new employee();
			emp.setEmp_id(rs.getInt("EMP_ID"));
			emp.setEmp_FirstName(rs.getString("EMP_FIRSTNAME"));
			emp.setEmp_LastName(rs.getString("EMP_LASTNAME"));
			emp.setEmp_UserName(rs.getString("EMP_USERNAME"));
			emp.setEmp_Email(rs.getString("EMP_EMAIL"));
			emp.setRole_id(rs.getInt("ROLE_ID"));
			arrEmp.add(emp);
		}
		Log.info("getting all employees");
		return arrEmp;
		
	} catch(SQLException e) {
		Log.error("Something went wrong when getting all employees");
		e.printStackTrace();
	}
		return null;
	}

public employee getEmployee(String username) {
	Connection conn = null;
	conn = cu.getConnection();
	int count = 0;
	
	String sql = "Select * from employee_table where emp_username like ?";
	try {
		PreparedStatement p = conn.prepareStatement(sql);
		p.setString(1, username);
		ResultSet rs = p.executeQuery();
		
		employee emp = new employee();
		while(rs.next()) {
			emp.setEmp_id(rs.getInt("EMP_ID"));
			emp.setEmp_FirstName(rs.getString("EMP_FIRSTNAME"));
			emp.setEmp_LastName(rs.getString("EMP_LASTNAME"));
			emp.setEmp_UserName(rs.getString("EMP_USERNAME"));
			emp.setEmp_Email(rs.getString("EMP_EMAIL"));
			emp.setRole_id(rs.getInt("ROLE_ID"));
		}
		Log.info("Getting an employee");
		return emp;
		
	} catch(SQLException e) {
		Log.error("An error occured when getting an employee");
		e.printStackTrace();
	}
		return null;
	}

public employee loginEmployee(String username, String password) {
	Connection conn = null;
	conn = cu.getConnection();
	int count = 0;
	
	String sql = "Select * from employee_table where emp_password like get_employee_hash(?,?)";
	try {
		PreparedStatement p = conn.prepareStatement(sql);
		p.setString(1, username);
		p.setString(2, password);
		ResultSet rs = p.executeQuery();
		
		employee emp = new employee();
		while(rs.next()) {
			emp.setEmp_id(rs.getInt("EMP_ID"));
			emp.setEmp_FirstName(rs.getString("EMP_FIRSTNAME"));
			emp.setEmp_LastName(rs.getString("EMP_LASTNAME"));
			emp.setEmp_UserName(rs.getString("EMP_USERNAME"));
			emp.setEmp_Email(rs.getString("EMP_EMAIL"));
			emp.setRole_id(rs.getInt("ROLE_ID"));
		}
		Log.info("An employee logged in");
		return emp;
		
	} catch(SQLException e) {
		Log.error("an error occurred when logging in of the employee");
		e.printStackTrace();
	}
		return new employee();
	}

public void insertStatus(String descr, String status){
	Connection conn = null;
	conn = cu.getConnection();
	
	String proc = "CALL UPDATE_STATUS(?,?)";
	try {
		PreparedStatement p = conn.prepareStatement(proc);
		p.setString(1, descr);
		p.setString(2, status);
		
		ResultSet rs = p.executeQuery();
		Log.info("inserting a status");
		
	} catch(SQLException e) {
		Log.error("inserted a status error");
		e.printStackTrace();
	}
}

public ArrayList<status> selectAllStatus() {
	Connection conn = null;
	conn = cu.getConnection();
	
	String sql = "Select * from status_table";
	try {
		PreparedStatement p = conn.prepareStatement(sql);
		ResultSet rs = p.executeQuery();
		
		ArrayList<status> stlst = new ArrayList<status>();
		
		while(rs.next()) {
			status st = new status();
			st.setSt_id(rs.getInt("ST_ID"));
			st.setSt_descr(rs.getString("ST_DESCR"));
			st.setSt_type(rs.getString("ST_TYPE"));
			stlst.add(st);
		}
		Log.info("getting all status");
		return stlst;
	}catch(SQLException e) {
		Log.error("error occurred when getting all status");
		e.printStackTrace();
	}
	return new ArrayList<status>();
}
/*	public static void main(String[] args) {
		employee empl = new employee();
		empl.setEmp_id(3);
		empl.setEmp_UserName("bwayne");
		empl.setEmp_FirstName("Bruce");
		empl.setEmp_LastName("Wayne");
		empl.setEmp_Password("1234");
		empl.setEmp_Email("bwayne@email.com");
		empl.setRole_id(1);
		//System.out.println(empl.getEmp_UserName());
		//employeeDaoImpl.getInstance().insertStatus("Update your reimbursements","Info");
		System.out.println(employeeDaoImpl.getInstance().selectAllStatus());
	}*/

	

	
}
