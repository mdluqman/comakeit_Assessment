package com.serviceticket_hibernate.serviceticket;

import java.io.IOException;

import java.io.PrintWriter;
import BeanClasses.Bean;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

import com.mysql.cj.Session;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		
		String clientrequest = request.getParameter("action");
		
		System.out.println(clientrequest);
		
		Client client = ClientBuilder.newClient(new ClientConfig());
		HttpSession session=request.getSession();
		session.setAttribute("username", request.getParameter("username"));
		
		if (clientrequest.equals("Login")) {

			WebTarget webTarget = client.target("http://localhost:8080/serviceticket/webapi/Login").path("Login1");
			
			Bean AccountHolder = new Bean();
			AccountHolder.setUsername(request.getParameter("username"));
			AccountHolder.setPassword(request.getParameter("password"));
			
			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
			Response res = invocationBuilder.post(Entity.entity(AccountHolder, MediaType.APPLICATION_JSON));
			String message = res.readEntity(String.class);
			System.out.println("CLIENT IS A :" + message);
			if (message.equals("ServiceEngineer")) {
				RequestDispatcher rd = request.getRequestDispatcher("ServiceEngineer.jsp");
				rd.forward(request, response);
			} else if (message.equals("EndUser")) {
				RequestDispatcher rd = request.getRequestDispatcher("EndUser.jsp");
				rd.forward(request, response);
			}else if (message.equals("Admin")) {
				RequestDispatcher rd = request.getRequestDispatcher("Admin.jsp");
				rd.forward(request, response);
			}else 
			{
				RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
				request.setAttribute("message", message);
				rd.forward(request, response);
			}
		} 
	
		else if (clientrequest.equals("LOGOUT")) {
			session.removeAttribute("username");
			RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
