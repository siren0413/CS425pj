package com.proj425.utils;

import java.io.*;
import java.sql.*;
import java.util.*;

public class JDBC_Conn {

	private static String driverClassName;
	private static String protocol;
	private static String url;
	private static String username;
	private static String password;

	static {

		InputStream in = JDBC_Conn.class.getClassLoader().getResourceAsStream("jdbc_config.properties");
		Properties prop = new Properties();
		try {

			prop.load(in);

			driverClassName = prop.getProperty("driverClassName");
			protocol = prop.getProperty("protocol");
			url = prop.getProperty("url");
			username = prop.getProperty("username");
			password = prop.getProperty("password");

			Class.forName(driverClassName);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static Connection getConnection() throws Exception {
		return DriverManager.getConnection(protocol + username + "/" + password + url);
	}

	public static void releaseConnection(Connection conn, Statement stmt, ResultSet rs) {

		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (stmt != null) {
					try {
						stmt.close();
					} catch (Exception e2) {
						e2.printStackTrace();
					} finally {
						if (conn != null) {
							try {
								conn.close();
							} catch (Exception e3) {
								e3.printStackTrace();
							}
						}
						conn = null;
					}
				}
				stmt = null;
			}
		}

		rs = null;
	}

}
