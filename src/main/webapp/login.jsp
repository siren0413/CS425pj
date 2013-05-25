<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>Sunsational Login</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body bgcolor="#EFF4FB">


	<br>
	<br>
	<br>
	<br>



	<h1 align="center">Sunsational Vacation Management</h1>
	<br>
	<form action="/425pj/LoginServlet" method="post">


		<table align="center" width="50" border="0" cellpadding="1" cellspacing="0">


			<tr>
				<td>username:</td>
				<td><input type="text" name="username"></td>
			</tr>

			<tr>
				<td>password:</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td colspan="2" align="center" nowrap="nowrap" ${ account_error==null? 'style="display: none;"':'style="display: table-cell; color: red; "' }>invalid username/password</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><br> <input class="sub" type="submit" name="submit" value="Login" style="width:80; height:25" /></td>
			</tr>




		</table>
		
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>

		

		<div align="center">

			Agent username: &nbsp;agent<br> Agent password: &nbsp;123456<br> <br> Manager username: &nbsp;manager<br> Manager password: &nbsp; 123456<br>



		</div>


	</form>



</body>
</html>
