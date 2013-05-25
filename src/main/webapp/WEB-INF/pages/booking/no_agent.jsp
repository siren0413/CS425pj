<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'update_success.jsp' starting page</title>

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


<body title="Update Fail" style="text-align: center">
	<h1 style="color: red;" align="center">Error:</h1>
	<h1 style="color: red;" align="center">No Such Agent Found In System!</h1>


	<img alt="" src="/425pj/image/fail.jpeg" align="middle">

	<br>
	<br>
	<br>
	<br>
	<p align="center">
		<a href="/425pj/AgentAddUI"> Click here return to ADD A AGENT ---> </a>
	</p>

</body>
</html>
