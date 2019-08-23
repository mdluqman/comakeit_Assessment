package RestClasses;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.serviceticket_hibernate.serviceticket.AdminDao;
import com.serviceticket_hibernate.serviceticket.EndUserDao;

import BeanClasses.Bean;
import BeanClasses.ServiceEngineerBean;
import BeanClasses.deptInfo;
import BeanClasses.usertypeinfo;

@Path("AdminRest")
public class AdminRest {
	AdminDao daoobject = new AdminDao();
	@POST
	@Path("Register")
	@Consumes(MediaType.APPLICATION_JSON)
	public String register(String user)
	{
		BeanClasses.Bean accountholder = new Gson().fromJson(user, BeanClasses.Bean.class);
		//System.out.println(accountholder.getUsername()+" messeup "+accountholder.getPassword());
		String x= daoobject.clientregister(accountholder);
		return x;
	}
	
	@POST
	@Path("RegisterSE")
	@Consumes(MediaType.APPLICATION_JSON)
	public void registerSE(ServiceEngineerBean s)
	{
//		ServiceEngineerBean  s= new Gson().fromJson(user, ServiceEngineerBean.class);
		System.out.println(s.getSEusername().getUsername()+ " in rest " + s.getDept().getDeptNo()+" "+s.getServiceEngineerId());
		//System.out.println(accountholder.getUsername()+" messeup "+accountholder.getPassword());
		daoobject.seregister(s);
	}
	
	@GET
	@Path("getuser")
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Bean> getuser()
	{
		AdminDao eud = new AdminDao();
		List<Bean> user = eud.getbean();
		// System.out.println(usertype);
		return user;
	}
	
	@GET
	@Path("getusertype")
	@Consumes(MediaType.APPLICATION_JSON)
	public List<usertypeinfo> getusertype()
	{
		AdminDao eud = new AdminDao();
		List<usertypeinfo> user = eud.getusertype();
		// System.out.println(usertype);
		return user;
	}
	
	@GET
	@Path("getserviceengineer")
	@Consumes(MediaType.APPLICATION_JSON)
	public List<ServiceEngineerBean> getserviceengineer()
	{
		AdminDao eud = new AdminDao();
		List<ServiceEngineerBean> getserviceengineer = eud.getserviceengineer();
		// System.out.println(usertype);
		return getserviceengineer;
	}
	
	@POST
	@Path("delete2")
	@Consumes(MediaType.APPLICATION_JSON)
	public String delete(ServiceEngineerBean b)
	{
		AdminDao eud = new AdminDao();
		eud.delete2(b);
		// System.out.println(usertype);
		return null;
	}
	
	@POST
	@Path("delete13")
	@Consumes(MediaType.APPLICATION_JSON)
	public String delete(Bean b)
	{
		AdminDao eud = new AdminDao();
		eud.delete13(b);
		// System.out.println(usertype);
		return null;
	}
}
