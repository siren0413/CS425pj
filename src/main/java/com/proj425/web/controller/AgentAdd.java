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
import javax.servlet.http.HttpSession;

import com.proj425.domain.Agent;
import com.proj425.domain.Booking;
import com.proj425.domain.Client;
import com.proj425.domain.PageStatus;
import com.proj425.domain.Resort;
import com.proj425.service.AgentService;
import com.proj425.service.impl.AgentServiceImpl;
import com.proj425.utils.CommUtils;

public class AgentAdd extends HttpServlet {

	private AgentService agent_service = new AgentServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getSession().getAttribute("account") == null) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}

		Map<String, String[]> args = request.getParameterMap();

		// check empty box
		java.util.Collection<String[]> values = args.values();
		for (String[] s : values) {
			if ("".equals(s[0].trim().toLowerCase())) {
				preserveFormInput(request, response);
				request.setAttribute("empty_value", "true");
				request.getRequestDispatcher("/WEB-INF/pages/agent/agent_add.jsp").forward(request, response);
				return;
			}
		}

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
		agent.setFirst_nm(args.get("first_nm")[0].trim().toLowerCase());
		agent.setLast_nm(args.get("last_nm")[0].trim().toLowerCase());
		agent.setGender(args.get("gender")[0]);
		agent.setPhone_number(args.get("phone_number")[0].trim().toLowerCase());
		agent.setZip(args.get("zip")[0].trim().toLowerCase());
		agent.setEmail(args.get("email")[0].trim().toLowerCase());
		agent.setPosition(args.get("position")[0]);

		try {
			agent_service.addAgent(agent);
		} catch (Exception e) {
			preserveFormInput(request, response);
			PageStatus status = new PageStatus("Agent", "Error: \n Database Error!", "/425pj/AgentManage");
			request.setAttribute("page_status", status);
			request.getRequestDispatcher("/WEB-INF/pages/status/update_fail.jsp").forward(request, response);
			return;
		}

		freeFormInput(request, response);
		request.setAttribute("page_status", new PageStatus("Agent", "Add Success!", "/425pj/AgentManage"));
		request.getRequestDispatcher("/WEB-INF/pages/status/update_success.jsp").forward(request, response);

	}

	private void preserveFormInput(HttpServletRequest request, HttpServletResponse response) {

		Map<String, String[]> args = request.getParameterMap();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date date = null;

		Agent agent_add = new Agent();

		if (args.get("first_nm")[0].trim().toLowerCase() != "") {
			agent_add.setFirst_nm(args.get("first_nm")[0].trim().toLowerCase());
		}
		if (args.get("last_nm")[0].trim().toLowerCase() != "") {
			agent_add.setLast_nm(args.get("last_nm")[0].trim().toLowerCase());
		}
		if (args.get("phone_number")[0].trim().toLowerCase() != "") {
			agent_add.setPhone_number(args.get("phone_number")[0].trim().toLowerCase());
		}

		if (args.get("dob")[0].trim().toLowerCase() != "") {
			try {
				date = sdf.parse(args.get("dob")[0].trim().toLowerCase());
				agent_add.setDob(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		if (args.get("gender")[0].trim().toLowerCase() != "") {
			agent_add.setGender(args.get("gender")[0]);
		}
		if (args.get("zip")[0].trim().toLowerCase() != "") {
			agent_add.setZip(args.get("zip")[0].trim().toLowerCase());
		}
		if (args.get("email")[0].trim().toLowerCase() != "") {
			agent_add.setEmail(args.get("email")[0].trim().toLowerCase());
		}

		if (args.get("position")[0].trim().toLowerCase() != "") {
			agent_add.setPosition(args.get("position")[0]);
		}

		HttpSession session = request.getSession();
		session.setAttribute("agent_add", agent_add);
	}

	private void freeFormInput(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		session.removeAttribute("agent_add");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
