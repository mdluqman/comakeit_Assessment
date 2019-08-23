<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ADMIN HOMEPAGE</title>
</head>
<%
String message = (String)request.getParameter("message");
if(message!=null) 
{
	%>
	<%=message %>
	<%
}
%>
<body><center>
<div style="background-color:	#8FBC8F ;margin: 200px 50px 75px	 50px">
  <a href="AdminServlet?value=Register" >To Register a Admin / EndUser / ServiceEngineer </a><br/><br/><br/>
<a href="AdminServlet?value=View" >View Users/Clients</a><br/><br/><br/>

</div>
<form action="Login.jsp" method="POST">
	<input type="submit" value="Logout">
	</form>
</body>
</html>