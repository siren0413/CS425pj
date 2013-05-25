package com.proj425.web.UI;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.proj425.domain.City;
import com.proj425.domain.PageStatus;
import com.proj425.exception.DAOException;
import com.proj425.service.CityService;
import com.proj425.service.impl.CityServiceImpl;

public class CityUpdateUI extends HttpServlet {

	private CityService city_service = new CityServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getSession().getAttribute("account") == null) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}

		String city_id = request.getParameter("city_id");

		City city = null;

		try {
			city = city_service.findCityById(city_id);
		} catch (DAOException e) {
			PageStatus status = new PageStatus("City", "Error: \n Database Error!", "/425pj/CityManage");
			request.setAttribute("page_status", status);
			request.getRequestDispatcher("/WEB-INF/pages/status/update_fail.jsp").forward(request, response);
			return;
		}

		request.setAttribute("city", city);
		request.getRequestDispatcher("/WEB-INF/pages/city/city_update.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

	}

}
