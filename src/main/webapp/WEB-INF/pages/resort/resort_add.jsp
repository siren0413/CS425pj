<%@page import="com.proj425.domain.Resort"%>
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

<title>Resort Management</title>

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
	<h1 align="center">RESORT ADD</h1>
	<br>

	<div>
		<span ${empty_value=="true"? 'style="display: run-in; color: red;"':'style="display: none; color: red;"' }> Please fill all boxes </span>
	</div>

	<form method="get" action="/425pj/ResortAdd" target="right">

		<table align="center" width="50%" border="3" cellpadding="0" cellspacing="0">


			<tr>
				<td>Resort Name: &nbsp;</td>
				<td><input type="text" name="resort_nm" size="10px" value="${resort_add.resort_nm }"></td>
			</tr>

			<tr>
				<td>City Name: &nbsp;</td>
				<td><input type="text" name="city" size="10px" value="${resort_add.city.city }"></td>
			</tr>

			<tr>
				<td>Country Name: &nbsp;</td>
				<td><input type="text" name="country" size="10px" value="${resort_add.city.country }"></td>
			</tr>

			<tr>
				<td>Phone Number: &nbsp;</td>
				<td><input type="text" name="phone_number" size="10px" value="${resort_add.phone_number }"></td>
			</tr>


			<tr>
				<td>Sun Rating: &nbsp;</td>
				<td><select name="rating">
						<option value="1" value="${resort_add.rating.rating=='1'?'selected':'' }">one sun</option>
						<option value="2" value="${resort_add.rating.rating=='2'?'selected':'' }">two sun</option>
						<option value="3" value="${resort_add.rating.rating=='3'?'selected':'' }">three sun</option>
				</select></td>
			</tr>


			<tr>
				<td>Address: &nbsp;</td>
				<td><input type="text" name="address" size="10px" value="${resort_add.address }"></td>
			</tr>

			<tr>
				<td colspan="2" align="center"><br> <input class="sub" type="submit" name="submit" value="Add" style="width:80; height:25" align="middle" /></td>

			</tr>

		</table>

	</form>


</body>



</html>
