package com.proj425.web.UI;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.proj425.domain.Booking;
import com.proj425.domain.Client;

public class ClientAddUI extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("account")==null) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		Booking booking_add = (Booking)request.getSession().getAttribute("booking_add");
		
		if(booking_add!=null) {
			Client client_add = new Client();
			client_add.setFirst_nm(booking_add.getClient().getFirst_nm());
			client_add.setLast_nm(booking_add.getClient().getLast_nm());
			client_add.setPhone_number(booking_add.getClient().getPhone_number());
			client_add.setEmail(booking_add.getClient().getEmail());
			client_add.setGender(booking_add.getClient().getGender());
			client_add.setZip(booking_add.getClient().getZip());
			request.getSession().setAttribute("client_add", client_add);
			
		
		}
		
		request.getRequestDispatcher("/WEB-INF/pages/client/client_add.jsp").forward(request, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
