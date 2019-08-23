<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LOGIN PAGE</title>

<style type="text/css">

input {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-top: 6px;
  margin-bottom: 16px;
}

input[type=submit] {
  background-color: #4CAF50;
  color: orange;
}
</style>
</head>
<%
String m=(String)request.getAttribute("message");
if(m!=null)
{
	%>
	<h3><%=m %></h3>
	<%
}
%>
<body>
<center>
<form action="LoginServlet" method="POST" id="demo"><br/><br/>
<h1>LOGIN</h1>
USERNAME: <input type="text" name="username"><br/><br/>
PASSWORD: <input type="password" name="password"><br/><br/>
<input type="submit" name="action" value="Login">
<br/><br/><br/>

</center>
</form>

</body>

</html>