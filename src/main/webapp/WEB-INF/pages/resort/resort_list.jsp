<%@page import="com.proj425.domain.Resort"%>
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

<title>Resort Management</title>

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

			form.submit();
		}

	}
</script>

</head>

<jsp:include page="/navigation.jsp"></jsp:include>


<body style="text-align: left" leftmargin="10px" rightmargin="10px" topmargin="20px">
	<h1 align="center">RESORT MANAGEMENT</h1>
	<br>

	<form name="form" action="/425pj/ResortDelete">
		<table align="center" width="100%" border="0" cellpadding="1" cellspacing="0">

			<tr style="background-color:#9EBAEA">
				<th align="left" valign="middle" nowrap="nowrap">&nbsp;NO.</th>
				<th align="left" valign="middle" nowrap="nowrap">&nbsp;Resort Name</th>
				<th align="left" valign="middle" nowrap="nowrap">&nbsp;City</th>
				<th align="left" valign="middle" nowrap="nowrap">&nbsp;Country</th>
				<th align="left" valign="middle" nowrap="nowrap">&nbsp;Phone Number</th>
				<th align="left" valign="middle" nowrap="nowrap">&nbsp;Sun Rating</th>
				<th align="left" valign="middle" nowrap="nowrap">&nbsp;Luxury Level</th>
				<th align="left" valign="middle" nowrap="nowrap">&nbsp;Address</th>
				<th align="left" valign="middle" nowrap="nowrap">&nbsp;Amenity</th>
				<th align="left" valign="middle" nowrap="nowrap" ${ account.agent.position=="travel agent"? 'style="display: none;"':'style="display: table-cell;"' }>&nbsp;Update</th>
				<th align="left" valign="middle" nowrap="nowrap" ${ account.agent.position=="travel agent"? 'style="display: none;"':'style="display: table-cell;"' }>&nbsp;Delete</th>
			</tr>


			<c:forEach items="${requestScope.resort_list }" var="resort" varStatus="stat">

				<tr style="background-color: ${stat.count%2==0?'#E8F1F7':'#FFFFFF'}">

					<td align="left" valign="middle" nowrap="nowrap">&nbsp;${stat.count }</td>
					<td align="left" valign="middle" nowrap="nowrap">&nbsp;${resort.resort_nm }</td>
					<td align="left" valign="middle" nowrap="nowrap">&nbsp;${resort.city.city }</td>
					<td align="left" valign="middle" nowrap="nowrap">&nbsp;${resort.city.country }</td>
					<td align="left" valign="middle" nowrap="nowrap">&nbsp;${resort.phone_number }</td>
					<td align="left" valign="middle" nowrap="nowrap">&nbsp;${resort.rating.rating }</td>
					<td align="left" valign="middle" nowrap="nowrap">&nbsp;${resort.rating.luxury_level }</td>
					<td align="left" valign="middle" nowrap="nowrap">&nbsp;${resort.address }</td>
					<td align="left" valign="middle" nowrap="nowrap">&nbsp;<a href="/425pj/AmenityManage?resort_id=${resort.resort_id }">amenity</a></td>
					<td align="left" valign="middle" nowrap="nowrap" ${ account.agent.position=="travel agent"? 'style="display: none;"':'style="display: table-cell;"' }>&nbsp;<a href="/425pj/ResortUpdateUI?resort_id=${resort.resort_id }">update</a></td>
					<td align="left" valign="middle" nowrap="nowrap" ${ account.agent.position=="travel agent"? 'style="display: none;"':'style="display: table-cell;"' }>&nbsp;<input type="checkbox" name="delete_list" value="${resort.resort_id }"></td>

				</tr>

			</c:forEach>


			<tr>
				<td colspan="9" height="10"></td>
			</tr>
			<tr align="right">
				<td colspan="9"><input type="button" name="button" value="Add" onclick="window.open('/425pj/ResortAddUI','right')" ${ account.agent.position=="travel agent"? 'style="display: none;"':'style="display: table-cell;"' }></td>
				<td colspan="9"><input type="button" name="button" value="Delete" onclick="confirmDelete()" ${ account.agent.position=="travel agent"? 'style="display: none;"':'style="display: table-cell;"' }></td>
			</tr>


		</table>

	</form>

</body>



</html>
