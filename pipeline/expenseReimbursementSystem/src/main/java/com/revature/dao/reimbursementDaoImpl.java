package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;

import com.expenseReimbursementSystem.beans.employee;
import com.expenseReimbursementSystem.beans.reimbursement;
import com.expenseReimbursementSystem.util.ConnectionUtil;

public class reimbursementDaoImpl implements reimbursementDao {
	private static reimbursementDaoImpl reimDaoImpl;
	private static ConnectionUtil cu = ConnectionUtil.getInstance();
	
	private reimbursementDaoImpl() {
		
	}
	
	public static reimbursementDaoImpl getInstance() {
		if(reimDaoImpl == null) {
			reimDaoImpl = new reimbursementDaoImpl();
		}
		return reimDaoImpl;
	}

	public boolean checkReimbursement(reimbursement reim) {
		Connection conn = null;
		conn = cu.getConnection();
		int count = 0;
		
		String sql = "Select COUNT(*) as count from reimbursement_table where reim_id = ?";
		
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setInt(1,reim.getReim_id());
			
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				if(rs.getInt("count") >= 1) {
					return true;
				} else {
					return false;
				}
			} 
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean insertReimbursement(reimbursement reim) {
		Connection conn = null;
		conn = cu.getConnection();
		
		String proc = "CALL insert_reimbursement(?,?,?,?,?)";
		try {
			CallableStatement c = conn.prepareCall(proc);
			
			c.setInt(1, reim.getRtype_id());
			c.setString(2, reim.getReim_descr());
			c.setInt(3, reim.getRas_id());
			c.setInt(4, reim.getEmp_id());
			c.setFloat(5, reim.getReim_amount());
//			c.setNull(6, Types.NULL);
			c.executeQuery();
			
			if(checkReimbursement(reim)== true) {
				return true;
			} else {
				return false;
			}
			
		} catch (SQLException e) {
			e.getStackTrace();
		}
		return false;
	}

	public boolean updateReimbursement(reimbursement reim) {
		Connection conn = null;
		conn = cu.getConnection();
		
		String proc = "CALL UPDATE_REIMBURSEMENT(?,?,?)";
		try {
			CallableStatement c = conn.prepareCall(proc);
			
			c.setInt(1,reim.getReim_id());
			c.setInt(2,reim.getApprovalMgr_id());
			c.setInt(3,reim.getRas_id());
			c.executeQuery();
			
			return true;
		} catch (SQLException e) {
			e.getStackTrace();
		}
		return false;
	}
	
	public ArrayList<reimbursement> getAllReimbursement() {
		Connection conn = null;
		conn = cu.getConnection();
		int count = 0;
		
		String sql = "Select * from reimbursement_table rt left join employee_table et on rt.emp_id = et.emp_id " + 
				"left join reimbursement_type_table rtt on rt.rtype_id = rtt.rtype_id " + 
				"left join reimbursement_approval_status ras on rt.ras_id = ras.ras_id " + 
				"left join employee_table mgr on rt.approvalmgr_id = mgr.emp_id";
		try {
			PreparedStatement p = conn.prepareStatement(sql);
			ResultSet rs = p.executeQuery();
			
			ArrayList<reimbursement> arrReim = new ArrayList<reimbursement>();
			while(rs.next()) {
				reimbursement reim = new reimbursement();
				reim.setReim_id(rs.getInt("REIM_ID"));
				reim.setRtype_id(rs.getInt("RTYPE_ID"));
				reim.setRtype_descr(rs.getString("RTYPE_DESCR"));
				reim.setReim_descr(rs.getString("REIM_DESCR"));
				reim.setRas_id(rs.getInt("RAS_ID"));
				reim.setRas_descr(rs.getString("RAS_DESCR"));
				reim.setEmp_id(rs.getInt("EMP_ID"));
				reim.setEmp_username(rs.getString("EMP_USERNAME"));
				reim.setReim_amount(rs.getFloat("REIM_AMOUNT"));
				reim.setApprovalMgr_id(rs.getInt("APPROVALMGR_ID"));
				arrReim.add(reim);
			}
			return arrReim;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
			return null;
		}
	
	public ArrayList<reimbursement> getUserReimbursement(employee emp) {
		Connection conn = null;
		conn = cu.getConnection();
		int count = 0;
		
		String sql = "Select * from reimbursement_table rt left join employee_table et on rt.emp_id = et.emp_id " + 
				"left join reimbursement_type_table rtt on rt.rtype_id = rtt.rtype_id " + 
				"left join reimbursement_approval_status ras on rt.ras_id = ras.ras_id " + 
				"left join employee_table mgr on rt.approvalmgr_id = mgr.emp_id where et.emp_username like ?";
		try {
			PreparedStatement p = conn.prepareStatement(sql);
			
			p.setString(1, emp.getEmp_UserName());
			
			ResultSet rs = p.executeQuery();
			ArrayList<reimbursement> arrReim = new ArrayList<reimbursement>();
			while(rs.next()) {
				reimbursement reim = new reimbursement();
				reim.setReim_id(rs.getInt("REIM_ID"));
				reim.setRtype_id(rs.getInt("RTYPE_ID"));
				reim.setRtype_descr(rs.getString("RTYPE_DESCR"));
				reim.setReim_descr(rs.getString("REIM_DESCR"));
				reim.setRas_id(rs.getInt("RAS_ID"));
				reim.setRas_descr(rs.getString("RAS_DESCR"));
				reim.setEmp_id(rs.getInt("EMP_ID"));
				reim.setEmp_username(rs.getString("EMP_USERNAME"));
				reim.setReim_amount(rs.getFloat("REIM_AMOUNT"));
				reim.setApprovalMgr_id(rs.getInt("APPROVALMGR_ID"));
				arrReim.add(reim);
			}
			return arrReim;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
			return null;
		}

	public HashMap<String,Integer> getUserReimbursementCount(employee emp){
		Connection conn = null;
		conn = cu.getConnection();
		
		String sql = "Select ras.ras_descr, count(*) as count from reimbursement_table rt" + 
				" Left Join reimbursement_approval_status ras on rt.ras_id = ras.ras_id" + 
				" where rt.emp_id = ? group by ras.ras_descr";
		try {
			PreparedStatement p = conn.prepareStatement(sql);
			p.setInt(1, emp.getEmp_id());
			
			ResultSet rs = p.executeQuery();
			ArrayList<String> arStatusc = new ArrayList<String>();
			HashMap<String, Integer> map = new HashMap<>(); 
			while(rs.next()) {
				map.put(rs.getString("RAS_DESCR"), rs.getInt("COUNT"));
				//arStatusc.add(rs.getString("RAS_DESCR") + ":" + rs.getInt("COUNT"));
			}
			return map;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new HashMap<String,Integer>();
		
	}
	public reimbursement getReimbursement(int id) {
		Connection conn = null;
		conn = cu.getConnection();
		
		String sql = "Select * from reimbursement_table rt left join employee_table et on rt.emp_id = et.emp_id " + 
				"left join reimbursement_type_table rtt on rt.rtype_id = rtt.rtype_id " + 
				"left join reimbursement_approval_status ras on rt.ras_id = ras.ras_id " + 
				"left join employee_table mgr on rt.approvalmgr_id = mgr.emp_id where reim_id = ?";
		try {
			PreparedStatement p = conn.prepareStatement(sql);
			p.setInt(1,id);
			
			ResultSet rs = p.executeQuery();
			
			reimbursement reim = new reimbursement();
			while(rs.next()) {
				reim.setReim_id(rs.getInt("REIM_ID"));
				reim.setRtype_id(rs.getInt("RTYPE_ID"));
				reim.setRtype_descr(rs.getString("RTYPE_DESCR"));
				reim.setReim_descr(rs.getString("REIM_DESCR"));
				reim.setRas_id(rs.getInt("RAS_ID"));
				reim.setRas_descr(rs.getString("RAS_DESCR"));
				reim.setEmp_id(rs.getInt("EMP_ID"));
				reim.setEmp_username(rs.getString("EMP_USERNAME"));
				reim.setReim_amount(rs.getFloat("REIM_AMOUNT"));
				reim.setApprovalMgr_id(rs.getInt("APPROVALMGR_ID"));
			}
			return reim;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
			return null;
		}
	
//	public static void main(String[] args) {
//		reimbursement reim = new reimbursement();
//		reim.setReim_id(1);
//		
//		reim.setApprovalMgr_id(1);
//		reim.setRas_id(0);
//		reim.setRtype_id(3);
//		reim.setReim_descr("Lobster");
//		reim.setRas_id(1);
//		reim.setEmp_id(2);
//		reim.setReim_amount((float) 50.50);
////		
////		c.setInt(1, reim.getRtype_id());
////		c.setString(2, reim.getReim_descr());
////		c.setString(3, reim.getRas_descr());
////		c.setInt(4, reim.getEmp_id());
////		c.setFloat(5, reim.getReim_amount());
////		c.setNull(6, Types.NULL);
//		
////		employee empl = new employee();
////		empl.setEmp_id(3);
////		empl.setEmp_UserName("mmurdock");
////		empl.setEmp_FirstName("Tony");
////		empl.setEmp_LastName("stark");
////		empl.setEmp_Password("1234");
////		empl.setEmp_Email("tstark@email.com");
////		empl.setRole_id(1);
//		
//	 System.out.println( reimbursementDaoImpl.getInstance().insertReimbursement(reim));
//	}



}
