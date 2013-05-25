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
	<h1 align="center">REVENUE LOOKUP</h1>
	<br>

	<table align="center" width="100%" border="0" cellpadding="1" cellspacing="0">

		<tr style="background-color:#9EBAEA">
			<th align="left" valign="middle" nowrap="nowrap">&nbsp;NO.</th>
			<th align="left" valign="middle" nowrap="nowrap">&nbsp;
			<c:choose>
					<c:when test="${revenue_obj=='agent' }">Agent</c:when>
					<c:when test="${revenue_obj=='city' }">City</c:when>
					<c:when test="${revenue_obj=='country' }">Country</c:when>
				</c:choose>
			</th>
			<th align="left" valign="middle" nowrap="nowrap">&nbsp;Revenue</th>
		</tr>


		<c:forEach items="${requestScope.revenue_list }" var="revenue" varStatus="stat">

			<tr style="background-color: ${stat.count%2==0?'#E8F1F7':'#FFFFFF'}">

				<td align="left" valign="middle" nowrap="nowrap">&nbsp;${stat.count }</td>
				
				<td align="left" valign="middle" nowrap="nowrap">
				<c:choose>
					<c:when test="${revenue_obj=='agent' }"> ${revenue.obj.first_nm }&nbsp;${revenue.obj.last_nm } </c:when>
					<c:when test="${revenue_obj=='city' }">${revenue.obj.city }</c:when>
					<c:when test="${revenue_obj=='country' }">${revenue.obj.country }</c:when>
				</c:choose>
				</td>
					
				<td align="left" valign="middle" nowrap="nowrap">&nbsp;${revenue.revenue }</td>


			</tr>

		</c:forEach>



	</table>




</body>



</html>
