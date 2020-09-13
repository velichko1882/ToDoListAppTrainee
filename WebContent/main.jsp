<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main</title>
</head>
<body>
	<div class="wrapper">
		<c:import url="header.jsp"/>
		<div class="content"><br>
			<h3>Welcome, ${user.name }</h3><br>
			<div style="font-size: large;">
				<a href="getTasks?section=TODAY">Today</a>&nbsp;||&nbsp;
				<a href="getTasks?section=TOMORROW">Tomorrow</a>&nbsp;||&nbsp;
				<a href="getTasks?section=SOMEDAY">Someday</a>&nbsp;||&nbsp;
				<a href="getTasks?section=FIXED">Fixed</a>&nbsp;||&nbsp;
				<a href="getTasks?section=RECYCLE_BIN">Recycle Bin</a>
			</div><br>
			<div>
				<c:choose>
					<c:when test="${not empty errorMessage }">
						<c:out value="${errorMessage }"/>
					</c:when>	
					<c:otherwise>
						<c:import url="tasks.jsp"/>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<div class="footer">
			<c:import url="footer.jsp"/>
		</div>
	</div>
</body>
</html>