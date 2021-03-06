package com.proj425.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.proj425.domain.PageStatus;
import com.proj425.exception.DAOException;
import com.proj425.service.CityService;
import com.proj425.service.impl.CityServiceImpl;

public class CityDelete extends HttpServlet {

	private CityService city_service = new CityServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getSession().getAttribute("account")==null) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		
		Map<String, String[]> args = request.getParameterMap();

		if (args == null || args.get("delete_list")==null ||  args.get("delete_list").length == 0) {
			request.getRequestDispatcher("/CityManage").forward(request, response);
			return;
		}

		String city_id_set = "'" + args.get("delete_list")[0].trim().toLowerCase() + "'";

		for (int i = 1; i < args.get("delete_list").length; i++) {

			city_id_set += " , " + "'" + args.get("delete_list")[i] + "'";
		}

		
		try {
			city_service.deleteCitySet(city_id_set);
		} catch (DAOException e) {
			PageStatus status = new PageStatus("City","Error: \n Database Error!", "/425pj/CityManage");
			request.setAttribute("page_status", status);
			request.getRequestDispatcher("/WEB-INF/pages/status/update_fail.jsp").forward(request, response);
			return;
		}
		
		request.setAttribute("page_status", new PageStatus("City", "Delete Success!", "/425pj/CityManage"));
		request.getRequestDispatcher("/WEB-INF/pages/status/update_success.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
