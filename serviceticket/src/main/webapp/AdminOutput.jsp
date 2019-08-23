<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="javax.ws.rs.client.*"%>
<%@page import="javax.ws.rs.core.*"%>
<%@page import="BeanClasses.*"%>
<%@page import="java.util.*"%>
<%@page import="org.glassfish.jersey.client.ClientConfig"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ADMIN OUTPUT</title>
</head>

<body>
<center>
	<form action="AdminServlet" method="post">
<%
	 List<usertypeinfo> usertype=(List<usertypeinfo>)request.getAttribute("usertype");
	String  x=(String)request.getAttribute("usertypeid");
	
	if(x.equals(null))
	{
		out.println("fish");
	}
	
	else if(x.equals("2"))
				  {		
					  List<ServiceEngineerBean> serviceengineer=(List<ServiceEngineerBean>)request.getAttribute("serviceengineer");
					  int y= Integer.parseInt(x);
				%>
<table border=1>
	<tr>
		<th>USERNAME</th>
		<th>Id</th>
		<th>TotalTickets</th>
		<th>AreaOfExpertise</th>
	</tr>
	<%
		for (int i = 0; i < serviceengineer.size(); i++){
	%>
	<tr>
		<td><%=serviceengineer.get(i).getSEusername().getUsername() %></td>
		<td><%=serviceengineer.get(i).getServiceEngineerId() %></td>
		<td><%=serviceengineer.get(i).getTotalTickets() %></td>
		<td><%=serviceengineer.get(i).getDept().getDeptName() %></td>
		<td><a href="AdminServlet?value=delete&name=<%=serviceengineer.get(i).getSEusername().getUsername()%>&id2=<%=2%>&id1=<%=serviceengineer.get(i).getServiceEngineerId()%>">Delete</a></td>
	</tr>
	<%
		}%>
		</table>
	<%
		}
		else if(x.equals("3") || x.equals("1"))
				  {		
					  List<Bean> users=(List<Bean>)request.getAttribute("users");
					  int y= Integer.parseInt(x);
				%>
<table border=1>
	<tr>
		<th>USERNAME</th>
		<th>PASSWORD</th>
	</tr>
	<%
		for (int i = 0; i < users.size(); i++){
	%>
	<tr>
		<%
	if(users.get(i).getUsertype().getUserTypeId()==y)
	{
	%>
		<td><%=users.get(i).getUsername() %></td>
		<td><%=users.get(i).getPassword() %></td>
		<td><a href="AdminServlet?value=delete&name=<%=users.get(i).getUsername()%>&id2=<%=y%>&id1=seid786786">Delete</a></td>
	</tr>
	<%
		}%>
	<%} %>
	<%
		}
		%>
</table>
		select Type of user you want to view: <select name="usertype">
			<%
  for(usertypeinfo user: usertype){
	  out.println("<option value="+user.getUserTypeId()+">"+user.getTypeOfUser()+"</option>");
  }
				%>



			<br />
			<br />
			<br />
			<br />
			<input type="submit" name="value" value="GET">
	</form>
	<form action="Admin.jsp" method="POST">
	<input type="submit" value="HomePage">
	</form>
</center>
</body>
</html>