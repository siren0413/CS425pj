package com.proj425.web.UI;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.proj425.domain.Booking;
import com.proj425.domain.Agent;

public class AgentAddUI extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getSession().getAttribute("account") == null) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}

		Booking booking_add = (Booking) request.getSession().getAttribute("booking_add");

		if (booking_add != null) {
			Agent agent_add = new Agent();
			agent_add.setFirst_nm(booking_add.getAgent().getFirst_nm());
			agent_add.setLast_nm(booking_add.getAgent().getLast_nm());
			agent_add.setPhone_number(booking_add.getAgent().getPhone_number());
			request.getSession().setAttribute("agent_add", agent_add);

		}

		request.getRequestDispatcher("/WEB-INF/pages/agent/agent_add.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);

	}

}
