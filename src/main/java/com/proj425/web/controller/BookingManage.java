package com.proj425.web.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.proj425.domain.Booking;
import com.proj425.domain.Page;
import com.proj425.domain.PageStatus;
import com.proj425.exception.DAOException;
import com.proj425.service.BookingService;
import com.proj425.service.impl.BookingServiceImpl;

public class BookingManage extends HttpServlet {

	private BookingService booking_service = new BookingServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getSession().getAttribute("account") == null) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}

		Map<String, String[]> args = request.getParameterMap();

		Page page = new Page();
		int pageIndex = 1;
		if (args != null && args.get("pageIndex") != null) {
			pageIndex = Integer.parseInt(args.get("pageIndex")[0]);
		}
		page.setPageIndex(pageIndex);

		List<Booking> booking_list = null;

		try {

			Booking booking = (Booking) request.getSession().getAttribute("booking_search");
			if (booking != null) {
				String range = (String) request.getSession().getAttribute("booking_search_range");
				if (range != null && range.equals("on"))
					booking_list = booking_service.findBookingByDateRange(booking, page);
				else
					booking_list = booking_service.findBookingByCondition(booking, page);
			} else
				booking_list = booking_service.findAllBookings(page);

		} catch (DAOException e) {
			PageStatus status = new PageStatus("Booking", "Error: \n Database Error!", "/425pj/BookingManage");
			request.setAttribute("page_status", status);
			request.getRequestDispatcher("/WEB-INF/pages/status/update_fail.jsp").forward(request, response);
			return;
		}

		request.setAttribute("page", page);
		request.setAttribute("booking_list", booking_list);
		request.getRequestDispatcher("/WEB-INF/pages/booking/booking_list.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
