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
				<th align="left" valign="middle" nowrap="nowrap">&nbsp;Gender</th>
				<th align="left" valign="middle" nowrap="nowrap">&nbsp;Date of Birth</th>
				<th align="left" valign="middle" nowrap="nowrap">&nbsp;Phone Number</th>
				<th align="left" valign="middle" nowrap="nowrap">&nbsp;Zip-Code</th>
				<th align="left" valign="middle" nowrap="nowrap">&nbsp;Email</th>
				<th align="left" valign="middle" nowrap="nowrap">&nbsp;Position</th>
				<th align="left" valign="middle" nowrap="nowrap" ${ account.agent.position=="travel agent"? 'style="display: none;"':'style="display: table-cell;"' }>&nbsp;Update</th>
				<th align="left" valign="middle" nowrap="nowrap" ${ account.agent.position=="travel agent"? 'style="display: none;"':'style="display: table-cell;"' }>&nbsp;Delete</th>
			</tr>


			<c:forEach items="${requestScope.agent_list }" var="agent" varStatus="stat">

				<tr style="background-color: ${stat.count%2==0?'#E8F1F7':'#FFFFFF'}">

					<td align="left" valign="middle" nowrap="nowrap">&nbsp;${stat.count }</td>
					<td align="left" valign="middle" nowrap="nowrap">&nbsp;${agent.first_nm }</td>
					<td align="left" valign="middle" nowrap="nowrap">&nbsp;${agent.last_nm }</td>
					<td align="left" valign="middle" nowrap="nowrap">&nbsp;${agent.gender }</td>
					<td align="left" valign="middle" nowrap="nowrap">&nbsp;<fmt:formatDate value="${agent.dob }" pattern="MM/dd/yyyy" />
					</td>
					<td align="left" valign="middle" nowrap="nowrap">&nbsp;${agent.phone_number }</td>
					<td align="left" valign="middle" nowrap="nowrap">&nbsp;${agent.zip }</td>
					<td align="left" valign="middle" nowrap="nowrap">&nbsp;${agent.email }</td>
					<td align="left" valign="middle" nowrap="nowrap">&nbsp;${agent.position }</td>
					<td align="left" valign="middle" nowrap="nowrap" ${ account.agent.position=="travel agent"? 'style="display: none;"':'style="display: table-cell;"' }>&nbsp;<a
						href="/425pj/AgentUpdateUI?agent_id=${agent.agent_id }">update</a></td>
					<td align="left" valign="middle" nowrap="nowrap" ${ account.agent.position=="travel agent"? 'style="display: none;"':'style="display: table-cell;"' }>&nbsp;<input
						type="checkbox" name="delete_list" value="${agent.agent_id }"></td>


				</tr>

			</c:forEach>
			<tr>
				<td colspan="5" height="10"></td>
			</tr>
			<tr align="right">
				<td colspan=""><input type="button" name="button" value="Clients" onclick="window.open('/425pj/ClientPerAgent','right')"></td>
				<td colspan="9"><input type="button" name="button" value="Add" onclick="window.open('/425pj/AgentAddUI','right')"
					${ account.agent.position=="travel agent"? 'style="display: none;"':'style="display: table-cell;"' }></td>
				<td colspan="5"><input type="button" name="button" value="Delete" onclick="confirmDelete()"
					${ account.agent.position=="travel agent"? 'style="display: none;"':'style="display: table-cell;"' }></td>
			</tr>


		</table>
	</form>

	<span>Records:${page.totalRows }</span>&nbsp;
	<span>Pages:${page.totalPage }</span>
	<div align="center">

		<c:forEach begin="1" end="${page.totalPage }" var="pagenum">

			[<a href="/425pj/AgentManage?pageIndex=${pagenum }" style="text-decoration: none"> ${pagenum } </a>]


		</c:forEach>
	</div>

</body>



</html>
