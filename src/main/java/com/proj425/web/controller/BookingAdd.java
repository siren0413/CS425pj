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

import com.proj425.domain.Agent;
import com.proj425.domain.Booking;
import com.proj425.domain.Client;
import com.proj425.domain.Page;
import com.proj425.domain.PageStatus;
import com.proj425.domain.Resort;
import com.proj425.exception.DAOException;
import com.proj425.service.AgentService;
import com.proj425.service.BookingService;
import com.proj425.service.ClientService;
import com.proj425.service.ResortService;
import com.proj425.service.impl.AgentServiceImpl;
import com.proj425.service.impl.BookingServiceImpl;
import com.proj425.service.impl.ClientServiceImpl;
import com.proj425.service.impl.ResortServiceImpl;

public class BookingAdd extends HttpServlet {

	ClientService client_service = new ClientServiceImpl();
	AgentService agent_service = new AgentServiceImpl();
	ResortService resort_service = new ResortServiceImpl();
	BookingService booking_service = new BookingServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getSession().getAttribute("account") == null) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}

		Map<String, String[]> args = request.getParameterMap();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date date = null;

		Booking booking = new Booking();

		// check empty box
		java.util.Collection<String[]> values = args.values();
		for (String[] s : values) {
			if ("".equals(s[0].trim().toLowerCase())) {
				preserveFormInput(request, response);
				request.setAttribute("empty_value", "true");
				request.getRequestDispatcher("/WEB-INF/pages/booking/booking_add.jsp").forward(request, response);
				return;
			}
		}

		// book date
		if (args.get("book_date")[0] != "") {
			try {
				date = sdf.parse(args.get("book_date")[0].trim().toLowerCase());
				booking.setBook_date(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		// arrive date
		if (args.get("arrive_date")[0] != "") {
			try {
				date = sdf.parse(args.get("arrive_date")[0].trim().toLowerCase());
				booking.setArrive_date(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		// departure date
		if (args.get("departure_date")[0] != "") {
			try {
				date = sdf.parse(args.get("departure_date")[0].trim().toLowerCase());
				booking.setDeparture_date(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		// room type
		booking.setRoom_type(args.get("room_type")[0]);
		booking.setActivity(args.get("activity")[0]);

		// client
		Client client = new Client();
		client.setFirst_nm(args.get("c_first_nm")[0].trim().toLowerCase());
		client.setLast_nm(args.get("c_last_nm")[0].trim().toLowerCase());
		client.setPhone_number(args.get("c_phone_number")[0].trim().toLowerCase());

		List<Client> client_list = null;
		try {
			client_list = client_service.findClientByCondition(client, new Page(1, 1));
		} catch (DAOException e) {
			preserveFormInput(request, response);
			PageStatus status = new PageStatus("Client", "Error: \n Database Error!", "/425pj/BookingManage");
			request.setAttribute("page_status", status);
			request.getRequestDispatcher("/WEB-INF/pages/status/update_fail.jsp").forward(request, response);
			return;
		}

		if (client_list == null || client_list.size() == 0) {
			preserveFormInput(request, response);
			request.getRequestDispatcher("/WEB-INF/pages/booking/no_client.jsp").forward(request, response);
			return;
		} else {
			String client_id = client_list.get(0).getClient_id();
			client.setClient_id(client_id);
			booking.setClient(client);
		}

		// agent
		Agent agent = new Agent();
		agent.setFirst_nm(args.get("a_first_nm")[0].trim().toLowerCase());
		agent.setLast_nm(args.get("a_last_nm")[0].trim().toLowerCase());
		agent.setPhone_number(args.get("a_phone_number")[0].trim().toLowerCase());

		List<Agent> agent_list = null;
		try {
			agent_list = agent_service.findAgentByCondition(agent, new Page(1, 1));
		} catch (DAOException e) {
			preserveFormInput(request, response);
			PageStatus status = new PageStatus("Agent", "Error: \n Database Error!", "/425pj/BookingManage");
			request.setAttribute("page_status", status);
			request.getRequestDispatcher("/WEB-INF/pages/status/update_fail.jsp").forward(request, response);
			return;
		}

		if (agent_list == null || agent_list.size() == 0) {
			preserveFormInput(request, response);
			request.getRequestDispatcher("/WEB-INF/pages/booking/no_agent.jsp").forward(request, response);
			return;
		} else {
			String agent_id = agent_list.get(0).getAgent_id();
			agent.setAgent_id(agent_id);
			booking.setAgent(agent);
		}

		// resort
		Resort resort = new Resort();
		resort.setResort_nm(args.get("resort_nm")[0].trim().toLowerCase());
		resort.setPhone_number(args.get("resort_phone_number")[0].trim().toLowerCase());

		List<Resort> resort_list = null;
		try {
			resort_list = resort_service.findResortByCondition(resort);
		} catch (DAOException e) {
			preserveFormInput(request, response);
			PageStatus status = new PageStatus("Resort", "Error: \n Database Error!", "/425pj/BookingManage");
			request.setAttribute("page_status", status);
			request.getRequestDispatcher("/WEB-INF/pages/status/update_fail.jsp").forward(request, response);
			return;
		}

		if (resort_list == null || resort_list.size() == 0) {
			preserveFormInput(request, response);
			request.getRequestDispatcher("/WEB-INF/pages/booking/no_resort.jsp").forward(request, response);
			return;
		} else {
			String resort_id = resort_list.get(0).getResort_id();
			resort.setResort_id(resort_id);
			booking.setResort(resort);
		}

		try {
			booking_service.addBooking(booking);
		} catch (DAOException e) {
			preserveFormInput(request, response);
			PageStatus status = new PageStatus("Booking", "Error: \n Database Error!", "/425pj/BookingManage");
			request.setAttribute("page_status", status);
			request.getRequestDispatcher("/WEB-INF/pages/status/update_fail.jsp").forward(request, response);
			return;
		}

		freeFormInput(request, response);
		request.setAttribute("page_status", new PageStatus("Booking", "Add Success!", "/425pj/BookingManage"));
		request.getRequestDispatcher("/WEB-INF/pages/status/update_success.jsp").forward(request, response);

	}

	private void preserveFormInput(HttpServletRequest request, HttpServletResponse response) {

		Map<String, String[]> args = request.getParameterMap();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date date = null;

		Booking booking_add = new Booking();
		Client booking_client = new Client();
		Agent booking_agent = new Agent();
		Resort booking_resort = new Resort();

		if (args.get("c_first_nm")[0].trim().toLowerCase() != "") {
			booking_client.setFirst_nm(args.get("c_first_nm")[0].trim().toLowerCase());
		}
		if (args.get("c_last_nm")[0].trim().toLowerCase() != "") {
			booking_client.setLast_nm(args.get("c_last_nm")[0].trim().toLowerCase());
		}
		if (args.get("c_phone_number")[0].trim().toLowerCase() != "") {
			booking_client.setPhone_number(args.get("c_phone_number")[0].trim().toLowerCase());
		}

		booking_add.setClient(booking_client);
		
		if (args.get("book_date")[0].trim().toLowerCase() != "") {
			try {
				date = sdf.parse(args.get("book_date")[0].trim().toLowerCase());
				booking_add.setBook_date(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		if (args.get("arrive_date")[0].trim().toLowerCase() != "") {
			try {
				date = sdf.parse(args.get("arrive_date")[0].trim().toLowerCase());
				booking_add.setArrive_date(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (args.get("departure_date")[0].trim().toLowerCase() != "") {
			try {
				date = sdf.parse(args.get("departure_date")[0].trim().toLowerCase());
				booking_add.setDeparture_date(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		if (args.get("resort_nm")[0].trim().toLowerCase() != "") {
			booking_resort.setResort_nm((args.get("resort_nm")[0].trim().toLowerCase()));
		}
		if (args.get("resort_phone_number")[0].trim().toLowerCase() != "") {
			booking_resort.setPhone_number((args.get("resort_phone_number")[0].trim().toLowerCase()));
		}

		booking_add.setResort(booking_resort);

		if (args.get("room_type")[0] != "") {
			booking_add.setRoom_type((args.get("room_type")[0]));
		}
		if (args.get("activity")[0] != "") {
			booking_add.setActivity((args.get("activity")[0]));
		}

		if (args.get("a_first_nm")[0].trim().toLowerCase() != "") {
			booking_agent.setFirst_nm(args.get("a_first_nm")[0].trim().toLowerCase());
		}
		if (args.get("a_last_nm")[0].trim().toLowerCase() != "") {
			booking_agent.setLast_nm(args.get("a_last_nm")[0].trim().toLowerCase());
		}
		if (args.get("a_phone_number")[0].trim().toLowerCase() != "") {
			booking_agent.setPhone_number(args.get("a_phone_number")[0].trim().toLowerCase());
		}

		booking_add.setAgent(booking_agent);

		HttpSession session = request.getSession();
		session.setAttribute("booking_add", booking_add);
	}

	private void freeFormInput(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		session.removeAttribute("booking_add");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
