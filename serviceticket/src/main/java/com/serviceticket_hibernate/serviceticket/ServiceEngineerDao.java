package com.serviceticket_hibernate.serviceticket;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import BeanClasses.Bean;
import BeanClasses.EndUserBean;
import BeanClasses.ServiceEngineerBean;
import BeanClasses.deptInfo;



public class ServiceEngineerDao {

	java.util.Date date = new java.util.Date();
	java.sql.Date sqlDate = new java.sql.Date(date.getTime());
    Date dobj=sqlDate;
	public List<EndUserBean> viewticket(ServiceEngineerBean seb) {
		
		// TODO Auto-generated method stub
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ServiceTicket");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		ArrayList<EndUserBean> dao = new ArrayList<>();
		System.out.println("hi in DAO " + seb.getSEusername().getUsername());
		Query q = entityManager.createQuery(
				"select t from EndUserBean t where t.serviceengineer=(select s from ServiceEngineerBean s where s.SEusername=(select b from Bean b where b.username=?1))order by t.ticketPriority desc,t.dateOfIssue desc")
				.setParameter(1, seb.getSEusername().getUsername());
		List<EndUserBean> tickets = (List<EndUserBean>) q.getResultList();
		deptInfo dp = new deptInfo();
		ServiceEngineerBean sb = new ServiceEngineerBean();
		Bean usernamebean = new Bean();
		for (int i = 0; i < tickets.size(); i++) {
			String bd = null;
			String cd = null;
			dp.setDeptNo(tickets.get(i).getDept().getDeptNo());
			sb.setServiceEngineerId(tickets.get(i).getServiceengineer().getServiceEngineerId());
			usernamebean.setUsername(tickets.get(i).getusername().getUsername());
			if (tickets.get(i).getDateOfAction() == null) {
				bd = "";
			}
			if (tickets.get(i).getDateOfCompletion() == null) {
				cd = "";
			}
			if (tickets.get(i).getDateOfAction() == null && tickets.get(i).getDateOfCompletion() == null)
				dao.add(new EndUserBean(tickets.get(i).getTicketId(), tickets.get(i).getusername(), bd, cd,
						tickets.get(i).getDateOfIssue().toString(), tickets.get(i).getRequestedEndDAte().toString(),
						tickets.get(i).getTicketPriority(), tickets.get(i).getTicketStatus(),
						tickets.get(i).getWorkStation(), dp, sb, tickets.get(i).getSubject()));
			else if (tickets.get(i).getDateOfAction() != null && tickets.get(i).getDateOfCompletion() == null)
				dao.add(new EndUserBean(tickets.get(i).getTicketId(), tickets.get(i).getusername(),
						tickets.get(i).getDateOfAction().toString(), cd, tickets.get(i).getDateOfIssue().toString(),
						tickets.get(i).getRequestedEndDAte().toString(), tickets.get(i).getTicketPriority(),
						tickets.get(i).getTicketStatus(), tickets.get(i).getWorkStation(), dp, sb,
						tickets.get(i).getSubject()));
			else if (tickets.get(i).getDateOfAction() != null && tickets.get(i).getDateOfCompletion() != null)
				dao.add(new EndUserBean(tickets.get(i).getTicketId(), tickets.get(i).getusername(),
						tickets.get(i).getDateOfAction().toString(), tickets.get(i).getDateOfCompletion().toString(),
						tickets.get(i).getDateOfIssue().toString(), tickets.get(i).getRequestedEndDAte().toString(),
						tickets.get(i).getTicketPriority(), tickets.get(i).getTicketStatus(),
						tickets.get(i).getWorkStation(), dp, sb, tickets.get(i).getSubject()));
		}
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
		return dao;

	}

	public List<String> ReportperS() {
		// TODO Auto-generated method stub
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ServiceTicket");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<String> avgseverity = new ArrayList<String>();
		for(int i=1;i<4;i++)
		{
		List a =	entityManager.createNativeQuery(
		"select (AVG(TIMESTAMPDIFF(day,  dateOfCompletion,dateOfAction))) from EndUserBean where ticketStatus = ?1 and ticketPriority=?2")
		.setParameter(1, "Completed").setParameter(2, i).getResultList();
//		System.out.println(a.get(0).toString());
		try
		{
			avgseverity.add(a.get(0).toString());
		}
		catch(NullPointerException e)
		{
			avgseverity.add("-----");
		}
		}
		for(int j=0;j<3;j++)
		{
			System.out.println(avgseverity.get(j));
		}
			entityManager.getTransaction().commit();
			return avgseverity;
	}

	public List<String> ReportperSE() {
		// TODO Auto-generated method stub
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ServiceTicket");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<String> avgservice = new ArrayList<String>();
		List<ServiceEngineerBean> l =(List<ServiceEngineerBean>) entityManager.createQuery("select l from ServiceEngineerBean l").getResultList();
		for(int j=0;j<l.size();j++)
		{
		List a =	entityManager.createNativeQuery(
		"select (AVG(TIMESTAMPDIFF(day, dateOfCompletion, dateOfAction))) from EndUserBean  where ticketStatus = ?1 and serviceengineer_ServiceEngineerId=?2")
		.setParameter(1, "Completed").setParameter(2, l.get(j).getServiceEngineerId()).getResultList();
		//System.out.println(a.get(0).toString());
		avgservice.add(l.get(j).getSEusername().getUsername().toString());
		if(a.get(0)==null)
		{
			avgservice.add("not a single ticket has been resolved");
		}
		else {
		avgservice.add(a.get(0).toString());
		}
		}
		for(int i=0;i<avgservice.size();i++)
		{
			System.out.println(avgservice.get(i));
		}
		return avgservice;
	}

	public List<String> avgage(ServiceEngineerBean seb) {
		// TODO Auto-generated method stub
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ServiceTicket");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Query query= entityManager.createQuery("select t from ServiceEngineerBean t where t.SEusername=:emp ").setParameter("emp", seb.getSEusername());
		List<ServiceEngineerBean> s=query.getResultList();
		System.out.println(s.get(0).getSEusername().getUsername().toString()+"have id as");
		seb.setServiceEngineerId(s.get(0).getServiceEngineerId());
		EndUserBean eb=new EndUserBean();
		eb.setServiceengineer(seb);
		System.out.println(seb.getServiceEngineerId());
		Query q= entityManager.createQuery("select h from EndUserBean h where h.serviceengineer =:p and h.ticketStatus!=:pooji ").setParameter("p", eb.getServiceengineer()).setParameter("pooji", "Completed");
		List<EndUserBean> e=q.getResultList();
		List<String> x = new ArrayList<String>();
		if(e.size()>0) {
		for(int i=0;i<e.size();i++) {
			List a =	 entityManager.createNativeQuery(
					"select TIMESTAMPDIFF(day, '" + e.get(i).getDateOfIssue() + "', '" + dobj + "') ").getResultList();

			x.add(e.get(i).getTicketId().toString());
			x.add(a.get(0).toString());
					}
		}
		return x;
	}

	public int changestat(EndUserBean eb) {
		// TODO Auto-generated method stub
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ServiceTicket");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EndUserBean beanobject = entityManager.find(EndUserBean.class, eb.getTicketId());
		ServiceEngineerBean s = entityManager.find(ServiceEngineerBean.class,
				beanobject.getServiceengineer().getServiceEngineerId());

		// CHANGING STATUS TO WIP
		if (eb.getTicketStatus().equals("WorkInProgress") && beanobject.getTicketStatus().equals("New")) {
			System.out.println(eb.getTicketId() + " " + eb.getTicketStatus());
			entityManager.getTransaction().begin();
			beanobject.setTicketStatus("WorkInProgress");
			beanobject.setDateOfAction(sqlDate.toString());
			s.setCurrentHighPrioityTicketId(eb.getTicketId());
			entityManager.persist(beanobject);
			entityManager.persist(s);
			entityManager.getTransaction().commit();
			return 1;
		}
		// CHANGING STATUS FROM NEW TO WAITING
		else if (eb.getTicketStatus().equals("Waiting") && beanobject.getTicketStatus().equals("New")) {
			System.out.println("1");
			Query query = entityManager.createQuery(
					"select t from EndUserBean t where t.serviceengineer =:emp and t.ticketStatus=:poo order by t.ticketPriority desc");
			query.setParameter("emp", beanobject.getServiceengineer());
			query.setParameter("poo", "Waiting");
			List<EndUserBean> list = query.getResultList();
			Query q1 = entityManager.createQuery(
					"select t from EndUserBean t  where t.serviceengineer=?1 and t.ticketStatus=?2 ORDER BY t.ticketPriority desc,field ('ticketStatus','New','Waiting','Pending')")
					.setParameter(1, beanobject.getServiceengineer()).setParameter(2, "Pending");
			List<EndUserBean> list1 = q1.getResultList();
			if (list.size() > 0) {

				EndUserBean beanobject1 = entityManager.find(EndUserBean.class, list.get(0).getTicketId());
				entityManager.getTransaction().begin();
				beanobject1.setTicketStatus("New");
				s.setCurrentHighPrioityTicketId(list.get(0).getTicketId());
				beanobject.setTicketStatus("Waiting");
				entityManager.persist(beanobject);
				entityManager.persist(beanobject1);
				entityManager.persist(s);
				entityManager.getTransaction().commit();
				return 1;
			} else {
				if (list1.size() > 0) {
					EndUserBean beanobject1 = entityManager.find(EndUserBean.class, list1.get(0).getTicketId());
					entityManager.getTransaction().begin();
					beanobject1.setTicketStatus("New");
					s.setCurrentHighPrioityTicketId(list1.get(0).getTicketId());
					beanobject.setTicketStatus("Waiting");
					entityManager.persist(beanobject);
					entityManager.persist(beanobject1);
					entityManager.persist(s);
					entityManager.getTransaction().commit();
					return 1;
				} else {
					entityManager.getTransaction().begin();
					s.setCurrentHighPrioityTicketId("0");
					beanobject.setTicketStatus("Waiting");
					entityManager.persist(beanobject);
					entityManager.persist(s);
					entityManager.getTransaction().commit();
					return 1;
				}
			}
		}
		// changing status FROM WORK_IN_PROGRESS to waiting
		else if (eb.getTicketStatus().equals("Waiting") && beanobject.getTicketStatus().equals("WorkInProgress")) {
			Query qu = entityManager.createQuery(
					"select t from EndUserBean t  where t.serviceengineer=?1 and t.ticketStatus=?2 ORDER BY t.ticketPriority desc")
					.setParameter(1, beanobject.getServiceengineer()).setParameter(2, "Waiting");

			List<EndUserBean> list3 = qu.getResultList();
			Query que = entityManager.createQuery(
					"select t from EndUserBean t  where t.serviceengineer=?1 and t.ticketStatus=?2 ORDER BY t.ticketPriority desc,field ('ticketStatus','New','Waiting','Pending')")
					.setParameter(1, beanobject.getServiceengineer()).setParameter(2, "Pending");
			List<EndUserBean> list4 = que.getResultList();
			if (list3.size() > 0) {
				System.out.println(list3.get(0).getTicketId());
				EndUserBean beanobject1 = entityManager.find(EndUserBean.class, list3.get(0).getTicketId());
				entityManager.getTransaction().begin();
				beanobject1.setTicketStatus("New");
				s.setCurrentHighPrioityTicketId(list3.get(0).getTicketId());
				entityManager.persist(beanobject1);
				entityManager.persist(s);
				beanobject.setTicketStatus("Waiting");
				entityManager.persist(beanobject);
				entityManager.getTransaction().commit();
				return 1;
			} else {
				if (list4.size() > 0) {
					EndUserBean beanobject2 = entityManager.find(EndUserBean.class, list4.get(0).getTicketId());
					entityManager.getTransaction().begin();
					beanobject2.setTicketStatus("New");
					s.setCurrentHighPrioityTicketId(list4.get(0).getTicketId());
					entityManager.persist(beanobject2);
					entityManager.persist(s);
					beanobject.setTicketStatus("Waiting");
					entityManager.persist(beanobject);
					entityManager.getTransaction().commit();
					return 1;

				} else {
					entityManager.getTransaction().begin();
					s.setCurrentHighPrioityTicketId("0");
					entityManager.persist(s);
					beanobject.setTicketStatus("Waiting");
					entityManager.persist(beanobject);
					entityManager.getTransaction().commit();
					return 1;
				}

			}
		}
		// CHANGING STATUS FROM WORK_IN_PROGRESS TO COMPLETED
		else if (eb.getTicketStatus().equals("Completed") && beanobject.getTicketStatus().equals("WorkInProgress")) {
			int l = s.getPending() - 1;
			int b = s.getTotalTickets() + 1;
			System.out.println("THAT SEID HAS TT AND PEND- " + l + " " + b);
			Query q = entityManager.createQuery(
					"select t from EndUserBean t  where t.serviceengineer=?1 and t.ticketStatus=?2 ORDER BY t.ticketPriority desc")
					.setParameter(1, beanobject.getServiceengineer()).setParameter(2, "Waiting");
			List<EndUserBean> list2 = q.getResultList();
			Query q7 = entityManager.createQuery(
					"select t from EndUserBean t  where t.serviceengineer=?1 and t.ticketStatus=?2 ORDER BY t.ticketPriority desc,field ('ticketStatus','New','Waiting','Pending')")
					.setParameter(1, beanobject.getServiceengineer()).setParameter(2, "Pending");
			List<EndUserBean> list7 = q7.getResultList();
			if (list2.size() > 0) {
				EndUserBean beanobject1 = entityManager.find(EndUserBean.class, list2.get(0).getTicketId());
				entityManager.getTransaction().begin();
				System.out.println("total tik :" + b + " " + l);
				beanobject1.setTicketStatus("New");
				s.setCurrentHighPrioityTicketId(list2.get(0).getTicketId());
				s.setTotalTickets(b);
				s.setPending(l);
				entityManager.persist(beanobject1);
				entityManager.persist(s);
				beanobject.setTicketStatus("Completed");
				beanobject.setDateOfCompletion(sqlDate.toString());
				entityManager.persist(beanobject);
				entityManager.getTransaction().commit();
				return 1;
			} else {
				if (list7.size() > 0) {
					EndUserBean beanobject1 = entityManager.find(EndUserBean.class, list7.get(0).getTicketId());
					entityManager.getTransaction().begin();
					s.setTotalTickets(b);
					s.setPending(l);
					beanobject1.setTicketStatus("New");
					s.setCurrentHighPrioityTicketId(list7.get(0).getTicketId());
					beanobject.setTicketStatus("Completed");
					beanobject.setDateOfCompletion(sqlDate.toString());
					entityManager.persist(beanobject);
					entityManager.persist(beanobject1);
					entityManager.persist(s);
					entityManager.getTransaction().commit();
					return 1;
				} else {
					entityManager.getTransaction().begin();
					s.setTotalTickets(b);
					s.setCurrentHighPrioityTicketId("0");
					beanobject.setTicketStatus("Completed");
					beanobject.setDateOfCompletion(sqlDate.toString());
					entityManager.persist(beanobject);
					entityManager.persist(s);
					entityManager.getTransaction().commit();
					return 1;
				}
			}
		}
		return 3;
	}

	public int changeprio(EndUserBean eb) {
		System.out.println("changeprio in dao entered");
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ServiceTicket");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EndUserBean beanobject = entityManager.find(EndUserBean.class, eb.getTicketId());
		System.out.println("changeprio working");
		entityManager.getTransaction().begin();
		beanobject.setTicketPriority(eb.getTicketPriority());
		entityManager.persist(beanobject);
		entityManager.getTransaction().commit();
		System.out.println("changeprio worked");
		return 1;

	}
}