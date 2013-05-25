<%@page import="com.proj425.domain.Amenity"%>
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

<title>AGENT Management</title>

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
	<h1 align="center">AMENITY MANAGEMENT</h1>
	<br>

	<div ${ amenity_search=="true"? 'style="display: none;"':'style="display: run-in;"' }>
		<form action="/425pj/AmenitySearch" target="right">

			<input type="text" name="am_nm"> <input type="submit" value="search">

		</form>
	</div>

	<table align="center" width="100%" border="0" cellpadding="1" cellspacing="0">

		<tr style="background-color:#9EBAEA">
			<th align="left" valign="middle" nowrap="nowrap">&nbsp;NO.</th>
			<th align="left" valign="middle" nowrap="nowrap">&nbsp;Amenity</th>
		</tr>


		<c:forEach items="${requestScope.amenity_list }" var="amenity" varStatus="stat">

			<tr style="background-color: ${stat.count%2==0?'#E8F1F7':'#FFFFFF'}">

				<td align="left" valign="middle" nowrap="nowrap">&nbsp;${stat.count }</td>
				<td align="left" valign="middle" nowrap="nowrap">&nbsp;${amenity.am_nm }</td>

			</tr>

		</c:forEach>
		<tr>
			<td colspan="10" height="10"></td>
		</tr>
		<tr align="right">
			<td colspan="10"><input type="button" name="button" value="Add" onclick="window.open('/425pj/AmenityAddUI','right')"
				${ account.agent.position=="travel agent"||amenity_search=="true" ? 'style="display: none;"':'style="display: table-cell;"' }></td>
		</tr>


	</table>




</body>



</html>