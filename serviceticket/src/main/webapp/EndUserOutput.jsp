<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.List" %>
	<%@page import="BeanClasses.EndUserBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EndUser Output</title>
</head>
<%
Integer ch=(Integer)request.getAttribute("ch");
List<EndUserBean> tickets=(List<EndUserBean>) request.getAttribute("tickets");
switch(ch)
{
	case 0 : out.println("<center><br/><br/><br/><h3>Sorry No ServiceEngineer Available to handle your Ticket<br/>Plz Try Again Later</h3><br/><br/><br/></center>");
				break;
	case 1 : out.println("<center><br/><br/><br/><h3>YOUR TICKET HAS BEEN RECORDED!!! <br/>KEEP CALM AND WAIT FOR THE SERVICE ENGINEER TO DO THE MIRACLE</h3></center>");
				break;
	case 2 : out.println("<table border = '2' style='text-align:center' ; >");
				out.println("<tr><th>CustomerUsername</th><th>TicketId</th><th>ServiceEngineerId</th><th>ConcernedDeptId</th><th>Subject</th><th>TicketStatus</th><th>TicketPriority</th><th>WorkStation</th><th>TicketIssueDate</th><th>RequestEndDate</th><th>BeginDate</th><th>CompletionDate</th></tr>");
				for(int i=0;i<tickets.size();i++)
				{
					out.println("<tr><td>"+tickets.get(i).getusername().getUsername()+"</td><td> "+tickets.get(i).getTicketId()+"</td><td> "+tickets.get(i).getServiceengineer().getServiceEngineerId()+"</td><td> "+tickets.get(i).getDept().getDeptNo()+"</td><td> "+tickets.get(i).getSubject()+"</td><td>"+tickets.get(i).getTicketStatus()+"</td><td> "+tickets.get(i).getTicketPriority()+"</td><td> "+tickets.get(i).getWorkStation()+"</td><td> "+tickets.get(i).getDateOfIssue()+"</td><td>"+tickets.get(i).getRequestedEndDAte()+"</td><td> "+tickets.get(i).getDateOfAction()+"</td><td> "+tickets.get(i).getDateOfCompletion()+"</td></tr>");
				}
				out.println("</table>");
				break;
}

%>
<body bgcolor="lightgreen">
<center>
<form action="EndUser.jsp">
<input type="submit" name="action" value="HomePage">
</form>
</center>
</body>
</html>