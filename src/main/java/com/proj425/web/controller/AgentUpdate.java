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
import com.proj425.domain.PageStatus;
import com.proj425.service.AgentService;
import com.proj425.service.impl.AgentServiceImpl;

public class AgentUpdate extends HttpServlet {

	private AgentService agent_service = new AgentServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getSession().getAttribute("account") == null) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}

		Map<String, String[]> args = request.getParameterMap();

		Agent agent = new Agent();

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

		agent.setAgent_id(args.get("agent_id")[0].trim().toLowerCase());
		agent.setFirst_nm(args.get("first_nm")[0].trim().toLowerCase());
		agent.setLast_nm(args.get("last_nm")[0].trim().toLowerCase());
		agent.setGender(args.get("gender")[0]);
		agent.setPhone_number(args.get("phone_number")[0].trim().toLowerCase());
		agent.setZip(args.get("zip")[0].trim().toLowerCase());
		agent.setEmail(args.get("email")[0].trim().toLowerCase());
		agent.setPosition(args.get("position")[0]);

		try {
			agent_service.updateAgent(agent);
		} catch (Exception e) {
			PageStatus status = new PageStatus("Agent", "Error: \n Database Error!", "/425pj/AgentManage");
			request.setAttribute("page_status", status);
			request.getRequestDispatcher("/WEB-INF/pages/status/update_fail.jsp").forward(request, response);
			return;
		}

		request.setAttribute("page_status", new PageStatus("Agent", "Update Success!", "/425pj/AgentManage"));
		request.getRequestDispatcher("/WEB-INF/pages/status/update_success.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
