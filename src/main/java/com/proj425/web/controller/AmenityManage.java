package com.proj425.web.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.proj425.domain.Amenity;
import com.proj425.domain.PageStatus;
import com.proj425.exception.DAOException;
import com.proj425.service.AmenityService;
import com.proj425.service.ResortService;
import com.proj425.service.impl.AmenityServiceImpl;
import com.proj425.service.impl.ResortServiceImpl;

public class AmenityManage extends HttpServlet {

	private AmenityService amenity_service = new AmenityServiceImpl();
	private ResortService resort_service = new ResortServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getSession().getAttribute("account") == null) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}

		Map<String, String[]> args = request.getParameterMap();

		List<Amenity> amenity_list = null;
		try {
			if (args.get("resort_id")!=null && !"".equals(args.get("resort_id")[0])) {
				request.setAttribute("amenity_search", "true");
				amenity_list = resort_service.findAmenityByResort(args.get("resort_id")[0]);
			} else {
				amenity_list = amenity_service.findAllAmenitys();
			}

		} catch (DAOException e) {
			PageStatus status = new PageStatus("Amenity", "Error: \n Database Error!", "/425pj/AmenityManage");
			request.setAttribute("page_status", status);
			request.getRequestDispatcher("/WEB-INF/pages/status/update_fail.jsp").forward(request, response);
			return;
		}

		request.setAttribute("amenity_list", amenity_list);
		request.getRequestDispatcher("/WEB-INF/pages/amenity/amenity_list.jsp").forward(request, response);

	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
