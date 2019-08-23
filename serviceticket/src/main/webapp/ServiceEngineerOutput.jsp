<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="BeanClasses.EndUserBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>VIEW TICKETS</title>
</head>
<%
	Integer ch = (Integer) request.getAttribute("ch");
	List<EndUserBean> tickets = (List<EndUserBean>) request.getAttribute("tickets");
	switch (ch) {
	case 1:
		out.println("<table border = 3 style='text-align:center'>");
		out.println(
				"<tr><th>CustomerUsername</th><th>TicketId</th><th>ServiceEngineerId</th><th>ConcernedDeptId</th><th>Subject</th><th>TicketStatus</th><th>TicketPriority</th><th>WorkStation</th><th>TicketIssueDate</th><th>RequestEndDate</th><th>BeginDate</th><th>CompletionDate</th></tr>");
		for (int i = 0; i < tickets.size(); i++) {
			out.println("<tr><td>" + tickets.get(i).getusername().getUsername() + "</td><td> "
					+ tickets.get(i).getTicketId() + "</td><td> "
					+ tickets.get(i).getServiceengineer().getServiceEngineerId() + "</td><td> "
					+ tickets.get(i).getDept().getDeptNo() + "</td><td> " + tickets.get(i).getSubject()
					+ "</td><td>" + tickets.get(i).getTicketStatus() + "</td><td> "
					+ tickets.get(i).getTicketPriority() + "</td><td> " + tickets.get(i).getWorkStation()
					+ "</td><td> " + tickets.get(i).getDateOfIssue() + "</td><td>"
					+ tickets.get(i).getRequestedEndDAte() + "</td><td>" + tickets.get(i).getDateOfAction()
					+ "</td><td>" + tickets.get(i).getDateOfCompletion() + "</td></tr>");
		}
		out.println("</table>");
		%>
		<form action='ServiceEngineerServlet' method='post'>
		<br /> Enter the Ticket ID on which you would like to perform a
		change:<input type="text" name="tid"><br />
		<br /> Now select from respective drop_down_lists and Click on the
		respective BUTTON to perform the required change!!<br />
		<br />
		<br />
		<br /> Update the Status of a desired ticket from options given below:
		<select name="stat"><option value="WorkInProgress">act
				for the ticket</option>
			<option value="Waiting">this ticket can wait</option>
			<option value="Completed">Work Done</option></select> <input type="submit"
			name="value" value="changestat"><br />
		<br />OR<br />
		<br /> If would you like to change the Priority of a desired ticket :
		<select name="Priority">
			<option value="3">High</option>
			<option value="2">Medium</option>
			<option value="1">Low</option>
		</select> <input type="submit" name="value" value="changepriority">
	</form>
	<%
		break;
		
	case 2 :%>
	<h3><center>no tickets assigned to you yet, you are free!!</h3></center>
	<%	
	}
%>
<body>
	<br />
	
	<center>
		<br />
		<br />
		<br />
		<br />
		<form action="ServiceEngineer.jsp">
			<input type="submit" name="action" value="HomePage">
		</form>
	</center>
</body>
</html>