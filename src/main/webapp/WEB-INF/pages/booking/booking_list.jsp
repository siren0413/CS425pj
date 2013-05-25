<%@page import="com.proj425.domain.Agent"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>AGENT Management</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
	function confirmDelete() {
		var x = confirm("Are you sure you want to delete?");
		if (x) {
			document.myform.action = "/425pj/BookingDelete";
			document.myform.submit();
		}

	}

	function showdays() {

		var table = document.getElementById("table");
		var rows = table.rows;
		for ( var i = 0; i < rows.length; i++) {
			rows[i].cells[6].style.display = "block";
		}
	}

	
	function frequentClient() {
		
		var start_date = document.getElementById("start_date").value;
		var end_date = document.getElementById("end_date").value;
		window.open("/425pj/FrequentClientSearch?start_date=" + start_date+"&end_date="+end_date,"right");
	}
	
	function topClient() {
		var start_date = document.getElementById("start_date").value;
		var end_date = document.getElementById("end_date").value;
		window.open("/425pj/TopClientSearch?start_date=" + start_date+"&end_date="+end_date,"right");
	}
</script>
</head>

<jsp:include page="/navigation.jsp"></jsp:include>


<body style="text-align: left" leftmargin="10px" rightmargin="10px" topmargin="20px">
	<h1 align="center">BOOKING MANAGEMENT</h1>
	<br>
	From <input type="text" id="start_date" size="10">To<input type="text" id="end_date" size="10"> <input type="button" name="days" value="frequent client" onclick="frequentClient()"><input type="button" name="days" value="top client" onclick="topClient()">
	<br>
	<form name="myform" action="/425pj/BookingDelete" method="get">
		<table align="center" width="100%" border="0" cellpadding="1" cellspacing="0" id="table">

			<tr style="background-color:#9EBAEA">
				<th align="left" valign="middle" nowrap="nowrap">&nbsp;NO.</th>
				<th align="left" valign="middle" nowrap="nowrap">&nbsp;Client</th>
				<th align="left" valign="middle" nowrap="nowrap">&nbsp;Phone</th>
				<th align="left" valign="middle" nowrap="nowrap">&nbsp;Book</th>
				<th align="left" valign="middle" nowrap="nowrap">&nbsp;Arrive</th>
				<th align="left" valign="middle" nowrap="nowrap">&nbsp;Departure</th>
				<th align="left" valign="middle" nowrap="nowrap" style="display: none;">&nbsp;Days</th>
				<th align="left" valign="middle" nowrap="nowrap">&nbsp;Resort</th>
				<th align="left" valign="middle" nowrap="nowrap">&nbsp;Room Type</th>
				<th align="left" valign="middle" nowrap="nowrap">&nbsp;Activity</th>
				<th align="left" valign="middle" nowrap="nowrap">&nbsp;Agent</th>
				<th align="left" valign="middle" nowrap="nowrap">&nbsp;Phone</th>
				<th align="left" valign="middle" nowrap="nowrap" ${ account.agent.position=="travel agent"? 'style="display: none;"':'style="display: table-cell;"' }>&nbsp;Update</th>
				<th align="left" valign="middle" nowrap="nowrap" ${ account.agent.position=="travel agent"? 'style="display: none;"':'style="display: table-cell;"' }>&nbsp;Delete</th>
			</tr>


			<c:forEach items="${requestScope.booking_list }" var="booking" varStatus="stat">

				<tr style="background-color: ${stat.count%2==0?'#E8F1F7':'#FFFFFF'}">

					<td align="left" valign="middle" nowrap="nowrap">&nbsp;${stat.count }</td>
					<td align="left" valign="middle" nowrap="nowrap">&nbsp;${booking.client.first_nm }&nbsp;${booking.client.last_nm }</td>
					<td align="left" valign="middle" nowrap="nowrap">&nbsp;${booking.client.phone_number }</td>
					<td align="left" valign="middle" nowrap="nowrap">&nbsp;<fmt:formatDate value="${booking.book_date }" pattern="MM/dd/yyyy" />
					</td>
					<td align="left" valign="middle" nowrap="nowrap">&nbsp;<fmt:formatDate value="${booking.arrive_date }" pattern="MM/dd/yyyy" />
					</td>
					<td align="left" valign="middle" nowrap="nowrap">&nbsp;<fmt:formatDate value="${booking.departure_date }" pattern="MM/dd/yyyy" />
					</td>
					<td align="left" valign="middle" nowrap="nowrap" style="display: none; color: #EA1E06">&nbsp; <fmt:formatNumber type="number" maxFractionDigits="0"
							value="${ (booking.departure_date.time - booking.arrive_date.time)/(3600*24*1000) }" />
					</td>
					<td align="left" valign="middle" nowrap="nowrap">&nbsp;${booking.resort.resort_nm }</td>
					<td align="left" valign="middle" nowrap="nowrap">&nbsp;${booking.room_type }</td>
					<td align="left" valign="middle" nowrap="nowrap">&nbsp;${booking.activity }</td>
					<td align="left" valign="middle" nowrap="nowrap">&nbsp;${booking.agent.first_nm }&nbsp;${booking.agent.last_nm }</td>
					<td align="left" valign="middle" nowrap="nowrap">&nbsp;${booking.agent.phone_number }</td>
					<td align="left" valign="middle" nowrap="nowrap" ${ account.agent.position=="travel agent"? 'style="display: none;"':'style="display: table-cell;"' }>&nbsp;<a href="/425pj/BookingUpdateUI?booking_id=${booking.booking_id }">update</a></td>
					<td align="left" valign="middle" nowrap="nowrap" ${ account.agent.position=="travel agent"? 'style="display: none;"':'style="display: table-cell;"' }>&nbsp;<input type="checkbox" name="delete_list" value="${booking.booking_id }"> <input
						type="hidden" name="booking_id" value="${booking.booking_id }"> <input type="hidden" name="client_first_nm" value="${booking.client.first_nm }">
						<input type="hidden" name="client_last_nm" value="${booking.client.last_nm }">

					</td>


				</tr>

			</c:forEach>


			<tr>
				<td colspan="5" height="10"></td>
			</tr>


			<tr align="right">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td> <input type="button" name="days" value="best agent" onclick="window.open('/425pj/BestAgentSearch','right')"></td>
				<td><input type="button" name="days" value="show days" onclick="showdays()"></td>
				<td><input type="button" name="button" value="Revenue" onclick="window.open('/425pj/revenue.html','_parent')"></td>
				<td><input type="button" name="button" value="Add" onclick="window.open('/425pj/BookingAddUI','right')" ${ account.agent.position=="travel agent"? 'style="display: none;"':'style="display: table-cell;"' }></td>
				<td><input type="button" name="button" value="Delete" onclick="confirmDelete()" ${ account.agent.position=="travel agent"? 'style="display: none;"':'style="display: table-cell;"' }></td>
			</tr>


		</table>


	</form>

	<span>Records:${page.totalRows }</span>&nbsp;
	<span>Pages:${page.totalPage }</span>
	<div align="center">

		<c:forEach begin="1" end="${page.totalPage }" var="pagenum">

			[<a href="/425pj/BookingManage?pageIndex=${pagenum }"  style="text-decoration: none"> ${pagenum } </a>]


		</c:forEach>
	</div>
	
	
	<br>
	<br>
	<br>
	<br>
	<span><a style="font: bold;">*Note:</a></span><br>
	<span> <a style="text-decoration: underline;">Best Agent</a>: the agent who booked the largest number of bookings.</span><br>
	<span> <a style="text-decoration: underline;">Show Days</a>: the number of days of a trip.</span><br>
	<span> <a style="text-decoration: underline;">Revenue</a>: Find the total revenue from hotel room bookings(sum of all bookings' nightly rates for all nights plus all inclusive fees).</span><br>
	<span><a style="text-decoration: underline;">Frequent Client</a>: the client who have booked the largest numbers of trips in a period.</span><br>
	<span><a style="text-decoration: underline;">Top Client</a>: the client who have spent the most money in a period.</span><br>
</body>



</html>
