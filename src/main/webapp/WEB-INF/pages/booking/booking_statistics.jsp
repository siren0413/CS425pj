<%@page import="com.proj425.domain.Client"%>
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

<title>Client Management</title>

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


<body style="text-align: left" leftmargin="10px" rightmargin="10px" topmargin="20px">
	<h1 align="center">BOOKING STATISTICS</h1>
	<br>

	<table align="center" width="100%" border="0" cellpadding="1" cellspacing="0">

		<tr style="background-color:#9EBAEA">
			<th align="left" valign="middle" nowrap="nowrap">&nbsp;Client</th>
			<th align="left" valign="middle" nowrap="nowrap">&nbsp;AverageDay</th>
		</tr>


			<tr style="background-color: ${stat.count%2==0?'#E8F1F7':'#FFFFFF'}">


				<td align="left" valign="middle" nowrap="nowrap">${clientStatistics.client.first_nm }&nbsp;${clientStatistics.client.last_nm }</td>

				<td align="left" valign="middle" nowrap="nowrap">&nbsp; <fmt:formatNumber type="number" maxFractionDigits="0" value="${clientStatistics.avgDaysPerTrip }"></fmt:formatNumber></td>


			</tr>

	



	</table>




</body>



</html>
