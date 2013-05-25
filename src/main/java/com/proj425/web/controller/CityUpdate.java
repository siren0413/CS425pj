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

import com.proj425.domain.City;
import com.proj425.domain.PageStatus;
import com.proj425.exception.DAOException;
import com.proj425.service.CityService;
import com.proj425.service.impl.CityServiceImpl;

public class CityUpdate extends HttpServlet {

	
	private CityService city_service = new CityServiceImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getSession().getAttribute("account")==null) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}

		Map<String, String[]> args = request.getParameterMap();

		City city = new City();
		city.setCity_id(args.get("city_id")[0].trim().toLowerCase());
		city.setCity(args.get("city")[0].trim().toLowerCase());
		city.setCountry(args.get("country")[0].trim().toLowerCase());

		try {
			city_service.updateCity(city);
		} catch (DAOException e) {
			PageStatus status = new PageStatus("City","Error: \n Database Error!", "/425pj/CityManage");
			request.setAttribute("page_status", status);
			request.getRequestDispatcher("/WEB-INF/pages/status/update_fail.jsp").forward(request, response);
			return;
		}
		

		request.setAttribute("page_status", new PageStatus("City", "Update Success!", "/425pj/CityManage"));
		request.getRequestDispatcher("/WEB-INF/pages/status/update_success.jsp").forward(request, response);
	}
	
	
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
