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
import com.proj425.domain.Amenity;
import com.proj425.domain.PageStatus;
import com.proj425.exception.DAOException;
import com.proj425.service.AmenityService;
import com.proj425.service.impl.AmenityServiceImpl;

public class AmenitySearch extends HttpServlet {

	
	private AmenityService amenity_service = new AmenityServiceImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		if(request.getSession().getAttribute("account")==null) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		
		Map<String, String[]> args = request.getParameterMap();

		if (args == null || args.get("am_nm")==null ||  args.get("am_nm")[0].trim().toLowerCase().equals("")) {
			request.getRequestDispatcher("/AmenityManage").forward(request, response);
			return;
		}
		
		
		String am_nm = args.get("am_nm")[0].trim().toLowerCase();
		
		List<Amenity> amenity_list = null;
		
		try {
			amenity_list = amenity_service.findAmenityFuzzy(am_nm);
		} catch (DAOException e) {
			PageStatus status = new PageStatus("Amenity","Error: \n Database Error!", "/425pj/AmenityManage");
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
