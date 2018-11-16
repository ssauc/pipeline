package com.expenseReimbursementSystem.delegate;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.expenseReimbursementSystem.beans.employee;
import com.expenseReimbursementSystem.services.employeeServices;

public class LoginDelegate {
	final static Logger Log = Logger.getLogger(LoginDelegate.class);

	public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String username = req.getParameter("user");
		String password = req.getParameter("pass");
		
		employee empSer = employeeServices.login(username, password);
		//System.out.println(empSer);
		if(empSer.getEmp_UserName() == null) {
			Log.info("User has no session");
			resp.sendRedirect("./html/index.html");	
			
			
		} else if( empSer.getRole_id() == 0) {
			Log.info("A manager Sign in");
			HttpSession session = req.getSession();
			session.setAttribute("user", empSer);
			resp.sendRedirect("./html/mgrHome.html");
		}else {
			Log.info("A user logged in");
			HttpSession session = req.getSession();
			session.setAttribute("user", empSer);
			resp.sendRedirect("./html/empHomepage.html");			
		
		}
		
	}
	
	public void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Log.info("User is logging out");
		req.getSession().invalidate();
		resp.sendRedirect("./html/index.html");
	}

}
