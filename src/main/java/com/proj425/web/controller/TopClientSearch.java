package com.proj425.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.proj425.domain.Client;
import com.proj425.domain.PageStatus;
import com.proj425.exception.DAOException;
import com.proj425.service.BookingService;
import com.proj425.service.impl.BookingServiceImpl;

public class TopClientSearch extends HttpServlet {

	private BookingService booking_service = new BookingServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getSession().getAttribute("account")==null) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		
		Map<String, String[]> args = request.getParameterMap();

		if (args == null || args.get("start_date") == null || args.get("end_date") == null) {
			request.getRequestDispatcher("/BookingManage").forward(request, response);
			return;
		}

		String start_date = args.get("start_date")[0].trim().toLowerCase();
		String end_date = args.get("end_date")[0].trim().toLowerCase();
		

		List<Client> client_list = null;
		try {
			client_list = booking_service.findTopClient(start_date, end_date);
		} catch (DAOException e) {
			PageStatus status = new PageStatus("Resort", "Error: \n Database Error!", "/425pj/ResortManage");
			request.setAttribute("page_status", status);
			request.getRequestDispatcher("/WEB-INF/pages/status/update_fail.jsp").forward(request, response);
			return;
		}
		
		request.setAttribute("client_list", client_list);
		request.getRequestDispatcher("/WEB-INF/pages/client/client_list.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
