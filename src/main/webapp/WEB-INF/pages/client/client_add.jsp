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


<body style="text-align: center" leftmargin="10px" rightmargin="10px" topmargin="20px">
	<h1 align="center">CLIENT ADD</h1>
	<br>

	<div>
		<span ${empty_value=="true"? 'style="display: run-in; color: red;"':'style="display: none; color: red;"' }> Please fill all boxes </span>
	</div>

	<form method="post" action="/425pj/ClientAdd" target="right">

		<table align="center" width="50%" border="3" cellpadding="0" cellspacing="0">


			<tr>
				<td>First Name: &nbsp;</td>
				<td><input type="text" name="first_nm" size="10px" value="${client_add.first_nm }"></td>
			</tr>

			<tr>
				<td>Last Name: &nbsp;</td>
				<td><input type="text" name="last_nm" size="10px" value="${client_add.last_nm }"></td>
			</tr>

			<tr>
				<td>Gender: &nbsp;</td>
				<td><select name="gender">
						<option value="M"  ${client_add.gender=='M'?'selected':'' }  >Male</option>
						<option value="F" ${client_add.gender=='F'?'selected':'' }>Female</option>
				</select></td>
			</tr>

			<tr>
				<td>Birth Date: &nbsp;</td>
				<td><input type="text" name="dob" size="10px" value="<fmt:formatDate value="${client_add.dob }" pattern="MM/dd/yyyy" />"> <span>(mm/dd/yyyy)</span></td>

			</tr>

			<tr>
				<td>Phone Number: &nbsp;</td>
				<td><input type="text" name="phone_number" size="10px" value="${client_add.phone_number }"></td>
			</tr>

			<tr>
				<td>Zip Code: &nbsp;</td>
				<td><input type="text" name="zip" size="10px" value="${client_add.zip }"></td>
			</tr>

			<tr>
				<td>Email: &nbsp;</td>
				<td><input type="text" name="email" size="15px" value="${client_add.email }"></td>
			</tr>



			<tr>
				<td colspan="2" align="center"><br> <input class="sub" type="submit" name="submit" value="Add" style="width:80; height:25" align="middle" /></td>

			</tr>

		</table>

	</form>


</body>



</html>
