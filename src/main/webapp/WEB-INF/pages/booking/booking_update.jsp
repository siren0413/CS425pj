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
	<h1 align="center">BOOKING UPDATE</h1>
	<br>

	<div>
		<span ${empty_value=="true"? 'style="display: run-in; color: red;"':'style="display: none; color: red;"' }> Please fill all boxes </span>
	</div>

	<form method="post" action="/425pj/BookingUpdate" target="right">

		<table align="center" width="50%" border="3" cellpadding="0" cellspacing="0">


			<tr style="background-color:#E8F1F7" align="center">
				<td colspan="2">Client</td>
			</tr>
			<tr>
				<td>Client First Name: &nbsp;</td>
				<td><input type="text" name="c_first_nm" value="${booking.client.first_nm }" size="10px"></td>
			</tr>

			<tr>
				<td>Client Last Name: &nbsp;</td>
				<td><input type="text" name="c_last_nm" value="${booking.client.last_nm }" size="10px"></td>
			</tr>


			<tr>
				<td>Gender: &nbsp;</td>
				<td><select name="c_gender">
						<option value="M" ${ booking.client.gender=='M'? 'selected':'' }>Male</option>
						<option value="F" ${ booking.client.gender=='F'? 'selected':'' }>Female</option>
				</select></td>
			</tr>

			<tr>
				<td>Birth Date: &nbsp;</td>
				<td><input type="text" name="c_dob" value='<fmt:formatDate value="${booking.client.dob }" pattern="MM/dd/yyyy"/>' size="10px"></td>
			</tr>

			<tr>
				<td>Client Phone Number: &nbsp;</td>
				<td><input type="text" name="c_phone_number" value="${booking.client.phone_number }" size="10px"></td>
			</tr>

			<tr>
				<td>Zip Code: &nbsp;</td>
				<td><input type="text" name="c_zip" value="${booking.client.zip }" size="10px"></td>
			</tr>

			<tr>
				<td>Email: &nbsp;</td>
				<td><input type="text" name="c_email" value="${booking.client.email }" size="15px"></td>
			</tr>



			<tr style="background-color:#E8F1F7" align="center">
				<td colspan="2">Date &nbsp;</td>
			</tr>

			<tr>
				<td>Booking Date: &nbsp;</td>
				<td><input type="text" name="book_date" value='<fmt:formatDate value="${booking.book_date }" pattern="MM/dd/yyyy"/>' size="10px"></td>
			</tr>

			<tr>
				<td>Arrive Date: &nbsp;</td>
				<td><input type="text" name="arrive_date" value='<fmt:formatDate value="${booking.arrive_date }" pattern="MM/dd/yyyy"/>' size="10px"></td>
			</tr>

			<tr>
				<td>Departure Date: &nbsp;</td>
				<td><input type="text" name="departure_date" value='<fmt:formatDate value="${booking.departure_date }" pattern="MM/dd/yyyy"/>' size="10px"></td>
			</tr>

			<tr style="background-color:#E8F1F7" align="center">
				<td colspan="2">Resort</td>
			</tr>

			<tr>
				<td>Resort Name: &nbsp;</td>
				<td><input type="text" name="resort_nm" value="${booking.resort.resort_nm }" size="10px"></td>
			</tr>

			<tr>
				<td>Resort Phone Number: &nbsp;</td>
				<td><input type="text" name="resort_phone_number" value="${booking.resort.phone_number }" size="10px"></td>
			</tr>

			<tr>
				<td>Resort Address: &nbsp;</td>
				<td><input type="text" name="address" value="${booking.resort.address }" size="10px"></td>
			</tr>

			<tr>
				<td>Rating: &nbsp;</td>
				<td><select name="rating">
						<option value="1" ${ booking.resort.rating.rating=='1'? 'selected':'' }>one sun</option>
						<option value="2" ${ booking.resort.rating.rating=='2'? 'selected':'' }>two sun</option>
						<option value="3" ${ booking.resort.rating.rating=='3'? 'selected':'' }>three sun</option>
				</select></td>
				<td><input type="hidden" name="city_id" value="${booking.resort.city.city_id }" size="10px"></td>
			</tr>

			<tr style="background-color:#E8F1F7" align="center">
				<td colspan="2">Agent</td>
			</tr>

			<tr>
				<td>Agent First Name: &nbsp;</td>
				<td><input type="text" name="a_first_nm" value="${booking.agent.first_nm }" size="10px"></td>
			</tr>

			<tr>
				<td>Agent Last Name: &nbsp;</td>
				<td><input type="text" name="a_last_nm" value="${booking.agent.last_nm }" size="10px"></td>
			</tr>

			<tr>
				<td>Gender: &nbsp;</td>
				<td><select name="a_gender">
						<option value="M" ${ booking.agent.gender=='M'? 'selected':'' }>Male</option>
						<option value="F" ${ booking.agent.gender=='F'? 'selected':'' }>Female</option>
				</select></td>
			</tr>

			<tr>
				<td>Birth Date: &nbsp;</td>
				<td><input type="text" name="a_dob" value='<fmt:formatDate value="${booking.agent.dob }" pattern="MM/dd/yyyy"/>' size="10px"></td>
			</tr>

			<tr>
				<td>Agent Phone Number: &nbsp;</td>
				<td><input type="text" name="a_phone_number" value="${booking.agent.phone_number }" size="10px"></td>
			</tr>

			<tr>
				<td>Zip Code: &nbsp;</td>
				<td><input type="text" name="a_zip" value="${booking.agent.zip }" size="10px"></td>
			</tr>

			<tr>
				<td>Email: &nbsp;</td>
				<td><input type="text" name="a_email" value="${booking.agent.email }" size="15px"></td>
			</tr>
			<tr>
				<td>Position: &nbsp;</td>
				<td><select name="position">
						<option value="travel agent" ${booking.agent.position=='travel agent'?'selected':'' }>Travel Agent</option>
						<option value="agent manager" ${booking.agent.position=='agent manager'?'selected':'' }>Agent Manager</option>
				</select></td>
			</tr>


			<tr style="background-color:#E8F1F7" align="center">
				<td colspan="2">Other &nbsp;</td>
			</tr>

			<tr>
				<td>Room Type: &nbsp;</td>
				<td><select name="room_type">
						<option value="Standard Double" ${ booking.room_type=='Standard Double'? 'selected':'' }>Standard Double</option>
						<option value="Standard Queen" ${ booking.room_type=='Standard Queen'? 'selected':'' }>Standard Queen</option>
						<option value="Standard King" ${ booking.room_type=='Standard King'? 'selected':'' }>Standard King</option>
						<option value="All-Inclusive" ${ booking.room_type=='All-Inclusive'? 'selected':'' }>All-Inclusive</option>
				</select></td>
			</tr>

			<tr>
				<td>Activity: &nbsp;</td>
				<td><select name="activity">
						<option value="Vacation" ${ booking.activity=='Vacation'? 'selected':'' }>Vacation</option>
						<option value="Business" ${ booking.activity=='Business'? 'selected':'' }>Business</option>
				</select></td>
			</tr>





			<tr>
				<td colspan="2" align="center"><br> <input class="sub" type="submit" name="submit" value="update" style="width:80; height:25" align="middle" /></td>
				<td><input type="hidden" name="booking_id" value="${booking.booking_id }" size="10px"></td>
			</tr>

		</table>

	</form>


</body>



</html>
