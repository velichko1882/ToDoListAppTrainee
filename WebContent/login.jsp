<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" href="css/style.css"/>
</head>
<body>
<div class="center">
	<br>
	<h3>Login</h3>
	<c:if test="${not empty errorMessage }">
	<c:out value="${errorMessage }"></c:out><br>
	<a href="registrate.jsp">Registrate</a>
	<hr>
	</c:if><br>
	<form name="loginForm" method="post" action="login">
		Login:<br>
		<input type="text" name="login"><br>
		Password:<br>
		<input type="password" name="password"><br>
		<input type="submit" value="Enter">
	</form>
	<br>
	<a href="index.jsp">ToDoList</a>
</div>
</body>
</html>