<%@page import="com.proj425.domain.Booking"%>
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

<title>Booking Management</title>

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
	<h1 align="center">BOOKING ADD</h1>
	<br>

	
	<div>
		<span ${empty_value=="true"? 'style="display: run-in; color: red;"':'style="display: none; color: red;"' }> Please fill all boxes </span>
	</div>

	<form method="post" action="/425pj/BookingAdd" target="right">

		<table align="center" width="50%" border="3" cellpadding="0" cellspacing="0">
			

			<tr>
				<td>Client First Name: &nbsp;</td>
				<td><input type="text" name="c_first_nm"  size="10px" value="${booking_add.client.first_nm }"></td>
			</tr>

			<tr>
				<td>Client Last Name: &nbsp;</td>
				<td><input type="text" name="c_last_nm"  size="10px" value="${booking_add.client.last_nm }"></td>
			</tr>
			
			<tr>
				<td>Client Phone Number: &nbsp;</td>
				<td><input type="text" name="c_phone_number"  size="10px" value="${booking_add.client.phone_number }" ></td>
			</tr>
			
			
		
			<tr>
				<td>Booking Date: &nbsp;</td>
				<td><input type="text" name="book_date"  size="10px"  readonly value="<fmt:formatDate value="<%= new Date() %>" pattern="MM/dd/yyyy" />" /> <span>(mm/dd/yyyy)</span></td>
				
			</tr>
			
			<tr>
				<td>Arrive Date: &nbsp;</td>
				<td><input type="text" name="arrive_date"  size="10px" value="<fmt:formatDate value="${booking_add.arrive_date }" pattern="MM/dd/yyyy" />"> <span>(mm/dd/yyyy)</span></td>
				
			</tr>
			
			<tr>
				<td>Departure Date: &nbsp;</td>
				<td><input type="text" name="departure_date"  size="10px" value="<fmt:formatDate value="${booking_add.departure_date }" pattern="MM/dd/yyyy" />"> <span>(mm/dd/yyyy)</span></td>
			</tr>
			
			<tr>
				<td>Resort Name: &nbsp;</td>
				<td><input type="text" name="resort_nm"  size="10px" value="${booking_add.resort.resort_nm }" ></td>
			</tr>
			
			<tr>
				<td>Resort Phone: &nbsp;</td>
				<td><input type="text" name="resort_phone_number"  size="10px" value="${booking_add.resort.phone_number }"></td>
			</tr>

			<tr>
				<td>Room Type: &nbsp;</td>
				<td><select name="room_type">
						<option value="Standard Double" ${booking_add.room_type=='Standard Double'?'selected':'' } >Standard Double</option>
						<option value="Standard Queen" ${booking_add.room_type=='Standard Queen'?'selected':'' }>Standard Queen</option>
						<option value="Standard King" ${booking_add.room_type=='Standard King'?'selected':'' }>Standard King</option>
						<option value="All-Inclusive" ${booking_add.room_type=='All-Inclusive'?'selected':'' }>All-Inclusive</option>
				</select></td>
			</tr>
			
			<tr>
				<td>Activity: &nbsp;</td>
				<td><select name="activity">
						<option value="Vacation" ${booking_add.activity=='Vacation'?'selected':'' } >Vacation</option>
						<option value="Business" ${booking_add.activity=='Business'?'selected':'' } >Business</option>
				</select></td>
			</tr>
			
			
			<tr>
				<td>Agent First Name: &nbsp;</td>
				<td><input type="text" name="a_first_nm"  size="10px" value="${booking_add.agent.first_nm }"></td>
			</tr>

			<tr>
				<td>Agent Last Name: &nbsp;</td>
				<td><input type="text" name="a_last_nm"  size="10px" value="${booking_add.agent.last_nm }"></td>
			</tr>
			
			<tr>
				<td>Agent Phone Number: &nbsp;</td>
				<td><input type="text" name="a_phone_number"  size="10px" value="${booking_add.agent.phone_number }"></td>
			</tr>

			<tr>
				<td colspan="2" align="center" ><br> <input class="sub" type="submit" name="submit" value="Add" style="width:80; height:25" align="middle"/></td>
				
			</tr>

		</table>

	</form>


</body>



</html>
