<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<title>My JSP 'client_search.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<style type="text/css">
span {
	font-size: 13px;
}

input.sub {
	size: 20px;
}
</style>

<script type="text/javascript">
	function checkDate() {

		var book = document.getElementById("book").value;
		var arrive = document.getElementById("arrive").value;
		var departure = document.getElementById("departure").value;
		var datePattern = /^(\d{2})\/(\d{2})\/(\d{4})$/;
		if (!datePattern.test(book) && book != "") {
			window.alert("book date format error");
			return;
		}
		if (!datePattern.test(arrive) && arrive != "") {
			window.alert("arrive date format error");
			return;
		}
		if (!datePattern.test(departure) && departure != "") {
			window.alert("departure date format error");
			return;
		}
		var d1 = Date.parse(book);
		var d2 = Date.parse(arrive);
		var d3 = Date.parse(departure);

		if (!isNaN(d1) && !isNaN(d2) && !isNaN(d3)) {
			if (d1 > d2 || d1 > d3 || d2 > d3) {
				window.alert("date error");
				return;
			}
		}

		if (!isNaN(d1) && !isNaN(d2)) {
			if (d1 > d2) {
				window.alert("book date should < arrive date");
				return;
			}
		}

		if (!isNaN(d1) && !isNaN(d3)) {
			if (d1 > d3) {
				window.alert("book date should < departure date");
				return;
			}
		}

		if (!isNaN(d2) && !isNaN(d3)) {
			if (d2 > d2) {
				window.alert("arrive date should < departure date");
				return;
			}
		}

		document.form1.submit();

	}
</script>

</head>

<body bgcolor="#E8F1F7">

	<jsp:include page="/logo.jsp"></jsp:include>


	<h3>SEARCH:</h3>

	<form method="post" action="/425pj/BookingSearch" target="right" name="form1">

		<table align="center" width="100%" border="0" cellpadding="1" cellspacing="1"font-size:10px; >
			<tr>
				<td>Client First Name:&nbsp;<br> <input type="text" name="c_first_nm" size="10px" /></td>
			</tr>


			<tr>
				<td>Client Last Name:&nbsp;<br> <input type="text" name="c_last_nm" size="10px" /></td>
			</tr>

			<tr>
				<td>Agent First Name:&nbsp;<br> <input type="text" name="a_first_nm" size="10px" /></td>
			</tr>


			<tr>
				<td>Agent Last Name:&nbsp;<br> <input type="text" name="a_last_nm" size="10px" /></td>
			</tr>


			<tr>
				<td>Book Date:&nbsp;<br> <input type="text" name="book_date" size="10px" id="book" /><br> <span>(mm/dd/yyyy)</span>
				</td>
			</tr>


			<tr>
				<td>Arrive Date:&nbsp;<br> <input type="text" name="arrive_date" size="10px" id="arrive" /><br> <span>(mm/dd/yyyy)</span>
				</td>
			</tr>

			<tr>
				<td>Departure Date:&nbsp;<br> <input type="text" name="departure_date" size="10px" id="departure" /><br> <span>(mm/dd/yyyy)</span>
				</td>
			</tr>

			<tr>
				<td>By Range:&nbsp;<input type="checkbox" name="range" size="10px" />
				</td>
			</tr>

			<tr>

				<td>Room Type: &nbsp;<br> <select name="room_type">
						<option value="">-</option>
						<option value="Standard Double">Standard Double</option>
						<option value="Standard Queen">Standard Queen</option>
						<option value="Standard King">Standard King</option>
						<option value="All-Inclusive">All-Inclusive</option>
				</select>
				</td>

			</tr>

			<tr>
				<td>Resort Name:&nbsp;<br> <input type="text" name="resort_nm" size="10px" /></td>
			</tr>


			<tr>

				<td>Activity: &nbsp;<br> <select name="activity">
						<option value="">-</option>
						<option value="Business">Business</option>
						<option value="Vacation">Vacation</option>
				</select>
				</td>

			</tr>



			<tr>
				<td><br> <input class="sub" type="button" name="button" value="search" style="width:80; height:25" onclick="checkDate()" /></td>
			</tr>






		</table>

	</form>

	<br>
	<br>
	<br>
	<br>
	<span><a style="font: bold;">*Note:</a></span>
	<br>
	<span> <a style="text-decoration: underline;">By Range</a>: if selected, then it picks the date which >= arrive date and <= departure date
	</span>
	<br>






</body>
</html>
