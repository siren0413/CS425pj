package com.proj425.web.controller;

import java.io.IOException;
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

public class ClientSearch extends HttpServlet {

	private ClientService client_service = new ClientServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getSession().getAttribute("account") == null) {
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

		// client
		Client client = new Client();
		client.setClient_id("");
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date dob = null;

		if (!args.get("dob")[0].trim().toLowerCase().equals("")) {
			try {
				dob = sdf.parse(args.get("dob")[0].trim().toLowerCase());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		client.setDob(dob);
		client.setFirst_nm(args.get("first_nm")[0].trim().toLowerCase());
		client.setLast_nm(args.get("last_nm")[0].trim().toLowerCase());
		client.setGender(args.get("gender")[0]);
		client.setPhone_number(args.get("phone_number")[0].trim().toLowerCase());
		client.setZip(args.get("zip")[0].trim().toLowerCase());
		client.setEmail(args.get("email")[0].trim().toLowerCase());

		List<Client> client_list = null;
		try {
			client_list = client_service.findClientByCondition(client, page);
		} catch (DAOException e) {
			PageStatus status = new PageStatus("Client", "Error: \n Database Error!", "/425pj/ClientManage");
			request.setAttribute("page_status", status);
			request.getRequestDispatcher("/WEB-INF/pages/status/update_fail.jsp").forward(request, response);
			return;
		}

		request.setAttribute("client_list", client_list);
		request.getSession().setAttribute("client_search", client);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/WEB-INF/pages/client/client_list.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

	}

}
