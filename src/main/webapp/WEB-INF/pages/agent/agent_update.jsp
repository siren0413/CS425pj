<%@page import="com.proj425.domain.Agent"%>
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

<title>Agent Management</title>

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
	<h1 align="center">AGENT UPDATE</h1>
	<br>



	<form method="post" action="/425pj/AgentUpdate" target="right">

		<table align="center" width="50%" border="3" cellpadding="0" cellspacing="0">
			

			<tr>
				<td>First Name: &nbsp;</td>
				<td><input type="text" name="first_nm" value="${agent.first_nm }" size="10px"></td>
			</tr>

			<tr>
				<td>Last Name: &nbsp;</td>
				<td><input type="text" name="last_nm" value="${agent.last_nm }" size="10px"></td>
			</tr>

			<tr>
				<td>Gender: &nbsp;</td>
				<td><select name="gender">
						<option value="M" ${ agent.gender=='M'? 'selected':'' }>Male</option>
						<option value="F" ${ agent.gender=='F'? 'selected':'' }>Female</option>
				</select></td>
			</tr>

			<tr>
				<td>Birth Date: &nbsp;</td>
				<td><input type="text" name="dob" value='<fmt:formatDate value="${agent.dob }" pattern="MM/dd/yyyy"/>' size="10px"></td>
			</tr>

			<tr>
				<td>Phone Number: &nbsp;</td>
				<td><input type="text" name="phone_number" value="${agent.phone_number }" size="10px"></td>
			</tr>

			<tr>
				<td>Zip Code: &nbsp;</td>
				<td><input type="text" name="zip" value="${agent.zip }" size="10px"></td>
			</tr>

			<tr>
				<td>Email: &nbsp;</td>
				<td><input type="text" name="email" value="${agent.email }" size="15px"></td>
			</tr>
			
			<tr>
				<td>Gender: &nbsp;</td>
				<td><select name="position">
						<option value="travel agent" ${ agent.position=='travel agent'? 'selected':'' }>Travel Agent</option>
						<option value="agent manager" ${ agent.position=='agent manager'? 'selected':'' }>Agent Manager</option>
				</select></td>
			</tr>



			<tr>
				<td colspan="2" align="center" ><br> <input class="sub" type="submit" name="submit" value="update" style="width:80; height:25" align="middle"/></td>
				<td><input type="hidden" name="agent_id" value="${agent.agent_id }" size="10px"></td>
			</tr>

		</table>

	</form>


</body>



</html>
