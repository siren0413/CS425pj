package com.proj425.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
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
import com.proj425.domain.City;
import com.proj425.domain.Client;
import com.proj425.domain.Page;
import com.proj425.domain.PageStatus;
import com.proj425.domain.Resort;
import com.proj425.domain.SunRating;
import com.proj425.exception.DAOException;
import com.proj425.service.AgentService;
import com.proj425.service.BookingService;
import com.proj425.service.ClientService;
import com.proj425.service.ResortService;
import com.proj425.service.impl.AgentServiceImpl;
import com.proj425.service.impl.BookingServiceImpl;
import com.proj425.service.impl.ClientServiceImpl;
import com.proj425.service.impl.ResortServiceImpl;

public class BookingUpdate extends HttpServlet {

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

		// check empty box
		java.util.Collection<String[]> values = args.values();
		for (String[] s : values) {
			if ("".equals(s[0].trim().toLowerCase())) {
				request.setAttribute("empty_value", "true");
				request.getRequestDispatcher("/WEB-INF/pages/booking/booking_update.jsp").forward(request, response);
				return;
			}
		}

		Booking booking = new Booking();

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date date = null;

		// client
		Client client = new Client();
		if (!args.get("c_dob")[0].trim().toLowerCase().equals("")) {
			try {
				date = sdf.parse(args.get("c_dob")[0].trim().toLowerCase());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		client.setDob(date);
		client.setFirst_nm(args.get("c_first_nm")[0].trim().toLowerCase());
		client.setLast_nm(args.get("c_last_nm")[0].trim().toLowerCase());
		client.setGender(args.get("c_gender")[0]);
		client.setPhone_number(args.get("c_phone_number")[0].trim().toLowerCase());
		client.setZip(args.get("c_zip")[0].trim().toLowerCase());
		client.setEmail(args.get("c_email")[0].trim().toLowerCase());

		booking.setClient(client);

		String id = checkExist(client);
		if (id != null) {
			client.setClient_id(id);
		} else {
			client_service.addClient(client);
			// request.getRequestDispatcher("/WEB-INF/pages/booking/no_client.jsp").forward(request,
			// response);
			// return;
		}

		// agent
		Agent agent = new Agent();
		if (!args.get("a_dob")[0].trim().toLowerCase().equals("")) {
			try {
				date = sdf.parse(args.get("a_dob")[0].trim().toLowerCase());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		agent.setDob(date);
		agent.setFirst_nm(args.get("a_first_nm")[0].trim().toLowerCase());
		agent.setLast_nm(args.get("a_last_nm")[0].trim().toLowerCase());
		agent.setGender(args.get("a_gender")[0]);
		agent.setPhone_number(args.get("a_phone_number")[0].trim().toLowerCase());
		agent.setZip(args.get("a_zip")[0].trim().toLowerCase());
		agent.setEmail(args.get("a_email")[0].trim().toLowerCase());
		agent.setPosition(args.get("position")[0]);

		booking.setAgent(agent);

		id = checkExist(agent);
		if (id != null) {
			agent.setAgent_id(id);
		} else {
			agent_service.addAgent(agent);
			//request.getRequestDispatcher("/WEB-INF/pages/booking/no_agent.jsp").forward(request, response);
			//return;
		}

		// resort
		Resort resort = new Resort();

		City city = new City();
		SunRating rating = new SunRating();

		city.setCity_id(args.get("city_id")[0].trim().toLowerCase());
		resort.setCity(city);

		rating.setRating(args.get("rating")[0].trim().toLowerCase());
		resort.setRating(rating);

		resort.setResort_nm(args.get("resort_nm")[0].trim().toLowerCase());
		resort.setPhone_number(args.get("resort_phone_number")[0].trim().toLowerCase());
		resort.setAddress(args.get("address")[0].trim().toLowerCase());

		booking.setResort(resort);

		id = checkExist(resort);
		if (id != null) {
			resort.setResort_id(id);
		} else {
			request.getRequestDispatcher("/WEB-INF/pages/booking/no_resort.jsp").forward(request, response);
			return;
		}

		// book date
		if (!args.get("book_date")[0].trim().toLowerCase().equals("")) {
			try {
				date = sdf.parse(args.get("book_date")[0].trim().toLowerCase());
				booking.setBook_date(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		// arrive date
		if (!args.get("arrive_date")[0].trim().toLowerCase().equals("")) {
			try {
				date = sdf.parse(args.get("arrive_date")[0].trim().toLowerCase());
				booking.setArrive_date(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		// departure date
		if (!args.get("departure_date")[0].trim().toLowerCase().equals("")) {
			try {
				date = sdf.parse(args.get("departure_date")[0].trim().toLowerCase());
				booking.setDeparture_date(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		booking.setActivity(args.get("activity")[0]);
		booking.setRoom_type(args.get("room_type")[0]);
		booking.setBooking_id(args.get("booking_id")[0]);

		try {
			booking_service.updateBooking(booking);
		} catch (DAOException e) {
			PageStatus status = new PageStatus("Booking", "Error: \n Database Error!", "/425pj/BookingManage");
			request.setAttribute("page_status", status);
			request.getRequestDispatcher("/WEB-INF/pages/status/update_fail.jsp").forward(request, response);
			return;
		}

		PageStatus status = new PageStatus("Booking", "Update Success!", "/425pj/BookingManage");
		request.setAttribute("page_status", status);
		request.getRequestDispatcher("/WEB-INF/pages/status/update_success.jsp").forward(request, response);
	}

	private String checkExist(Client client) {

		List<Client> client_list = client_service.findClientByCondition(client, new Page(1, 1));
		if (client_list == null || client_list.size() == 0) {
			return null;
		}

		return client_list.get(0).getClient_id();

	}

	private String checkExist(Agent agent) {

		List<Agent> agent_list = agent_service.findAgentByCondition(agent, new Page(1, 1));
		if (agent_list == null || agent_list.size() == 0) {
			return null;
		} 

		return agent_list.get(0).getAgent_id();

	}

	private String checkExist(Resort resort) {

		List<Resort> resort_list = resort_service.findResortByCondition(resort);
		if (resort_list == null || resort_list.size() == 0) {
			return null;
		}

		return resort_list.get(0).getResort_id();

	}



	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

	}

}
