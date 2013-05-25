<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">



<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	
	<div align="right">
	Name:&nbsp; <span style="color: blue;">${sessionScope.account.agent.first_nm }&nbsp;${sessionScope.account.agent.last_nm }</span><br>
	Position: <span style="color: blue;">&nbsp;${sessionScope.account.agent.position } </span> <br>
	<a href="/425pj/LogoutServlet" target="_parent">Logout</a>
	
	</div>

	<table align="center" width="10%" border="0" cellpadding="20" cellspacing="0">

		<tr>
			<td><a href="/425pj/home.jsp" target="_parent" style="text-decoration: none;">HOME </a></td>
			<td><a href="/425pj/client.html" target="_parent" style="text-decoration: none;">CLIENT </a></td>
			<td><a href="/425pj/agent.html" target="_parent" style="text-decoration: none;">AGENT </a></td>
			<td><a href="/425pj/city.html" target="_parent" style="text-decoration: none;">CITY </a></td>
			<td><a href="/425pj/resort.html" target="_parent" style="text-decoration: none;">RESORT </a></td>
			<td><a href="/425pj/booking.html" target="_parent" style="text-decoration: none;">BOOKING </a></td>
		</tr>

	</table>

	<hr>
	<br>




</body>
</html>
