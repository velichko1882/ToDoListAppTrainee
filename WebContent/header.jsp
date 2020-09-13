<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>header</title>
<link rel="stylesheet" href="css/style.css"/>
</head>
<body>
	<div class="header">
		<hr>
		<c:choose>
			<c:when test="${not empty user }">
				<c:out value="User: ${user.login }"/>
				<form name="logout" action="logout" method="post" style="float: right;">
					<input type="submit" value="Logout">
				</form>
			</c:when>	
			<c:otherwise>
				User: guest
				<div style="float: right;">
					<a href="login.jsp">Login</a> &nbsp;&nbsp;&nbsp;
					<a href="registrate.jsp">Registrate</a>
				</div>
			</c:otherwise>
		</c:choose>
		<hr>
	</div>
</body>
</html>