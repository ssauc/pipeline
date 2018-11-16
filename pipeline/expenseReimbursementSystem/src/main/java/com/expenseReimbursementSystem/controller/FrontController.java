package com.expenseReimbursementSystem.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.servlets.DefaultServlet;

import com.expenseReimbursementSystem.beans.employee;

public class FrontController extends DefaultServlet{

	private static final long serialVersionUID = 1552053818249147424L;
	
	private RequestHelper rh = new RequestHelper();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		if(request.getRequestURI().substring(request.getContextPath().length())
				.startsWith("/html/")) {
			super.doGet(request, response);
		} else {
			System.out.println(request.getRequestURI().substring(request.getContextPath().length()));
			rh.process(request, response);
		}
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		//System.out.println(request.getRequestURI().substring(request.getContextPath().length())); // /login
		//System.out.println(request.getRequestURI().substring(request.getContextPath().length()+1)); // login
		HttpSession session = request.getSession(false);
		employee empSer = (employee) request.getSession().getAttribute("user");
		System.out.println(empSer);
		doGet(request,response);
	}
	
}
