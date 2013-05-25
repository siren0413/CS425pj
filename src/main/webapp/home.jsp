<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'home.jsp' starting page</title>

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


<%
	if (request.getSession().getAttribute("account") == null) {
		request.getRequestDispatcher("/login.jsp").forward(request, response);
		return;
	}
%>

<body bgcolor="#EFF4FB">
	<h2 align="center">CS425 Database Final Project</h2>


	<div align="center">
		<span style="font: bold;">team member:</span><br><br>
		Yijun Mao <a href="mailto:ymao3@iit.edu">ymao3@iit.edu</a> <br>
		Han Wang <a href="mailto:hwang78@iit.edu">hwang78@iit.edu</a><br>
		Ruizhe Li<br>
		Yubo Diao<br>


	</div>

	<br>
	<br>


	<div style="margin-left: 20px">

		<span>Project Requirement Document:&nbsp;<a href="/425pj/files/CS425DatabaseProjectRequirement.pdf">DatabaseProject-Spring2013.pdf </a> </span><br>
		<span>Source Code:&nbsp;<a href="/425pj/files/425Project.zip">425Project.zip </a> </span><br>
		<span>ER Design Diagram:&nbsp;<a href="/425pj/files/425Proj_ER_Diagram.pdf">ER_Diagram.pdf </a> </span><br>
		<span>Design Document:&nbsp;<a href="/425pj/files/425Proj_ER_Diagram.pdf"></a> </span><br>
		<span>Test Plan Document:&nbsp;<a href="/425pj/files/425Proj_ER_Diagram.pdf"></a> </span><br>
		<span>Manual:&nbsp;<a href="/425pj/files/425Proj_ER_Diagram.pdf"></a> </span><br>
		
		
	</div>



</body>
</html>
