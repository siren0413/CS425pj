package com.proj425.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.proj425.domain.PageStatus;
import com.proj425.exception.DAOException;
import com.proj425.service.BookingService;
import com.proj425.service.impl.BookingServiceImpl;

public class BookingDelete extends HttpServlet {

	private BookingService booking_service = new BookingServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getSession().getAttribute("account")==null) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		
		
		Map<String, String[]> args = request.getParameterMap();

		if (args == null || args.get("delete_list") == null || args.get("delete_list").length == 0) {
			request.getRequestDispatcher("/BookingManage").forward(request, response);
			return;
		}

		String booking_id_set = "'" + args.get("delete_list")[0] + "'";

		for (int i = 1; i < args.get("delete_list").length; i++) {

			booking_id_set += " , " + "'" + args.get("delete_list")[i] + "'";
		}

		
		
		try {
			booking_service.deleteBookingSet(booking_id_set);
		} catch (DAOException e) {
			PageStatus status = new PageStatus("Booking","Error: \n Database Error!", "/425pj/BookingManage");
			request.setAttribute("page_status", status);
			request.getRequestDispatcher("/WEB-INF/pages/status/update_fail.jsp").forward(request, response);
			return;
		}


		request.setAttribute("page_status", new PageStatus("Booking", "Delete Success!", "/425pj/BookingManage"));

		request.getRequestDispatcher("/WEB-INF/pages/status/update_success.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
