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

public class ClientUpdate extends HttpServlet {

	private ClientService client_service = new ClientServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getSession().getAttribute("account") == null) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}

		Map<String, String[]> args = request.getParameterMap();

		Client client = new Client();

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
		client.setClient_id(args.get("client_id")[0]);
		client.setFirst_nm(args.get("first_nm")[0].trim().toLowerCase());
		client.setLast_nm(args.get("last_nm")[0].trim().toLowerCase());
		client.setGender(args.get("gender")[0]);
		client.setPhone_number(args.get("phone_number")[0].trim().toLowerCase());
		client.setZip(args.get("zip")[0].trim().toLowerCase());
		client.setEmail(args.get("email")[0].trim().toLowerCase());

		try {
			client_service.updateClient(client);
		} catch (DAOException e) {
			PageStatus status = new PageStatus("Client", "Error: \n Database Error!", "/425pj/ClientManage");
			request.setAttribute("page_status", status);
			request.getRequestDispatcher("/WEB-INF/pages/status/update_fail.jsp").forward(request, response);
			return;
		}

		request.setAttribute("page_status", new PageStatus("Client", "Update Success!", "/425pj/ClientManage"));
		request.getRequestDispatcher("/WEB-INF/pages/status/update_success.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
