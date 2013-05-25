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

import com.proj425.domain.Client;
import com.proj425.domain.Client;
import com.proj425.domain.Page;
import com.proj425.domain.PageStatus;
import com.proj425.exception.DAOException;
import com.proj425.service.ClientService;
import com.proj425.service.impl.ClientServiceImpl;

public class ClientAdd extends HttpServlet {

	private ClientService client_service = new ClientServiceImpl();

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
				request.getRequestDispatcher("/WEB-INF/pages/client/client_add.jsp").forward(request, response);
				return;
			}
		}

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
		client.setFirst_nm(args.get("first_nm")[0].trim().toLowerCase());
		client.setLast_nm(args.get("last_nm")[0].trim().toLowerCase());
		client.setGender(args.get("gender")[0]);
		client.setPhone_number(args.get("phone_number")[0].trim().toLowerCase());
		client.setZip(args.get("zip")[0].trim().toLowerCase());
		client.setEmail(args.get("email")[0].trim().toLowerCase());

		try {
			client_service.addClient(client);
		} catch (DAOException e) {
			preserveFormInput(request, response);
			PageStatus status = new PageStatus("Client", "Error: \n Database Error!", "/425pj/ClientManage");
			request.setAttribute("page_status", status);
			request.getRequestDispatcher("/WEB-INF/pages/status/update_fail.jsp").forward(request, response);
			return;
		}

		freeFormInput(request, response);
		request.setAttribute("page_status", new PageStatus("Client", "Add Success!", "/425pj/ClientManage"));
		request.getRequestDispatcher("/WEB-INF/pages/status/update_success.jsp").forward(request, response);

	}

	private void preserveFormInput(HttpServletRequest request, HttpServletResponse response) {

		Map<String, String[]> args = request.getParameterMap();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date date = null;

		Client client_add = new Client();

		if (args.get("first_nm")[0].trim().toLowerCase() != "") {
			client_add.setFirst_nm(args.get("first_nm")[0].trim().toLowerCase());
		}
		if (args.get("last_nm")[0].trim().toLowerCase() != "") {
			client_add.setLast_nm(args.get("last_nm")[0].trim().toLowerCase());
		}
		if (args.get("phone_number")[0].trim().toLowerCase() != "") {
			client_add.setPhone_number(args.get("phone_number")[0].trim().toLowerCase());
		}

		if (args.get("dob")[0].trim().toLowerCase() != "") {
			try {
				date = sdf.parse(args.get("dob")[0].trim().toLowerCase());
				client_add.setDob(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		if (args.get("gender")[0].trim().toLowerCase() != "") {
			client_add.setGender(args.get("gender")[0]);
		}
		if (args.get("zip")[0].trim().toLowerCase() != "") {
			client_add.setZip(args.get("zip")[0].trim().toLowerCase());
		}
		if (args.get("email")[0].trim().toLowerCase() != "") {
			client_add.setEmail(args.get("email")[0].trim().toLowerCase());
		}

		HttpSession session = request.getSession();
		session.setAttribute("client_add", client_add);
	}

	private void freeFormInput(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		session.removeAttribute("client_add");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
