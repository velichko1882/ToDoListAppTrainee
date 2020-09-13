<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>tasks</title>
<script type="text/javascript">
	function check(){
 		var check=document.getElementsByTagName('input');
 		for(var i=0;i<check.length;i++){
  			if(check[i].type=='checkbox'){
   				check[i].checked=true;
  			}
 		}
	}
	function uncheck(){
 		var uncheck=document.getElementsByTagName('input');
 		for(var i=0;i<uncheck.length;i++){
  			if(uncheck[i].type=='checkbox'){
   				uncheck[i].checked=false;
  			}
 		}
	}
</script>
</head>
<body>
	<h4>${section}</h4><br>
	<c:choose>
		<c:when test="${not empty tasks}">
				<table border="1" style="width: 80%; margin: auto; background-color: PaleGreen;">
					<tr>
						<th style="width: 120px;">CheckBox</th>
						<th>Tasks</th>
						<c:if test="${section eq 'SOMEDAY' or section eq 'FIXED' or section eq 'RECYCLE_BIN'}">
							<th style="width: 150px;">Date</th>
						</c:if>
						<th style="width: 150px;">File operations</th>
					</tr>
					<c:forEach items="${tasks}" var="task">
						<tr>
							<td>
								<input type="checkbox" form="editTaskForm" name="idTask" value="${task.idTask}">
							</td>
							<td style="text-align: left;">
								<c:out value="${task.description}"></c:out>
							</td>
							<c:if test="${section eq 'SOMEDAY' or section eq 'FIXED' or section eq 'RECYCLE_BIN'}">
								<td>
									<c:out value="${task.date}"></c:out>
								</td>
							</c:if>
							<td>
								<%@include file="fileOperations.jsp" %>
							</td>
						</tr>
					</c:forEach>
				</table><br>
		<form id="editTaskForm" action="editTask" method="post">
			<input type="hidden" name="section" value="${section}"/>		
				<table style="margin-left: 200px;">
					<c:choose>
						<c:when test="${section eq 'TODAY' or section eq 'TOMORROW' or section eq 'SOMEDAY'}">
							<tr>
								<td>
									<input type="button" value="Add Task" onclick="location.href ='addTask.jsp?section=${section}'"/>
								</td>
								<td>
									<input type="submit" name="operation" value="Fixed"/>
								</td>
								<td>
									<input type="submit" name="operation" value="Recycle Bin"/>
								</td>
							</tr>
						</c:when>
						<c:when test="${section eq 'FIXED'}">
							<tr>
								<td>
									<input type="submit" name="operation" value="Not Fixed"/>
								</td>
								<td>
									<input type="submit" name="operation" value="Recycle Bin"/>
								</td>
							</tr>
						</c:when>
						<c:when test="${section eq 'RECYCLE_BIN'}">
							<tr>
								<td>
									<input type="button" value="Check All" onclick="check();"/>
								</td>
								<td>
									<input type="button" value="Uncheck All" onclick="uncheck();"/>
								</td>
							</tr>
							<tr>
								<td>
									<input type="submit" name="operation" value="Restore"/>
								</td>
								<td>
									<input type="submit" name="operation" value="Remove"/>
								</td>
							</tr>
						</c:when>
				
					</c:choose>
				</table>
		</form> 	
		</c:when>
		
		<c:otherwise><br>
			<h3>There are no tasks in this section.</h3><br>
			<c:if test="${section eq 'TODAY' or section eq 'TOMORROW' or section eq 'SOMEDAY'}">
				<input type="button" value="Add Task" onclick="location.href ='addTask.jsp?section=${section}'"/>
			</c:if>
		</c:otherwise>
		
	</c:choose>
</body>
</html>