package com.proj425.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.proj425.domain.Agent;
import com.proj425.service.AgentService;
import com.proj425.service.impl.AgentServiceImpl;

public class ClientPerAgent extends HttpServlet {

	private AgentService agent_service = new AgentServiceImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Agent> agent_list = agent_service.findClientPerAgent();
		
		
		request.setAttribute("agent_list", agent_list);
		request.getRequestDispatcher("/WEB-INF/pages/agent/client_list.jsp").forward(request, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
