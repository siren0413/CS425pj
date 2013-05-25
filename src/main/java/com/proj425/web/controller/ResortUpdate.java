package com.proj425.web.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.proj425.domain.Agent;
import com.proj425.domain.City;
import com.proj425.domain.PageStatus;
import com.proj425.domain.Resort;
import com.proj425.domain.SunRating;
import com.proj425.exception.DAOException;
import com.proj425.service.CityService;
import com.proj425.service.ResortService;
import com.proj425.service.impl.CityServiceImpl;
import com.proj425.service.impl.ResortServiceImpl;

public class ResortUpdate extends HttpServlet {

	private ResortService resort_service = new ResortServiceImpl();
	private CityService city_service = new CityServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getSession().getAttribute("account") == null) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}

		Map<String, String[]> args = request.getParameterMap();

		Resort resort = new Resort();

		City city = new City();
		SunRating rating = new SunRating();

		city.setCity(args.get("city")[0].trim().toLowerCase());
		city.setCountry(args.get("country")[0].trim().toLowerCase());
		resort.setCity(city);

		String id = checkExist(city);
		if (id != null) {
			city.setCity_id(id);
		} else {
			request.getRequestDispatcher("/WEB-INF/pages/resort/no_city.jsp").forward(request, response);
			return;
		}
		resort.setCity(city);

		rating.setRating(args.get("rating")[0]);
		resort.setRating(rating);

		resort.setResort_id(args.get("resort_id")[0].trim().toLowerCase());
		resort.setResort_nm(args.get("resort_nm")[0].trim().toLowerCase());
		resort.setPhone_number(args.get("phone_number")[0].trim().toLowerCase());
		resort.setAddress(args.get("address")[0].trim().toLowerCase());

		try {
			resort_service.updateResort(resort);
		} catch (DAOException e) {
			PageStatus status = new PageStatus("Resort", "Error: \n Database Error!", "/425pj/ResortManage");
			request.setAttribute("page_status", status);
			request.getRequestDispatcher("/WEB-INF/pages/status/update_fail.jsp").forward(request, response);
			return;
		}

		request.setAttribute("page_status", new PageStatus("Resort", "Update Success!", "/425pj/ResortManage"));
		request.getRequestDispatcher("/WEB-INF/pages/status/update_success.jsp").forward(request, response);

	}

	private String checkExist(City city) {

		List<City> city_list = city_service.findCityByCondition(city);
		if (city_list == null || city_list.size() == 0) {
			return null;
		} else if (city_list.get(0).getCity_id().equals(city.getCity_id())) {
			return null;
		}

		return city_list.get(0).getCity_id();

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
