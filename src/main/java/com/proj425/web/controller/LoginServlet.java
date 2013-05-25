package com.proj425.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.proj425.domain.AgentAccount;
import com.proj425.domain.PageStatus;
import com.proj425.exception.DAOException;
import com.proj425.service.AgentAccountService;
import com.proj425.service.impl.AgentAccountServiceImpl;

public class LoginServlet extends HttpServlet {

	private AgentAccountService agentAccountService = new AgentAccountServiceImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		
		Map<String, String[]> args = request.getParameterMap();
		
		if(args==null || args.get("username")==null || args.get("password")==null || args.get("username")[0].equals("") || args.get("password")[0].equals("")) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		
		String username = args.get("username")[0].trim().toLowerCase();
		String password = args.get("password")[0].trim().toLowerCase();
		
		
		AgentAccount agent_account = null;
		
		try {
			agent_account = agentAccountService.findAgentAccount(username, password);
		} catch (DAOException e) {
			PageStatus status = new PageStatus("Account", "Error: \n Database Error!", "/425pj/login.jsp");
			request.setAttribute("page_status", status);
			request.getRequestDispatcher("/WEB-INF/pages/status/update_fail.jsp").forward(request, response);
			return;
		}
		
		if(agent_account == null) {
			request.setAttribute("account_error", "invalid username/password!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("account", agent_account);
			request.getRequestDispatcher("/home.jsp").forward(request, response);
		}
		
		
		
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
