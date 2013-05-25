package com.proj425.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.proj425.domain.PageStatus;
import com.proj425.domain.Resort;
import com.proj425.exception.DAOException;
import com.proj425.service.ResortService;
import com.proj425.service.impl.ResortServiceImpl;

public class ResortManage extends HttpServlet {
	
	private ResortService resort_service = new ResortServiceImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getSession().getAttribute("account")==null) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		
		request.getSession().removeAttribute("booking_add");
		
		List<Resort> resort_list = null;
		
		try {
			resort_list = resort_service.findAllResorts();
		} catch (DAOException e) {
			PageStatus status = new PageStatus("Resort", "Error: \n Database Error!", "/425pj/ResortManage");
			request.setAttribute("page_status", status);
			request.getRequestDispatcher("/WEB-INF/pages/status/update_fail.jsp").forward(request, response);
			return;
		}
		
		request.setAttribute("resort_list", resort_list);
		request.getRequestDispatcher("/WEB-INF/pages/resort/resort_list.jsp").forward(request, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
