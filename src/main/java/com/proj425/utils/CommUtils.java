package com.proj425.utils;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA. User: Siren Date: 4/16/13 Time: 6:28 PM To change
 * this template use File | Settings | File Templates.
 */
public class CommUtils {

	public static String getId() {
		return UUID.randomUUID().toString();
	}

	public static String initCap(String str) {
		if(str==null || str.equals(""))
			return null;
		return str.substring(0,1).toUpperCase()+str.substring(1);
	}

}
