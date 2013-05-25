<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>Update Fail</title>

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

<br>
<br>
<br>
<br>


<body title="Update Success" style="text-align: center">
	<h1 style="color: red;" align="center">${page_status.status }</h1>


	<img alt="" src="/425pj/image/success1.gif" align="middle">

	<br>
	<br>
	<br>
	<br>
	<p align="center">
		<a href="${page_status.forwardURL }"> Click here return to ${page_status.object } ---> </a>
	</p>
	
	
	<p align="center" ${  booking_add==null ? 'style="display: none;"':'style="display: run-in;"' }>
		<a href="/425pj/BookingAddUI"> Click here return to CONTINUE BOOKING ADD ---> </a>
	</p>
	
	

</body>
</html>
