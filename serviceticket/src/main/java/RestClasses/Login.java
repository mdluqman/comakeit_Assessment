package RestClasses;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.google.gson.Gson;
import com.serviceticket_hibernate.serviceticket.DAOclass;

import BeanClasses.Bean;
//import com.google.gson.Gson;
@Path("Login")
public class Login {
	DAOclass daoobject=new DAOclass();
	
	@POST
	@Path("Login1")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String getUserType(String user)
	{
		Bean accountholder = new Gson().fromJson(user, Bean.class);
		System.out.println("hi1");
		String usertype= daoobject.clientlogin(accountholder);
		System.out.println(usertype);
		return usertype;
	}
}