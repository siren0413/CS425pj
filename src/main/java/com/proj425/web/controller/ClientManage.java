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

import com.proj425.domain.Client;
import com.proj425.domain.Page;
import com.proj425.domain.PageStatus;
import com.proj425.exception.DAOException;
import com.proj425.service.ClientService;
import com.proj425.service.impl.ClientServiceImpl;

public class ClientManage extends HttpServlet {
	private ClientService client_service = new ClientServiceImpl();

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

		List<Client> client_list = null;
		try {
			Client client = (Client) request.getSession().getAttribute("client_search");
			if (client != null)
				client_list = client_service.findClientByCondition(client, page);
			else
				client_list = client_service.findAllClients(page);
			
		} catch (DAOException e) {
			PageStatus status = new PageStatus("Client", "Error: \n DAO Error!", "/425pj/ClientManage");
			request.setAttribute("page_status", status);
			request.getRequestDispatcher("/WEB-INF/pages/status/update_fail.jsp").forward(request, response);
			return;
		}

		request.setAttribute("client_list", client_list);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/WEB-INF/pages/client/client_list.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

	}

}
