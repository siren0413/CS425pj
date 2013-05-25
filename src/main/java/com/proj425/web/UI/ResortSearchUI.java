package com.proj425.web.UI;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.proj425.domain.Amenity;
import com.proj425.service.ResortService;
import com.proj425.service.impl.ResortServiceImpl;

public class ResortSearchUI extends HttpServlet {

	
	private ResortService resort_service = new ResortServiceImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		if(request.getSession().getAttribute("account")==null) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		
		
		List<Amenity> amenity_list = resort_service.findAllAmenities();
		
		
		request.setAttribute("amenity_list", amenity_list);
		request.getRequestDispatcher("/WEB-INF/pages/resort/resort_search_UI.jsp").forward(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
