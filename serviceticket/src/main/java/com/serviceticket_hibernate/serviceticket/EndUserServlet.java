package com.serviceticket_hibernate.serviceticket;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

import BeanClasses.Bean;
import BeanClasses.EndUserBean;
import BeanClasses.deptInfo;

public class EndUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EndUserDao eud = new EndUserDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		HttpSession session = request.getSession(false);
		String uname = (String) session.getAttribute("username");
		System.out.println(uname);
		String action = request.getParameter("name");
		java.util.Date date = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		System.out.println(action);
		
		if (action.equals("raise")) {
			List<deptInfo> departments=getdept();
			RequestDispatcher rd = request.getRequestDispatcher("RaiseTicket.jsp");
			request.setAttribute("dept", departments);
			rd.forward(request, response);
		} 
		
		
		else if (action.equals("raiseticket")) {
			List<deptInfo> departments=getdept();
			EndUserBean eub = new EndUserBean();
			deptInfo d = new deptInfo();
			Bean b=new Bean();
			b.setUsername(uname);
			eub.setusername(b);
			eub.setWorkStation(request.getParameter("ws"));
			eub.setDateOfIssue(sqlDate.toString());
			eub.setRequestedEndDAte(request.getParameter("red"));
			eub.setTicketPriority(request.getParameter("priority"));
			eub.setDateOfAction(null);
			eub.setDateOfCompletion(null);
			eub.setSubject(request.getParameter("subject"));
			d.setDeptNo(Integer.parseInt(request.getParameter("dept")));
			eub.setDept(d);
			Client client = ClientBuilder.newClient(new ClientConfig());
			WebTarget webTarget = client.target("http://localhost:8080/serviceticket/webapi/EndUser").path("raiseticket");
			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
			Response res = invocationBuilder.post(Entity.entity(eub, MediaType.APPLICATION_JSON));
			int message = res.readEntity(Integer.class);
			System.out.println("CLIENT IS A :" + message);
			if(message==8)
			{
				RequestDispatcher rd=request.getRequestDispatcher("RaiseTicket.jsp");
				request.setAttribute("ch",message);
				request.setAttribute("dept", departments);
				rd.forward(request, response);
			}
			else
				{
				RequestDispatcher rd=request.getRequestDispatcher("EndUserOutput.jsp");
				request.setAttribute("ch",message);
				rd.forward(request, response);
				}
		}
		
		else if (action.equals("viewticket")) {
			Client client = ClientBuilder.newClient(new ClientConfig());
			WebTarget webTarget = client.target("http://localhost:8080/serviceticket/webapi/EndUser").path("viewticket");
			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
			EndUserBean eub=new EndUserBean();
			Bean b=new Bean();
			b.setUsername(uname);
			eub.setusername(b);
			Response res = invocationBuilder.post(Entity.entity(eub, MediaType.APPLICATION_JSON));
			List<EndUserBean> tickets = res.readEntity(new GenericType<List<EndUserBean>>() {});
			RequestDispatcher rd = request.getRequestDispatcher("EndUserOutput.jsp");
			request.setAttribute("tickets",tickets);
			request.setAttribute("ch", 2);
			rd.forward(request, response);

		}

	}
	public List<deptInfo> getdept()
	{
		Client client = ClientBuilder.newClient(new ClientConfig());
		WebTarget webTarget = client.target("http://localhost:8080/serviceticket/webapi/EndUser").path("raise");
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response res = invocationBuilder.get();
		List<deptInfo> departments = res.readEntity(new GenericType<List<deptInfo>>() {});
		return departments;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
