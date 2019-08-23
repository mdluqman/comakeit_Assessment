package RestClasses;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.serviceticket_hibernate.serviceticket.ServiceEngineerDao;

import BeanClasses.EndUserBean;
import BeanClasses.ServiceEngineerBean;

@Path("ServiceEngineer")
public class ServiceEngineerRest {
	
	
	@POST
	@Path("viewticket")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<EndUserBean> viewticket(String se) {
		ServiceEngineerBean seb = new Gson().fromJson(se, ServiceEngineerBean.class);
		ServiceEngineerDao sed = new ServiceEngineerDao();
		List<EndUserBean> tickets = sed.viewticket(seb);
		return tickets;
		}
	
	@GET
	@Path("ReportperS")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<String> ReportperS() {
//		ServiceEngineerBean seb = new Gson().fromJson(se, ServiceEngineerBean.class);
		ServiceEngineerDao sed = new ServiceEngineerDao();
		List<String> severity = sed.ReportperS();
			System.out.println("bye rest");

		return severity;
		}
	
	@GET
	@Path("ReportperSE")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<String> ReportperSE() {
		ServiceEngineerDao sed = new ServiceEngineerDao();
		System.out.println("hey rest");
		List<String> serviceeng = sed.ReportperSE();
		System.out.println("bye rest");
		return serviceeng;
		}
	
	@POST
	@Path("avgage")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<String> avgage(String se) {
		ServiceEngineerBean seb = new Gson().fromJson(se, ServiceEngineerBean.class);
		ServiceEngineerDao sed = new ServiceEngineerDao();
		List<String> tickets = sed.avgage(seb);
		return tickets;
		}
	
	@POST
	@Path("changestat")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public int changestat(String se) {
		System.out.println("JSON: " + se);
		EndUserBean seb = new Gson().fromJson(se, EndUserBean.class);
		ServiceEngineerDao sed = new ServiceEngineerDao();
		int x=sed.changestat(seb);
		System.out.println("in rest :"+x);
		return x;
		}
	
	@POST
	@Path("changeprio")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public int changeprio(String se) {
		System.out.println("JSON: " + se);
		EndUserBean seb = new Gson().fromJson(se, EndUserBean.class);
		ServiceEngineerDao sed = new ServiceEngineerDao();
		System.out.println("changeprio in rest entered");
		int x=sed.changeprio(seb);
		System.out.println("in rest :"+x);
		return x+1;
		}
}
