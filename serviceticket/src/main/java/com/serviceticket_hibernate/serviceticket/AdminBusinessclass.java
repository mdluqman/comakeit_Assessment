package com.serviceticket_hibernate.serviceticket;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
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

public class AdminBusinessclass {

	public List<deptInfo> getdept() {
		Client client = ClientBuilder.newClient(new ClientConfig());
		WebTarget webTarget = client.target("http://localhost:8080/serviceticket/webapi/EndUser").path("raise");
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response res = invocationBuilder.get();
		List<deptInfo> departments = res.readEntity(new GenericType<List<deptInfo>>() {
		});
		return departments;
	}

	public List<Bean> getuser() {
		Client client = ClientBuilder.newClient(new ClientConfig());
		WebTarget webTarget = client.target("http://localhost:8080/serviceticket/webapi/AdminRest").path("getuser");
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response res = invocationBuilder.get();
		List<Bean> user = res.readEntity(new GenericType<List<Bean>>() {
		});
		return user;
	}

	public List<usertypeinfo> getusertype() {
		Client client = ClientBuilder.newClient(new ClientConfig());
		WebTarget webTarget = client.target("http://localhost:8080/serviceticket/webapi/AdminRest").path("getusertype");
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response res = invocationBuilder.get();
		List<usertypeinfo> user = res.readEntity(new GenericType<List<usertypeinfo>>() {
		});
		return user;
	}

	public List<ServiceEngineerBean> getserviceengineer() {
		Client client = ClientBuilder.newClient(new ClientConfig());
		WebTarget webTarget = client.target("http://localhost:8080/serviceticket/webapi/AdminRest")
				.path("getserviceengineer");
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response res = invocationBuilder.get();
		List<ServiceEngineerBean> user = res.readEntity(new GenericType<List<ServiceEngineerBean>>() {
		});
		return user;
	}
}
