package com.proj425.web.UI;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.proj425.domain.Resort;
import com.proj425.domain.Booking;

public class ResortAddUI extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getSession().getAttribute("account")==null) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		
		Booking booking_add = (Booking) request.getSession().getAttribute("booking_add");

		if (booking_add != null) {
			Resort resort_add = new Resort();
			resort_add.setResort_nm(booking_add.getResort().getResort_nm());
			resort_add.setPhone_number(booking_add.getResort().getPhone_number());
			request.getSession().setAttribute("resort_add", resort_add);

		}
		
		request.getRequestDispatcher("/WEB-INF/pages/resort/resort_add.jsp").forward(request, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
