package com.proj425.web.UI;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.proj425.dao.ClientDAO;
import com.proj425.dao.impl.ClientDAO_Impl;
import com.proj425.domain.Client;
import com.proj425.domain.PageStatus;
import com.proj425.exception.DAOException;
import com.proj425.service.ClientService;
import com.proj425.service.impl.ClientServiceImpl;

public class ClientUpdateUI extends HttpServlet {

	private ClientService client_service = new ClientServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getSession().getAttribute("account") == null) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}

		String client_id = request.getParameter("client_id");

		Client client = null;

		try {
			client = client_service.findClientById(client_id);
		} catch (DAOException e) {
			PageStatus status = new PageStatus("Client", "Error: \n Database Error!", "/425pj/ClientManage");
			request.setAttribute("page_status", status);
			request.getRequestDispatcher("/WEB-INF/pages/status/update_fail.jsp").forward(request, response);
			return;
		}

		request.setAttribute("client", client);
		request.getRequestDispatcher("/WEB-INF/pages/client/client_update.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
