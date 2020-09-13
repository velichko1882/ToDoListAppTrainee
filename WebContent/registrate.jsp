<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registrate</title>
<link rel="stylesheet" href="css/style.css"/>
</head>
<body>
<div class="center">
	<br>
	<h3>Registration</h3>
	<c:if test="${not empty errorMessage }">
	<c:out value="${errorMessage }"></c:out><br>
	<hr>
	</c:if><br>
	<form name="registrateForm" method="post" action="registrate">
		Login:<br>
		<input type="text" name="login"><br>
		Password:<br>
		<input type="password" name="password"><br>
		Name:<br>	
		<input type="text" name="name"><br>
		Email:<br>
		<input type="text" name="email"><br>
		<input type="submit" value="Registrate">
	</form>
	<br>
	<a href="index.jsp">ToDoList</a>
</div>
</body>
</html>