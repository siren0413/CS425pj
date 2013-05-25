<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'client_search.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<style type="text/css">
span {
	font-size: 13px;
}

input.sub {
	size: 20px;
}
</style>

</head>

<body  bgcolor=#E8F1F7>

	<jsp:include page="/logo.jsp"></jsp:include>


	<h3>SEARCH:</h3>

	<form method="post" action="/425pj/CitySearch" target="right">

		<table align="center" width="100%" border="0" cellpadding="1" cellspacing="1"font-size:10px; >
			<tr>
				<td>City:&nbsp;<br> <input type="text" name=city size="10px" /></td>
			</tr>


			<tr>
				<td>Country:&nbsp;<br> <input type="text" name="country" size="10px" /></td>
			</tr>

	
			<tr>
				<td><br> <input class="sub" type="submit" name="submit" value="search" style="width:80; height:25" /></td>
			</tr>






		</table>

	</form>







</body>
</html>
