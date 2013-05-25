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
import com.proj425.domain.City;
import com.proj425.domain.Client;
import com.proj425.domain.Page;
import com.proj425.domain.PageStatus;
import com.proj425.domain.Resort;
import com.proj425.domain.SunRating;
import com.proj425.exception.DAOException;
import com.proj425.service.CityService;
import com.proj425.service.ResortService;
import com.proj425.service.impl.CityServiceImpl;
import com.proj425.service.impl.ResortServiceImpl;

public class ResortAdd extends HttpServlet {

	private ResortService resort_service = new ResortServiceImpl();
	private CityService city_service = new CityServiceImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getSession().getAttribute("account")==null) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}


		Map<String, String[]> args = request.getParameterMap();
		
		// check empty box
		java.util.Collection<String[]> values = args.values();
		 for(String[] s: values) {
			 if("".equals(s[0])) {
				 preserveFormInput(request, response);
				 request.setAttribute("empty_value", "true");
				 request.getRequestDispatcher("/WEB-INF/pages/resort/resort_add.jsp").forward(request, response);
				 return;
			 }
		 }

		Resort resort = new Resort();
		
		City city = new City();
		SunRating rating = new SunRating();
		
		city.setCity(args.get("city")[0].trim().toLowerCase());
		city.setCountry(args.get("country")[0].trim().toLowerCase());
		resort.setCity(city);
		

		List<City> city_list = null;
		try {
			city_list = city_service.findCityByCondition(city);
		} catch (DAOException e) {
			PageStatus status = new PageStatus("City","Error: \n Database Error!", "/425pj/ResortManage");
			request.setAttribute("page_status", status);
			request.getRequestDispatcher("/WEB-INF/pages/status/update_fail.jsp").forward(request, response);
			return;
		}
		
		if (city_list == null || city_list.size() == 0) {
			preserveFormInput(request, response);
			request.getRequestDispatcher("/WEB-INF/pages/resort/no_city.jsp").forward(request, response);
			return;
		} else {
			String city_id = city_list.get(0).getCity_id();
			city.setCity_id(city_id);
			resort.setCity(city);
		}
		
		
		rating.setRating(args.get("rating")[0]);
		resort.setRating(rating);
		
		
		resort.setResort_nm(args.get("resort_nm")[0].trim().toLowerCase());
		resort.setPhone_number(args.get("phone_number")[0].trim().toLowerCase());
		resort.setAddress(args.get("address")[0].trim().toLowerCase());
		
	
		try {
			resort_service.addResort(resort);
		} catch (DAOException e) {
			PageStatus status = new PageStatus("Resort", "Error: \n Database Error!", "/425pj/ResortManage");
			request.setAttribute("page_status", status);
			request.getRequestDispatcher("/WEB-INF/pages/status/update_fail.jsp").forward(request, response);
			return;
		}
		
		
		freeFormInput(request, response);
		request.setAttribute("page_status", new PageStatus("Resort", "Add Success!", "/425pj/ResortManage"));
		request.getRequestDispatcher("/WEB-INF/pages/status/update_success.jsp").forward(request, response);
		
		
	}
	
	
	
	
private void preserveFormInput(HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, String[]> args = request.getParameterMap();

		
		Resort resort_add = new Resort();
		City city_add = new City();
		SunRating rating_add = new SunRating();

		if (args.get("city")[0].trim().toLowerCase() != "") {
			city_add.setCity((args.get("city")[0].trim().toLowerCase()));
		}
		if (args.get("country")[0].trim().toLowerCase() != "") {
			city_add.setCountry((args.get("country")[0].trim().toLowerCase()));
		}
		
		resort_add.setCity(city_add);
		

		if (args.get("resort_nm")[0].trim().toLowerCase() != "") {
			resort_add.setResort_nm((args.get("resort_nm")[0].trim().toLowerCase()));
		}
		if (args.get("phone_number")[0].trim().toLowerCase() != "") {
			resort_add.setPhone_number((args.get("phone_number")[0].trim().toLowerCase()));
		}

		if (args.get("rating")[0] != "") {
			rating_add.setRating((args.get("rating")[0]));
		}
		
		resort_add.setRating(rating_add);
		
		if (args.get("address")[0].trim().toLowerCase() != "") {
			resort_add.setAddress((args.get("address")[0].trim().toLowerCase()));
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("resort_add", resort_add);
	}
	
	private void freeFormInput(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		session.removeAttribute("resort_add");
	}
	


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

}
