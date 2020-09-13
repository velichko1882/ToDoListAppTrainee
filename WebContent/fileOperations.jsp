<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>file</title>
</head>
<body>

	<c:choose>
		<c:when test="${task.fileName ne 'no file' }">
			<c:out value="${task.fileName }"/><br>
			<form action="downloadFile">
				<input type="hidden" name="idTask" value="${task.idTask}">
				<input type="hidden" name="fileName" value="${task.fileName}"/>
				<input type="submit" name="action" value="Download">
			</form>
			<form action="deleteFile" method="post">
				<input type="hidden" name="idTask" value="${task.idTask}">
				<input type="hidden" name="section" value="${section}"/>
				<input type="hidden" name="fileName" value="${task.fileName}"/>
				<input type="submit" name="action" value="Delete">
			</form>
		</c:when>
		<c:when test="${section eq 'TODAY' or section eq 'TOMORROW' or section eq 'SOMEDAY' }">
			<form action="uploadFile" method="post" enctype="multipart/form-data">
				<input type="hidden" name="idTask" value="${task.idTask}">
				<input type="hidden" name="section" value="${section}"/>
				<input type="file" name="file">
				<input type="submit" name="action" value="Upload">
			</form>
		</c:when>
		<c:otherwise>
			<c:out value="${task.fileName }"/>
		</c:otherwise>
	</c:choose>

</body>
</html>