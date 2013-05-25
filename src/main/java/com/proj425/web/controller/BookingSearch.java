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

import com.proj425.domain.Agent;
import com.proj425.domain.Booking;
import com.proj425.domain.Client;
import com.proj425.domain.Page;
import com.proj425.domain.PageStatus;
import com.proj425.domain.Resort;
import com.proj425.exception.DAOException;
import com.proj425.service.BookingService;
import com.proj425.service.impl.BookingServiceImpl;

public class BookingSearch extends HttpServlet {

	private BookingService booking_service = new BookingServiceImpl();

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

		Booking booking = new Booking();

		booking.setBooking_id("");

		// set client
		Client client = new Client();
		client.setClient_id("");
		client.setFirst_nm(args.get("c_first_nm")[0].trim().toLowerCase());
		client.setLast_nm(args.get("c_last_nm")[0].trim().toLowerCase());
		booking.setClient(client);

		// set agent
		Agent agent = new Agent();
		agent.setAgent_id("");
		agent.setFirst_nm(args.get("a_first_nm")[0].trim().toLowerCase());
		agent.setLast_nm(args.get("a_last_nm")[0].trim().toLowerCase());
		booking.setAgent(agent);

		// set resort
		Resort resort = new Resort();
		resort.setResort_id("");
		resort.setResort_nm(args.get("resort_nm")[0].trim().toLowerCase());
		booking.setResort(resort);

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date date = null;

		// set booking date
		if (!args.get("book_date")[0].trim().toLowerCase().equals("")) {
			try {
				date = sdf.parse(args.get("book_date")[0].trim().toLowerCase());
				booking.setBook_date(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		

		// set arrive date
		if (!args.get("arrive_date")[0].trim().toLowerCase().equals("")) {
			try {
				date = sdf.parse(args.get("arrive_date")[0].trim().toLowerCase());
				booking.setArrive_date(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		

		// set departure date
		if (!args.get("departure_date")[0].trim().toLowerCase().equals("")) {
			try {
				date = sdf.parse(args.get("departure_date")[0].trim().toLowerCase());
				booking.setDeparture_date(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		

		// set others
		booking.setRoom_type(args.get("room_type")[0]);
		booking.setActivity(args.get("activity")[0]);

		List<Booking> booking_list = null;

		if (args.get("range") != null && "on".equals(args.get("range")[0])) {

			try {
				booking_list = booking_service.findBookingByDateRange(booking, page);
				request.getSession().setAttribute("booking_search_range", "on");
			} catch (DAOException e) {
				PageStatus status = new PageStatus("Booking", "Error: \n Database Error!", "/425pj/BookingManage");
				request.setAttribute("page_status", status);
				request.getRequestDispatcher("/WEB-INF/pages/status/update_fail.jsp").forward(request, response);
				return;
			}

		} else {

			try {
				booking_list = booking_service.findBookingByCondition(booking, page);
				request.getSession().setAttribute("booking_search_range", "off");
			} catch (DAOException e) {
				PageStatus status = new PageStatus("Booking", "Error: \n Database Error!", "/425pj/BookingManage");
				request.setAttribute("page_status", status);
				request.getRequestDispatcher("/WEB-INF/pages/status/update_fail.jsp").forward(request, response);
				return;
			}
		}

		request.setAttribute("page", page);
		request.getSession().setAttribute("booking_search", booking);
		request.setAttribute("booking_list", booking_list);
		request.getRequestDispatcher("/WEB-INF/pages/booking/booking_list.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
