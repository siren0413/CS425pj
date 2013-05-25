package com.proj425.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.proj425.domain.Agent;
import com.proj425.domain.Page;
import com.proj425.domain.PageStatus;
import com.proj425.service.AgentService;
import com.proj425.service.impl.AgentServiceImpl;

public class AgentSearch extends HttpServlet {
	
	
	private AgentService agent_service = new AgentServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getSession().getAttribute("account")==null) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		
		Map<String, String[]> args = request.getParameterMap();
		
		// page
				Page page = new Page();
				int pageIndex = 1;
				if (args != null && args.get("pageIndex") != null) {
					pageIndex = Integer.parseInt(args.get("pageIndex")[0]);
				}
				page.setPageIndex(pageIndex);

		// agent
		Agent agent = new Agent();
		agent.setAgent_id("");
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date dob = null;

		if (!args.get("dob")[0].equals("")) {
			try {
				dob = sdf.parse(args.get("dob")[0].trim().toLowerCase());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		agent.setDob(dob);
		agent.setFirst_nm(args.get("first_nm")[0].trim().toLowerCase());
		agent.setLast_nm(args.get("last_nm")[0].trim().toLowerCase());
		agent.setGender(args.get("gender")[0]);
		agent.setPhone_number(args.get("phone_number")[0].trim().toLowerCase());
		agent.setZip(args.get("zip")[0].trim().toLowerCase());
		agent.setEmail(args.get("email")[0].trim().toLowerCase());
		agent.setPosition(args.get("position")[0]);
			
		
		List<Agent> agent_list = null;

		try {
			agent_list = agent_service.findAgentByCondition(agent, page );
		} catch (Exception e) {
			PageStatus status = new PageStatus("Agent", "Error: \n Database Error!", "/425pj/AgentManage");
			request.setAttribute("page_status", status);
			request.getRequestDispatcher("/WEB-INF/pages/status/update_fail.jsp").forward(request, response);
			return;
		}
		
		request.getSession().setAttribute("agent_search", agent);
		request.setAttribute("page", page);
		request.setAttribute("agent_list", agent_list);
		request.getRequestDispatcher("/WEB-INF/pages/agent/agent_list.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
