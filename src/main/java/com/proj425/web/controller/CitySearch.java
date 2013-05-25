package com.proj425.web.controller;

import java.io.IOException;
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

public class CitySearch extends HttpServlet {

	private CityService city_service = new CityServiceImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getSession().getAttribute("account")==null) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}

		Map<String, String[]> args = request.getParameterMap();
		
		City city = new City();
		city.setCity_id("");
		city.setCity(args.get("city")[0].trim().toLowerCase());
		city.setCountry(args.get("country")[0].trim().toLowerCase());

		List<City> city_list = null;
		
		try {
			city_list = city_service.findCityByCondition(city);
		} catch (DAOException e) {
			PageStatus status = new PageStatus("City","Error: \n Database Error!", "/425pj/CityManage");
			request.setAttribute("page_status", status);
			request.getRequestDispatcher("/WEB-INF/pages/status/update_fail.jsp").forward(request, response);
			return;
		}
		
		request.setAttribute("city_list", city_list);
		request.getRequestDispatcher("/WEB-INF/pages/city/city_list.jsp").forward(request, response);
		
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
