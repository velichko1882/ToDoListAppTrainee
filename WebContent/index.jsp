<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ToDoList</title>
<link rel="stylesheet" href="css/style.css"/>
</head>
<body>
	<div class="wrapper">
		<c:import url="header.jsp" />
		<div class="content">
			
			<h1 style="margin-top: 10%;">Welcome To-Do List</h1>
		 	<p style="margin-top: 30px;">Todolist is a simple to-do list or task list. 
		 		Write in it all your important affairs, so as not to forget.
		 	</p>
		</div>
		<div class="footer">
			<c:import url="footer.jsp"/>
		</div>
	</div>
</body>
</html>