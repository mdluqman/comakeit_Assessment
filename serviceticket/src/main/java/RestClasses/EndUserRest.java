package RestClasses;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.google.gson.Gson;
import com.serviceticket_hibernate.serviceticket.EndUserDao;
import BeanClasses.EndUserBean;
import BeanClasses.deptInfo;

@Path("EndUser")

public class EndUserRest {

	@GET
	@Path("raise")
	@Produces(MediaType.APPLICATION_JSON)
	public List<deptInfo> getUserType() {
		EndUserDao eud = new EndUserDao();
		List<deptInfo> depts = eud.getdept();
		// System.out.println(usertype);
		return depts;
	}

	@POST
	@Path("raiseticket")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public int raise(String tickets) {
		EndUserBean ticket = new Gson().fromJson(tickets, EndUserBean.class);
		EndUserDao eud = new EndUserDao();
		if (redv(ticket)) {
			int x = eud.raise(ticket);
			return x;
		} 
		else
			return 8;
	}
	@POST
	@Path("viewticket")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<EndUserBean> viewticket(String eb) {
		EndUserBean eub = new Gson().fromJson(eb, EndUserBean.class);
		EndUserDao eud = new EndUserDao();
		List<EndUserBean> tickets = eud.viewticket(eub);
		return tickets;
		}

	@SuppressWarnings("deprecation")
	public boolean redv(EndUserBean enduser) {
		// TODO Auto-generated method stub
		java.util.Date date = new java.util.Date();
		java.sql.Date now = new java.sql.Date(date.getTime());
		java.util.Date cd = null;
		try {
			cd = (java.util.Date) new SimpleDateFormat("yyyy-MM-dd").parse(enduser.getRequestedEndDAte());
			if (cd.getYear() > now.getYear()) {
				return true;
			} else if (cd.getYear() == now.getYear()) {
				if (cd.getMonth() > now.getMonth()) {
					return true;
				} else if (cd.getMonth() == now.getMonth()) {
					if (cd.getDate() >= now.getDate()) {
						return true;
					} else {
						return false;
					}
				}

			}

			else {
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}
}
