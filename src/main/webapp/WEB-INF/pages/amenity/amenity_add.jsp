<%@page import="com.proj425.domain.Amenity"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>Amenity Management</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<jsp:include page="/navigation.jsp"></jsp:include>


<body style="text-align: center" leftmargin="10px" rightmargin="10px" topmargin="20px">
	<h1 align="center">AGENT ADD</h1>
	<br>



	<form method="post" action="/425pj/AmenityAdd" target="right">

		<table align="center" width="50%" border="3" cellpadding="0" cellspacing="0">
			

			<tr>
				<td>Amenity Name: &nbsp;</td>
				<td><input type="text" name="am_nm"  size="10px"></td>
			</tr>

			<tr>
				<td colspan="2" align="center" ><br> <input class="sub" type="submit" name="submit" value="Add" style="width:80; height:25" align="middle"/></td>
				
			</tr>

		</table>

	</form>


</body>



</html>
