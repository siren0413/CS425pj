package com.proj425.web.controller;

import java.io.IOException;

import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.proj425.domain.Amenity;
import com.proj425.domain.PageStatus;
import com.proj425.exception.DAOException;
import com.proj425.service.AmenityService;
import com.proj425.service.impl.AmenityServiceImpl;

public class AmenityAdd extends HttpServlet {

	private AmenityService amenity_service = new AmenityServiceImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getSession().getAttribute("account")==null) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		
		Map<String, String[]> args = request.getParameterMap();

		Amenity amenity = new Amenity();
		amenity.setAm_nm(args.get("am_nm")[0].trim().toLowerCase());
		
	
		try {
			amenity_service.addAmenity(amenity);
		} catch (DAOException e) {
			PageStatus status = new PageStatus("Amenity","Error: \n Database Error!", "/425pj/AmenityManage");
			request.setAttribute("page_status", status);
			request.getRequestDispatcher("/WEB-INF/pages/status/update_fail.jsp").forward(request, response);
			return;
		}
		
		
		request.setAttribute("page_status", new PageStatus("Amenity", "Add Success!", "/425pj/AmenityManage"));
		request.getRequestDispatcher("/WEB-INF/pages/status/update_success.jsp").forward(request, response);
		
	}


	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
