<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<%
String username=(String)session.getAttribute("username");
%>
<body bgcolor="cyan">
<CENTER>
<h1>WELCOME <%=username %> SERVICE_ENGINEER</h1>
you can perform the following<br/><br/><br/>
<a href="ServiceEngineerServlet?value=Respond">View & Respond to a ticket</a><br><br><br>
<a href="ServiceEngineerServlet?value=ReportperS">PerformanceReportPerSeverity</a><br><br><br>
<a href="ServiceEngineerServlet?value=ReportperSE">PerformanceReportPerServiceEngineer</a><br><br><br>
<a href="ServiceEngineerServlet?value=avgage">AgeOfOpenTicket</a><br><br><br><br/><br/><br/>
<form action="LoginServlet">
<input type="submit" name="action" value="LOGOUT">
</form>
</CENTER>
</body>
</html>