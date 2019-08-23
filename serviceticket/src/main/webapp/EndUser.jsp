<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%
String username=(String)session.getAttribute("username");%>
</head>
<body bgcolor="#45B39D"><center>
<h1>WELCOME <%=username %></h1>
you can perform the following
<!-- <input type="hidden" name="username" value=<%=username %>> -->
<a href="EndUserServlet?name=viewticket" ><h3>View Tickets</h3></a>
<a href="EndUserServlet?name=raise" ><h3>raise Tickets</h3></a>
<br/><br/><br/>
<form action="LoginServlet">
<input type="submit" name="action" value="LOGOUT">
</center></body>
</html>