package com.serviceticket_hibernate.serviceticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import BeanClasses.Bean;
import BeanClasses.EndUserBean;
import BeanClasses.ServiceEngineerBean;
import BeanClasses.deptInfo;

public class EndUserDao {
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ServiceTicket");
	EntityManager em = entityManagerFactory.createEntityManager();

	public List<deptInfo> getdept() {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		Query q = em.createNamedQuery("selectDepartments");
		@SuppressWarnings("unchecked")
		List<deptInfo> departments = (List<deptInfo>) q.getResultList();
		em.getTransaction().commit();
		return departments;
	}

	public int raise(EndUserBean ticket) {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		ticket.setTicketId(gen());
		Query q = em.createQuery("select s from ServiceEngineerBean s where s.dept=" + ticket.getDept().getDeptNo());
		List<ServiceEngineerBean> result = q.getResultList();
		ServiceEngineerBean sb = new ServiceEngineerBean();
		ServiceEngineerBean sb1 = new ServiceEngineerBean();
		//System.out.println(ticket.getTicketId());
		for (int i = 0; i < result.size(); i++) {
			if (result.get(i).getCurrentHighPrioityTicketId().equals("0")) {
				System.out.println(result.get(i).getSEusername().getUsername());
				sb.setServiceEngineerId(result.get(i).getServiceEngineerId());
				ticket.setServiceengineer(sb);
				ticket.setTicketStatus("New");
				System.out.println(ticket.getServiceengineer().getServiceEngineerId());
				sb1 = em.find(ServiceEngineerBean.class, sb.getServiceEngineerId());
				sb1.setCurrentHighPrioityTicketId(ticket.getTicketId());
				em.persist(ticket);
				em.getTransaction().commit();
				return 1;
			} 
		}
		Query unavailableengineer=em.createQuery("select e from EndUserBean e where e.dept=" + ticket.getDept().getDeptNo() +"order by ticketPriority asc , dateOfAction desc");
		List<EndUserBean> selist = unavailableengineer.getResultList();
		for (int i = 0; i < selist.size(); i++) {
			sb=em.find(ServiceEngineerBean.class,selist.get(i).getServiceengineer().getServiceEngineerId());
			sb1.setServiceEngineerId(selist.get(i).getServiceengineer().getServiceEngineerId());
			int j=sb.getPending() + 1;
			int x=Integer.parseInt(selist.get(i).getTicketPriority());
			if(x>=Integer.parseInt(ticket.getTicketPriority()) && j < 8)
			{
				ticket.setServiceengineer(sb1);
				ticket.setTicketStatus("Pending");
				em.persist(ticket);
				sb.setPending(j);
				em.getTransaction().commit();
				return 1;
			}
			else if(x<Integer.parseInt(ticket.getTicketPriority())&&j < 8)
			{
				String h=sb.getCurrentHighPrioityTicketId();
				ticket.setServiceengineer(sb1);
				ticket.setTicketStatus("New");
				em.persist(ticket);
				sb.setCurrentHighPrioityTicketId(ticket.getTicketId());
				sb.setPending(j);
				EndUserBean e1=new EndUserBean();
				e1=em.find(EndUserBean.class, h);
				e1.setTicketStatus("Waiting");
				em.getTransaction().commit();
				return 1;
			}			
		}
		em.getTransaction().commit();
		return 0;
	}

	public String gen() {
		String x;
		final Random RANDOM = new Random();
		String digits = "0123456789";
		StringBuilder returnValue = new StringBuilder(5);
		String v = "TKTID";
		for (int i = 0; i < 5; i++) {
			returnValue.append(digits.charAt(RANDOM.nextInt(digits.length())));
		}
		x = returnValue.toString();
		x = v + x;
		return x;
	}

	public List<EndUserBean> viewticket(EndUserBean eb) {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		ArrayList<BeanClasses.EndUserBean> dao = new ArrayList<>();
		System.out.println("what is this ?" +eb.getusername().getUsername());
		Query q = em.createQuery("select e from EndUserBean e where e.username=(select u from Bean u where u.username=?1)").setParameter(1, eb.getusername().getUsername());
		//Query q = em.createQuery("select e from EndUserBean e where e.username="+eb.getUsername().getUsername());
		//Query q = em.createQuery("select s from ServiceEngineerBean s where s.dept=" + ticket.getDept().getDeptNo());
		List<EndUserBean> tickets=(List<EndUserBean>) q.getResultList();
		deptInfo dp=new deptInfo();
		ServiceEngineerBean sb=new ServiceEngineerBean();
		Bean usernamebean=new Bean();
		for(int i=0;i<tickets.size();i++)
			{
				String bd = null;
				String cd =  null;
				dp.setDeptNo(tickets.get(i).getDept().getDeptNo());
				sb.setServiceEngineerId(tickets.get(i).getServiceengineer().getServiceEngineerId());
				usernamebean.setUsername(tickets.get(i).getusername().getUsername());
				// rs1.getDate(8).toString(),rs1.getDate(9).toString()
				if(tickets.get(i).getDateOfAction()== null) {
					bd = "";
				}
				if(tickets.get(i).getDateOfCompletion() == null) {
					cd = "";
				}
				if(tickets.get(i).getDateOfAction() == null && tickets.get(i).getDateOfCompletion() == null)
					dao.add(new EndUserBean(tickets.get(i).getTicketId(),tickets.get(i).getusername(),bd,cd,tickets.get(i).getDateOfIssue().toString(),tickets.get(i).getRequestedEndDAte().toString(),tickets.get(i).getTicketPriority(),tickets.get(i).getTicketStatus(),tickets.get(i).getWorkStation(),dp,sb,tickets.get(i).getSubject()));
				else if(tickets.get(i).getDateOfAction() != null && tickets.get(i).getDateOfCompletion() == null)
					dao.add(new EndUserBean(tickets.get(i).getTicketId(),tickets.get(i).getusername(),tickets.get(i).getDateOfAction().toString(),cd,tickets.get(i).getDateOfIssue().toString(),tickets.get(i).getRequestedEndDAte().toString(),tickets.get(i).getTicketPriority(),tickets.get(i).getTicketStatus(),tickets.get(i).getWorkStation(),dp,sb,tickets.get(i).getSubject()));
				else if(tickets.get(i).getDateOfAction() != null && tickets.get(i).getDateOfCompletion() != null)
					dao.add(new EndUserBean(tickets.get(i).getTicketId(),tickets.get(i).getusername(),tickets.get(i).getDateOfAction().toString(),tickets.get(i).getDateOfCompletion().toString(),tickets.get(i).getDateOfIssue().toString(),tickets.get(i).getRequestedEndDAte().toString(),tickets.get(i).getTicketPriority(),tickets.get(i).getTicketStatus(),tickets.get(i).getWorkStation(),dp,sb,tickets.get(i).getSubject()));
			}
		em.getTransaction().commit();
		return dao;
	}
}
