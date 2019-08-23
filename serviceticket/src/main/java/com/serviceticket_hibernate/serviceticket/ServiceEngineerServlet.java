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
import BeanClasses.ServiceEngineerBean;

public class ServiceEngineerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession(false);
		String uname = (String) session.getAttribute("username");
		System.out.println("at servlet using session" + uname);
		String action = request.getParameter("value");
		System.out.println(action);
		Client client = ClientBuilder.newClient(new ClientConfig());
		if (action.equals("Respond")) {
			Bean b = new Bean();
			b.setUsername(uname);
			ServiceEngineerBean sb = new ServiceEngineerBean();
			sb.setSEusername(b);
			WebTarget webTarget = client.target("http://localhost:8080/serviceticket/webapi/ServiceEngineer")
					.path("viewticket");
			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
			Response res = invocationBuilder.post(Entity.entity(sb, MediaType.APPLICATION_JSON));
			List<EndUserBean> tickets = res.readEntity(new GenericType<List<EndUserBean>>() {
			});
			if(tickets.size()==0)
			{
				RequestDispatcher rd = request.getRequestDispatcher("ServiceEngineerOutput.jsp");
				request.setAttribute("tickets", tickets);
				request.setAttribute("ch", 2);
				rd.forward(request, response);
			}
			else {
			RequestDispatcher rd = request.getRequestDispatcher("ServiceEngineerOutput.jsp");
			request.setAttribute("tickets", tickets);
			request.setAttribute("ch", 1);
			rd.forward(request, response);
			}
		} else if (action.equals("changestat")) {
			EndUserBean e = new EndUserBean();
			e.setTicketId(request.getParameter("tid"));
			e.setTicketStatus(request.getParameter("stat"));
			WebTarget webTarget = client.target("http://localhost:8080/serviceticket/webapi/ServiceEngineer")
					.path("changestat");
			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
			Response res = invocationBuilder.post(Entity.entity(e, MediaType.APPLICATION_JSON));
			int x = Integer.parseInt(res.readEntity(String.class));
			RequestDispatcher rd = request.getRequestDispatcher("ServiceEngineerOutput1.jsp");
			request.setAttribute("ch", x);
			rd.forward(request, response);
		}
		else if (action.equals("changepriority")) {
			System.out.println("in servlet");
			EndUserBean e = new EndUserBean();
			e.setTicketId(request.getParameter("tid"));
			e.setTicketPriority(request.getParameter("Priority"));
			WebTarget webTarget = client.target("http://localhost:8080/serviceticket/webapi/ServiceEngineer")
					.path("changeprio");
			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
			Response res = invocationBuilder.post(Entity.entity(e, MediaType.APPLICATION_JSON));
			int x = Integer.parseInt(res.readEntity(String.class));
			RequestDispatcher rd = request.getRequestDispatcher("ServiceEngineerOutput1.jsp");
			request.setAttribute("ch", x);
			rd.forward(request, response);
		}
		else if (action.equals("ReportperS")) {
			System.out.println("hola");
			WebTarget webTarget = client.target("http://localhost:8080/serviceticket/webapi/ServiceEngineer")
					.path("ReportperS");
			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
			Response res = invocationBuilder.get();
			List<String> sev = res.readEntity(new GenericType<List<String>>() {
			});
			RequestDispatcher rd = request.getRequestDispatcher("ServiceEngineerOutput1.jsp");
			request.setAttribute("ch", 4);
			request.setAttribute("severity", sev);
			rd.forward(request, response);
		}
//		else if (action.equals("ReportperS")) {
//			WebTarget webTarget = client.target("http://localhost:8080/serviceticket/webapi/ServiceEngineer")
//					.path("ReportperS");
//			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
//			Response res = invocationBuilder.get();
//			List<String> sev = res.readEntity(new GenericType<List<String>>() {
//			});
//			RequestDispatcher rd = request.getRequestDispatcher("ServiceEngineerOutput1.jsp");
//			request.setAttribute("ch", 4);
//			request.setAttribute("severity", sev);
//			rd.forward(request, response);
//		}
		else if (action.equals("ReportperSE")) {
			WebTarget webTarget = client.target("http://localhost:8080/serviceticket/webapi/ServiceEngineer")
					.path("ReportperSE");
			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
			Response res = invocationBuilder.get();
			List<String> sev = res.readEntity(new GenericType<List<String>>() {
			});

			RequestDispatcher rd = request.getRequestDispatcher("ServiceEngineerOutput1.jsp");
			request.setAttribute("ch", 5);
			request.setAttribute("service", sev);
			rd.forward(request, response);
		} 
		 else if (action.equals("avgage")) {
				Bean b = new Bean();
				b.setUsername(uname);
				ServiceEngineerBean sb = new ServiceEngineerBean();
				sb.setSEusername(b);
				WebTarget webTarget = client.target("http://localhost:8080/serviceticket/webapi/ServiceEngineer")
						.path("avgage");
				Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
				Response res = invocationBuilder.post(Entity.entity(sb, MediaType.APPLICATION_JSON));
				List<String> se = res.readEntity(new GenericType<List<String>>() {
				});
				System.out.println(se.size());
				if(se.size()==0)
				{
					RequestDispatcher rd = request.getRequestDispatcher("ServiceEngineerOutput1.jsp");
					request.setAttribute("ch", 11);
					rd.forward(request, response);
				}
				else {
				RequestDispatcher rd = request.getRequestDispatcher("ServiceEngineerOutput1.jsp");
				request.setAttribute("ch", 6);
				request.setAttribute("tickets", se);
				rd.forward(request, response);
				}
			}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
