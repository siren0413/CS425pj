<%@page import="com.proj425.domain.Agent"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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

<script type="text/javascript">
	function confirmDelete() {
		var x = confirm("Are you sure you want to delete?");
		if (x) {

			form.submit();
		}

	}
</script>

</head>

<jsp:include page="/navigation.jsp"></jsp:include>


<body style="text-align: left" leftmargin="10px" rightmargin="10px" topmargin="20px">
	<h1 align="center">AGENT MANAGEMENT</h1>
	<br>
	<form name="form" action="/425pj/AgentDelete">
		<table align="center" width="100%" border="0" cellpadding="1" cellspacing="0">

			<tr style="background-color:#9EBAEA">
				<th align="left" valign="middle" nowrap="nowrap">&nbsp;NO.</th>
				<th align="left" valign="middle" nowrap="nowrap">&nbsp;First Name</th>
				<th align="left" valign="middle" nowrap="nowrap">&nbsp;Last Name</th>
				<th align="left" valign="middle" nowrap="nowrap">&nbsp;Phone Number</th>
				<th align="left" valign="middle" nowrap="nowrap">&nbsp;Number of Clients</th>
			</tr>


			<c:forEach items="${requestScope.agent_list }" var="agent" varStatus="stat">

				<tr style="background-color: ${stat.count%2==0?'#E8F1F7':'#FFFFFF'}">

					<td align="left" valign="middle" nowrap="nowrap">&nbsp;${stat.count }</td>
					<td align="left" valign="middle" nowrap="nowrap">&nbsp;${agent.first_nm }</td>
					<td align="left" valign="middle" nowrap="nowrap">&nbsp;${agent.last_nm }</td>
					<td align="left" valign="middle" nowrap="nowrap">&nbsp;${agent.phone_number }</td>
					<td align="left" valign="middle" nowrap="nowrap">&nbsp;${agent.nbr_clients }</td>
				</tr>

			</c:forEach>


		</table>
	</form>


</body>



</html>
