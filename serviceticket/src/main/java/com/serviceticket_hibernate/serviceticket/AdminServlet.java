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
import BeanClasses.ServiceEngineerBean;
import BeanClasses.deptInfo;
import BeanClasses.usertypeinfo;

public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AdminBusinessclass abc=new AdminBusinessclass();
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String clientrequest = request.getParameter("value");
		System.out.println(clientrequest +"is op");
		Client client = ClientBuilder.newClient(new ClientConfig());
		if (clientrequest.equals("Register!")) {

			WebTarget webTarget = client.target("http://localhost:8080/serviceticket/webapi/AdminRest")
					.path("Register");
			Bean AccountHolder = new Bean();
			AccountHolder.setUsername(request.getParameter("username"));
			AccountHolder.setPassword(request.getParameter("password"));
			usertypeinfo user = new usertypeinfo();
			user.setUserTypeId(Integer.parseInt(request.getParameter("roletype")));
			AccountHolder.setUsertype(user);
			System.out.println(AccountHolder.getUsername() + " having " + AccountHolder.getPassword() + " "
					+ AccountHolder.getUsertype().getUserTypeId());
			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
			Response res1 = invocationBuilder.post(Entity.entity(AccountHolder, MediaType.APPLICATION_JSON));
			String message = res1.readEntity(String.class);
			System.out.println(message);
			if (message.equals("user_registered")) {
				if (AccountHolder.getUsertype().getUserTypeId() == 2) {
					WebTarget webTarget1 = client.target("http://localhost:8080/serviceticket/webapi/AdminRest")
							.path("RegisterSE");
					ServiceEngineerBean seb = new ServiceEngineerBean();
					seb.setCurrentHighPrioityTicketId("0");
					deptInfo dept = new deptInfo();
					dept.setDeptNo(Integer.parseInt(request.getParameter("dept")));
					seb.setDept(dept);
					seb.setPending(0);
					seb.setServiceEngineerId(request.getParameter("seid"));
					seb.setSEusername(AccountHolder);
					seb.setTotalTickets(0);
					System.out.println(seb.getSEusername().getUsername() + " having " + seb.getDept().getDeptNo() + " "
							+ seb.getServiceEngineerId());
					Invocation.Builder invocationBuilder1 = webTarget1.request(MediaType.APPLICATION_JSON);
					Response res2 = invocationBuilder1.post(Entity.entity(seb, MediaType.APPLICATION_JSON));
				}
					RequestDispatcher rd = request.getRequestDispatcher("Admin.jsp");
					request.setAttribute("message", message);
					rd.forward(request, response);
				}
			
		 else if (message.equals("username_exists")) {
				RequestDispatcher rd = request.getRequestDispatcher("Registration.jsp");
				request.setAttribute("value", message);
				rd.forward(request, response);
			}
		}else if (clientrequest.equals("Register")) {
			List<deptInfo> departments = abc.getdept();
			RequestDispatcher rd = request.getRequestDispatcher("Registration.jsp");
			request.setAttribute("dept", departments);
			rd.forward(request, response);
		} else if (clientrequest.equals("View")) {
			List<usertypeinfo> user = abc.getusertype();
			RequestDispatcher rd = request.getRequestDispatcher("AdminOutput.jsp");
			request.setAttribute("usertype", user);
			request.setAttribute("usertypeid", "5");
			rd.forward(request, response);
		} else if (clientrequest.equals("GET")) {
			int usertypeid = Integer.parseInt(request.getParameter("usertype"));
			System.out.println("usertypeid is:" + usertypeid);
			if (usertypeid == 1) {
				List<Bean> user = abc.getuser();
				List<usertypeinfo> usertype = abc.getusertype();
				RequestDispatcher rd = request.getRequestDispatcher("AdminOutput.jsp");
				request.setAttribute("users", user);
				request.setAttribute("usertype", usertype);
				request.setAttribute("usertypeid", "1");
				rd.forward(request, response);
			} else if (usertypeid == 2) {
				List<ServiceEngineerBean> serviceengineer = abc.getserviceengineer();
				for(int i=0;i<serviceengineer.size();i++)
				{
					System.out.println(serviceengineer.get(i).getSEusername().getUsername());
				}
				List<usertypeinfo> usertype = abc.getusertype();
				RequestDispatcher rd = request.getRequestDispatcher("AdminOutput.jsp");
				request.setAttribute("serviceengineer", serviceengineer);
				request.setAttribute("usertype", usertype);
				request.setAttribute("usertypeid", "2");
				rd.forward(request, response);
			} else if (usertypeid == 3) {
				List<Bean> user = abc.getuser();
				for(int i=0;i<user.size();i++)
				{
					System.out.println(user.get(i).getUsername());
				}
				List<usertypeinfo> usertype = abc.getusertype();
				RequestDispatcher rd = request.getRequestDispatcher("AdminOutput.jsp");
				request.setAttribute("users", user);
				request.setAttribute("usertype", usertype);
				request.setAttribute("usertypeid", "3");
				rd.forward(request, response);
		}
		}
			else if(clientrequest.equals("delete"))
			{
				System.out.println("hi");
				String un=request.getParameter("name");
				int id=Integer.parseInt(request.getParameter("id2"));
				String seid = request.getParameter("id1");
				System.out.println(id+" "+ un + " " + seid);
				Bean b=new Bean();
				b.setUsername(un);
				ServiceEngineerBean s=new ServiceEngineerBean();
				s.setSEusername(b);
				s.setServiceEngineerId(seid);
				usertypeinfo type=new usertypeinfo();
				type.setUserTypeId(id);
				b.setUsertype(type);
				if(id==2) {
					WebTarget webTarget1 = client.target("http://localhost:8080/serviceticket/webapi/AdminRest")
							.path("delete2");
					Invocation.Builder invocationBuilder1 = webTarget1.request(MediaType.APPLICATION_JSON);
				Response res2 = invocationBuilder1.post(Entity.entity(s, MediaType.APPLICATION_JSON));
				}
				else if(id==1 || id ==3)
				{
					WebTarget webTarget1 = client.target("http://localhost:8080/serviceticket/webapi/AdminRest")
							.path("delete13");
					Invocation.Builder invocationBuilder1 = webTarget1.request(MediaType.APPLICATION_JSON);
					Response res2 = invocationBuilder1.post(Entity.entity(b, MediaType.APPLICATION_JSON));
				}
				RequestDispatcher rd = request.getRequestDispatcher("Admin.jsp");
				request.setAttribute("message", "the selected client has been deleted!");
				rd.forward(request, response);
				
				
			} 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	

}
