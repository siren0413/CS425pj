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

</head>

<body bgcolor="E8F1F7">

	<jsp:include page="/logo.jsp"></jsp:include>


	<h3>SEARCH:</h3>

	<form method="post" action="/425pj/ResortSearch" target="right">

		<table align="center" width="100%" border="0" cellpadding="1" cellspacing="1"font-size:10px; >
			<tr>
				<td>Resort Name:&nbsp;<br> <input type="text" name="resort_nm" size="10px" />
				</td>
			</tr>

			<tr>
				<td>City:&nbsp;<br> <input type="text" name="city" size="10px" /></td>
			</tr>

			<tr>
				<td>Country:&nbsp;<br> <input type="text" name="country" size="10px" />
				</td>
			</tr>

			<tr>
				<td>Phone number:&nbsp;<br> <input type="text" name="phone_number" size="10px" />
				</td>
			</tr>


			<tr>

				<td>Sun rating: &nbsp;<br> <select name="rating">
						<option value="">-</option>
						<option value="1">one sun</option>
						<option value="2">two sun</option>
						<option value="3">three sun</option>
				</select>
				</td>

			</tr>


			<tr>
				<td>Address:&nbsp;<br> <input type="text" name="address" size="10px" />
				</td>
			</tr>

			

			<tr>
				<td>Amenity:&nbsp;<br>
				<c:forEach items="${ requestScope.amenity_list}" var="amenity">
						<input type="checkbox" name="amenity" value="${ amenity.am_nm}" size="10px" />${ amenity.am_nm} <br>
				</c:forEach>
				</td>
			</tr>


			<tr>
				<td><br> <input class="sub" type="submit" name="submit" value="search" style="width:80; height:25" /></td>
			</tr>


		</table>

	</form>


	<br>
	<br>


	<h3>MANAGE:</h3>

	<table align="center" width="100%" border="0" cellpadding="1" cellspacing="1"font-size:10px; >

		<tr>
			<td><input class="sub" type="button" name="submit" value="Amenity Manage" style="width:150; height:25"
				onclick="window.open('/425pj/AmenityManage','right')" /></td>
		</tr>

	</table>




</body>
</html>
