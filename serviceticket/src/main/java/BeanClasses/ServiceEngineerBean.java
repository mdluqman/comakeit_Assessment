package BeanClasses;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ServiceEngineerBean")
@NamedNativeQueries({@NamedNativeQuery(name="selectengineers", query="SELECT * from ServiceEngineerBean serviceengineer", resultClass = ServiceEngineerBean.class)})
public class ServiceEngineerBean {
	@Id
	private String ServiceEngineerId;
	private int totalTickets;
	private String currentHighPrioityTicketId;
	private int pending;

	@OneToOne
	@JoinColumn(name = "SEusername")
	Bean SEusername=new Bean();
	
	public Bean getSEusername() {
		return SEusername;
	}
	public void setSEusername(Bean sEusername) {
		SEusername = sEusername;
	}

	@ManyToOne
	@JoinColumn(name = "deptNo")
	private deptInfo dept;

	public String getServiceEngineerId() {
		return ServiceEngineerId;
	}
	public void setServiceEngineerId(String serviceEngineerId) {
		ServiceEngineerId = serviceEngineerId;
	}
	public int getTotalTickets() {
		return totalTickets;
	}
	public void setTotalTickets(int totalTickets) {
		this.totalTickets = totalTickets;
	}
	public String getCurrentHighPrioityTicketId() {
		return currentHighPrioityTicketId;
	}
	public void setCurrentHighPrioityTicketId(String currentHighPrioityTicketId) {
		this.currentHighPrioityTicketId = currentHighPrioityTicketId;
	}
	public int getPending() {
		return pending;
	}
	public void setPending(int pending) {
		this.pending = pending;
	}
	public deptInfo getDept() {
		return dept;
	}
	public void setDept(deptInfo dept) {
		this.dept = dept;
	}
	

}
