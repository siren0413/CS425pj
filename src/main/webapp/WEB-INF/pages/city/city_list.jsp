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

			form.submit();
		}

	}
</script>


</head>

<jsp:include page="/navigation.jsp"></jsp:include>


<body style="text-align: left" leftmargin="10px" rightmargin="10px" topmargin="20px">
	<h1 align="center">CITY LOOKUP</h1>
	<br>
	<form name="form" action="/425pj/CityDelete">
		<table align="center" width="100%" border="0" cellpadding="1" cellspacing="0">

			<tr style="background-color:#9EBAEA">
				<th align="left" valign="middle" nowrap="nowrap">&nbsp;NO.</th>
				<th align="left" valign="middle" nowrap="nowrap">&nbsp;City</th>
				<th align="left" valign="middle" nowrap="nowrap">&nbsp;Country</th>
				<th align="left" valign="middle" nowrap="nowrap" ${ account.agent.position=="travel agent"? 'style="display: none;"':'style="display: table-cell;"' }>&nbsp;Update</th>
				<th align="left" valign="middle" nowrap="nowrap" ${ account.agent.position=="travel agent"? 'style="display: none;"':'style="display: table-cell;"' }>&nbsp;Delete</th>

			</tr>


			<c:forEach items="${requestScope.city_list }" var="city" varStatus="stat">

				<tr style="background-color: ${stat.count%2==0?'#E8F1F7':'#FFFFFF'}">

					<td align="left" valign="middle" nowrap="nowrap">&nbsp;${stat.count }</td>
					<td align="left" valign="middle" nowrap="nowrap">&nbsp;${city.city }</td>
					<td align="left" valign="middle" nowrap="nowrap">&nbsp;${city.country }</td>
					<td align="left" valign="middle" nowrap="nowrap" ${ account.agent.position=="travel agent"? 'style="display: none;"':'style="display: table-cell;"' }>&nbsp;<a href="/425pj/CityUpdateUI?city_id=${city.city_id }">update</a></td>
					<td align="left" valign="middle" nowrap="nowrap" ${ account.agent.position=="travel agent"? 'style="display: none;"':'style="display: table-cell;"' }>&nbsp;<input type="checkbox" name="delete_list" value="${city.city_id }"></td>

				</tr>

			</c:forEach>
			<tr>
				<td colspan="6" height="10"></td>
			</tr>
			<tr align="right">
				<td colspan="6" ${ account.agent.position=="travel agent"? 'style="display: none;"':'style="display: table-cell;"' }><input type="button" name="button" value="Add" onclick="window.open('/425pj/CityAddUI','right')">
				<input type="button" name="button" value="Delete" onclick="confirmDelete()"></td>
			</tr>


		</table>


	</form>

</body>



</html>
