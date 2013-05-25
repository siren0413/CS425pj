package com.proj425.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.proj425.domain.PageStatus;
import com.proj425.service.AgentService;
import com.proj425.service.impl.AgentServiceImpl;
import com.proj425.utils.CommUtils;

public class AgentDelete extends HttpServlet {

	private AgentService agent_service = new AgentServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getSession().getAttribute("account")==null) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		
		Map<String, String[]> args = request.getParameterMap();

		if (args == null || args.get("delete_list")==null ||  args.get("delete_list").length == 0) {
			request.getRequestDispatcher("/AgentManage").forward(request, response);
			return;
		}

		String agent_id_set = "'" + args.get("delete_list")[0] + "'";

		for (int i = 1; i < args.get("delete_list").length; i++) {

			agent_id_set += " , " + "'" + args.get("delete_list")[i] + "'";
		}

		try {
			agent_service.deleteAgentSet(agent_id_set);
		} catch (Exception e) {
			PageStatus status = new PageStatus("Agent", "Error: \n Database Error!", "/425pj/AgentManage");
			request.setAttribute("page_status", status);
			request.getRequestDispatcher("/WEB-INF/pages/status/update_fail.jsp").forward(request, response);
			return;
		}
		

		request.setAttribute("page_status", new PageStatus("Agent", "Delete Success!", "/425pj/AgentManage"));
		request.getRequestDispatcher("/WEB-INF/pages/status/update_success.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
