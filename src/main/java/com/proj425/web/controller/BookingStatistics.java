package com.proj425.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.proj425.domain.Booking;
import com.proj425.domain.Client;
import com.proj425.domain.ClientStatistics;
import com.proj425.domain.PageStatus;
import com.proj425.exception.DAOException;
import com.proj425.service.BookingService;
import com.proj425.service.impl.BookingServiceImpl;

public class BookingStatistics extends HttpServlet {
	
	private BookingService booking_service = new BookingServiceImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getSession().getAttribute("account")==null) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		
		Map<String, String[]> args = request.getParameterMap();

		ClientStatistics clientStatistics = null;
		try {
			clientStatistics = booking_service.findAvgDaysPerTripByClient(args.get("client_id")[0]);
		} catch (DAOException e) {
			PageStatus status = new PageStatus("Booking","Error: \n Database Error!", "/425pj/BookingManage");
			request.setAttribute("page_status", status);
			request.getRequestDispatcher("/WEB-INF/pages/status/update_fail.jsp").forward(request, response);
			return;
		}
		
		
		
		request.setAttribute("clientStatistics", clientStatistics);
		request.getRequestDispatcher("/WEB-INF/pages/booking/booking_statistics.jsp").forward(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}

}
