<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>footer</title>
</head>
<body>
	<jsp:useBean id="now" class="java.util.Date"/>
	<fmt:setLocale value="en-EN"/>
	<hr>
	<table style="width: 100%;">
		<tr>
			<td>Developed by Valiantsin Vialichka</td>
			<td style="text-align: right;">
				The current date:
				<fmt:formatDate value="${now}"/>
			</td>
		</tr>
	</table>
	<hr>
</body>
</html>