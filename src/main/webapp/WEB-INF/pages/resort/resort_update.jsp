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
	<h1 align="center">RESORT UPDATE</h1>
	<br>



	<form method="post" action="/425pj/ResortUpdate" target="right">

		<table align="center" width="50%" border="3" cellpadding="0" cellspacing="0">
			

			<tr>
				<td>Resort Name: &nbsp;</td>
				<td><input type="text" name="resort_nm" value="${resort.resort_nm }" size="10px"></td>
			</tr>

			<tr>
				<td>City Name: &nbsp;</td>
				<td><input type="text" name="city" value="${resort.city.city }" size="10px"></td>
			</tr>
			
			<tr>
				<td>Country Name: &nbsp;</td>
				<td><input type="text" name="country" value="${resort.city.country }" size="10px"></td>
			</tr>
			
			
			<tr>
				<td>Phone Number: &nbsp;</td>
				<td><input type="text" name="phone_number" value="${resort.phone_number }" size="10px"></td>
			</tr>
				
			<tr>
				<td>Sun Rating: &nbsp;</td>
				<td><select name="rating">
						<option value="1" ${ resort.rating.rating=='1'? 'selected':'' }>one sun</option>
						<option value="2" ${ resort.rating.rating=='2'? 'selected':'' }>two sun</option>
						<option value="3" ${ resort.rating.rating=='3'? 'selected':'' }>three sun</option>
				</select></td>
			</tr>
			
			<tr>
				<td>Address: &nbsp;</td>
				<td><input type="text" name="address" value="${resort.address }" size="10px"></td>
			</tr>


			<tr>
				<td colspan="2" align="center" ><br> <input class="sub" type="submit" name="submit" value="update" style="width:80; height:25" align="middle"/></td>
				<td><input type="hidden" name="resort_id" value="${resort.resort_id }" size="10px"></td>
			</tr>

		</table>

	</form>


</body>



</html>
