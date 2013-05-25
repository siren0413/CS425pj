package com.proj425.web.UI;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.proj425.domain.Agent;
import com.proj425.domain.PageStatus;
import com.proj425.exception.DAOException;
import com.proj425.service.AgentService;
import com.proj425.service.impl.AgentServiceImpl;

public class AgentUpdateUI extends HttpServlet {

	private AgentService agent_service = new AgentServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getSession().getAttribute("account") == null) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}

		String agent_id = request.getParameter("agent_id");

		Agent agent = null;

		try {
			agent = agent_service.findAgentById(agent_id);
		} catch (DAOException e) {
			PageStatus status = new PageStatus("Agent", "Error: \n Database Error!", "/425pj/AgentManage");
			request.setAttribute("page_status", status);
			request.getRequestDispatcher("/WEB-INF/pages/status/update_fail.jsp").forward(request, response);
			return;
		}

		request.setAttribute("agent", agent);
		request.getRequestDispatcher("/WEB-INF/pages/agent/agent_update.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

	}

}
