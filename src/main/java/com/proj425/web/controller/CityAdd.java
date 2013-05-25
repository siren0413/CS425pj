package com.proj425.web.controller;

import java.io.IOException;
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
import com.proj425.domain.City;
import com.proj425.domain.Client;
import com.proj425.domain.PageStatus;
import com.proj425.domain.Resort;
import com.proj425.exception.DAOException;
import com.proj425.service.CityService;
import com.proj425.service.impl.CityServiceImpl;

public class CityAdd extends HttpServlet {

	private CityService city_service = new CityServiceImpl();

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
				request.getRequestDispatcher("/WEB-INF/pages/city/city_add.jsp").forward(request, response);
				return;
			}
		}

		City city = new City();
		city.setCity(args.get("city")[0].trim().toLowerCase());
		city.setCountry(args.get("country")[0].trim().toLowerCase());

		
		try {
			city_service.addCity(city);
		} catch (DAOException e) {
			PageStatus status = new PageStatus("City","Error: \n Database Error!", "/425pj/CityManage");
			request.setAttribute("page_status", status);
			request.getRequestDispatcher("/WEB-INF/pages/status/update_fail.jsp").forward(request, response);
			return;
		}

		
		freeFormInput(request, response);

		request.setAttribute("page_status", new PageStatus("City", "Add Success!", "/425pj/CityManage"));
		request.getRequestDispatcher("/WEB-INF/pages/status/update_success.jsp").forward(request, response);

	}

	

	private void preserveFormInput(HttpServletRequest request, HttpServletResponse response) {

		Map<String, String[]> args = request.getParameterMap();

		City city_add = new City();

		if (args.get("city")[0].trim().toLowerCase() != "") {
			city_add.setCity(args.get("city")[0].trim().toLowerCase());
		}
		if (args.get("country")[0].trim().toLowerCase() != "") {
			city_add.setCity(args.get("country")[0].trim().toLowerCase());
		}

		HttpSession session = request.getSession();
		session.setAttribute("city_add", city_add);
	}
	
	private void freeFormInput(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		session.removeAttribute("city_add");
	}
	
	

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

	}

}
