package com.proj425.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.proj425.domain.PageStatus;
import com.proj425.domain.Revenue;
import com.proj425.exception.DAOException;
import com.proj425.service.BookingService;
import com.proj425.service.impl.BookingServiceImpl;

public class CheckRevenue extends HttpServlet {

	private BookingService booking_service = new BookingServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getSession().getAttribute("account") == null) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}

		Map<String, String[]> args = request.getParameterMap();

		if (args.get("first_nm") != null && args.get("last_nm") != null && !"".equals(args.get("first_nm")[0].trim().toLowerCase())
				&& !"".equals(args.get("last_nm")[0].trim().toLowerCase())) {

			List<Revenue> revenue_list = booking_service.findRevenueByAgent(args.get("first_nm")[0].trim().toLowerCase(), args.get("last_nm")[0].trim().toLowerCase());
			request.setAttribute("revenue_list", revenue_list);
			request.setAttribute("revenue_obj", "agent");
			request.getRequestDispatcher("/WEB-INF/pages/booking/revenue_list.jsp").forward(request, response);
			return;
		}

		if (args.get("country") != null && !"".equals(args.get("country")[0].trim().toLowerCase())) {

			List<Revenue> revenue_list = booking_service.findRevenueByCountry(args.get("country")[0].trim().toLowerCase());
			request.setAttribute("revenue_list", revenue_list);
			request.setAttribute("revenue_obj", "country");
			request.getRequestDispatcher("/WEB-INF/pages/booking/revenue_list.jsp").forward(request, response);
			return;
		}

		if (args.get("city") != null && !"".equals(args.get("city")[0].trim().toLowerCase())) {

			List<Revenue> revenue_list = booking_service.findRevenueByCity(args.get("city")[0].trim().toLowerCase());
			request.setAttribute("revenue_list", revenue_list);
			request.setAttribute("revenue_obj", "city");
			request.getRequestDispatcher("/WEB-INF/pages/booking/revenue_list.jsp").forward(request, response);
			return;
		}

		
		List<Revenue> revenue_list = null;
		
		try {
			revenue_list = booking_service.findRevenueByAgents();
		} catch (DAOException e) {
			PageStatus status = new PageStatus("Booking","Error: \n Database Error!", "/425pj/BookingManage");
			request.setAttribute("page_status", status);
			request.getRequestDispatcher("/WEB-INF/pages/status/update_fail.jsp").forward(request, response);
			return;
		}
		
		
		
		request.setAttribute("revenue_list", revenue_list);
		request.setAttribute("revenue_obj", "agent");
		request.getRequestDispatcher("/WEB-INF/pages/booking/revenue_list.jsp").forward(request, response);
		return;

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
