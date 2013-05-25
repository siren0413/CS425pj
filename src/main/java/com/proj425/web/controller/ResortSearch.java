package com.proj425.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.proj425.domain.Amenity;
import com.proj425.domain.City;
import com.proj425.domain.PageStatus;
import com.proj425.domain.Resort;
import com.proj425.domain.SunRating;
import com.proj425.exception.DAOException;
import com.proj425.service.ResortService;
import com.proj425.service.impl.ResortServiceImpl;

public class ResortSearch extends HttpServlet {

	private ResortService resort_service = new ResortServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getSession().getAttribute("account") == null) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}

		Map<String, String[]> args = request.getParameterMap();

		// resort
		Resort resort = new Resort();

		City city = new City();
		SunRating rating = new SunRating();

		city.setCity(args.get("city")[0].trim().toLowerCase());
		city.setCountry(args.get("country")[0].trim().toLowerCase());
		resort.setCity(city);

		rating.setRating(args.get("rating")[0]);
		resort.setRating(rating);

		resort.setResort_id("");
		resort.setResort_nm(args.get("resort_nm")[0].trim().toLowerCase());
		resort.setPhone_number(args.get("phone_number")[0].trim().toLowerCase());
		resort.setAddress(args.get("address")[0].trim().toLowerCase());

		List<Resort> resort_list = null;

		try {
			resort_list = resort_service.findResortByCondition(resort);
		} catch (DAOException e) {
			PageStatus status = new PageStatus("Resort", "Error: \n Database Error!", "/425pj/ResortManage");
			request.setAttribute("page_status", status);
			request.getRequestDispatcher("/WEB-INF/pages/status/update_fail.jsp").forward(request, response);
			return;
		}

		// amenity
		List<Resort> resort_list_by_amenity = null;

		if (args.get("amenity") != null && args.get("amenity").length > 0) {

			List<Amenity> amenity_list = new ArrayList<Amenity>();
			Amenity amenity = null;
			for (int i = 0; i < args.get("amenity").length; i++) {
				amenity = new Amenity();
				amenity.setAm_nm(args.get("amenity")[i]);
				amenity_list.add(amenity);
			}

			try {
				resort_list_by_amenity = resort_service.findResortByAmenity(amenity_list);
			} catch (DAOException e) {
				PageStatus status = new PageStatus("Resort", "Error: \n Database Error!", "/425pj/ResortManage");
				request.setAttribute("page_status", status);
				request.getRequestDispatcher("/WEB-INF/pages/status/update_fail.jsp").forward(request, response);
				return;
			}

		}else {
			try {
				resort_list_by_amenity = resort_service.findAllResorts();
			} catch (DAOException e) {
				PageStatus status = new PageStatus("Resort", "Error: \n Database Error!", "/425pj/ResortManage");
				request.setAttribute("page_status", status);
				request.getRequestDispatcher("/WEB-INF/pages/status/update_fail.jsp").forward(request, response);
				return;
			}
		}

		// merge two list
		if (resort_list_by_amenity != null && resort_list != null) {
			List<Resort> new_resort_list = new ArrayList<Resort>();
			for (Resort r : resort_list) {
				for (Resort r2 : resort_list_by_amenity) {
					if (r.getResort_id().equals(r2.getResort_id()))
						new_resort_list.add(r);
				}
			}
			resort_list = new_resort_list;
		}else {
			resort_list = null;
		}


		// set the attribute and forward to .jsp to display the result
		request.setAttribute("resort_list", resort_list);
		request.getRequestDispatcher("/WEB-INF/pages/resort/resort_list.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
