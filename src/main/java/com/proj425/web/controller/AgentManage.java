package com.proj425.web.controller;

import java.io.IOException;
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

public class AgentManage extends HttpServlet {

	private AgentService agent_service = new AgentServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getSession().getAttribute("account") == null) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		
		request.getSession().removeAttribute("booking_add");

		Map<String, String[]> args = request.getParameterMap();

		Page page = new Page();
		int pageIndex = 1;
		if (args != null && args.get("pageIndex") != null) {
			pageIndex = Integer.parseInt(args.get("pageIndex")[0]);
		}
		page.setPageIndex(pageIndex);

		List<Agent> agent_list = null;

		try {

			Agent agent = (Agent) request.getSession().getAttribute("agent_search");
			if (agent != null)
				agent_list = agent_service.findAgentByCondition(agent, page);
			else
				agent_list = agent_service.findAllAgents(page);
		} catch (Exception e) {
			PageStatus status = new PageStatus("Agent", "Error: \n Database Error!", "/425pj/AgentManage");
			request.setAttribute("page_status", status);
			request.getRequestDispatcher("/WEB-INF/pages/status/update_fail.jsp").forward(request, response);
			return;
		}

		request.setAttribute("page", page);
		request.setAttribute("agent_list", agent_list);
		request.getRequestDispatcher("/WEB-INF/pages/agent/agent_list.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

	}

}
