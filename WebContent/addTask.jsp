<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addTask</title>
<link rel="stylesheet" href="css/style.css"/>
</head>
<body>
<div class="center">
	<br>
	<h3>Add Task</h3><br>
	<c:if test="${not empty errorMessage }">
		<c:out value="${errorMessage }"></c:out><br>
	</c:if>
	<form name="addTaskForm" action="addTask" method="post" >
		<input type="hidden" name="section" value="${param.section}"/>
		<h4>Task description:</h4>
		<textarea style="font-size: xx-large; background-color: PaleGreen;" 
					rows="7" cols="50" name="description" maxlength="250"></textarea><br>
		<c:if test="${param.section eq 'SOMEDAY'}">
			Date:
			<input type="date" name="date" lang=""/><br>
		</c:if>
		<input type="submit" value="Add Task"/>
	</form><br>
	<a href="getTasks">Main</a>
</div>	
</body>
</html>