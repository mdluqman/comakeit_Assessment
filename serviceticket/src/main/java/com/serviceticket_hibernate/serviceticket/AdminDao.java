package com.serviceticket_hibernate.serviceticket;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.google.protobuf.Service;

import BeanClasses.Bean;
import BeanClasses.ServiceEngineerBean;
import BeanClasses.deptInfo;
import BeanClasses.usertypeinfo;

public class AdminDao {

	public String clientregister(Bean accuser) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ServiceTicket");
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		// System.out.println(accuser.getUsername()+"*"+accuser.getPassword());
		Bean accountholder2 = new Bean();
		accountholder2 = em.find(Bean.class, accuser.getUsername());
		System.out.println(accuser.getUsername() + " " + accuser.getPassword());
		if (accountholder2 != null) {
			return "username_exists";
		} else {
			em.persist(accuser);
			em.getTransaction().commit();
			return "user_registered";
		}
	}

	public void seregister(ServiceEngineerBean seb) {
		// TODO Auto-generated method stub
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ServiceTicket");
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		System.out.println(seb.getSEusername().getUsername()+ " in dao" + seb.getDept().getDeptNo()+" "+seb.getServiceEngineerId());
		em.persist(seb);
		em.getTransaction().commit();
	}

	public List<Bean> getbean() {
		// TODO Auto-generated method stub
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ServiceTicket");
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		Query q = em.createNamedQuery("selectusers");
		@SuppressWarnings("unchecked")
		List<Bean> user = (List<Bean>) q.getResultList();
		em.getTransaction().commit();
		return user;
	}
	
	public List<usertypeinfo> getusertype() {
		// TODO Auto-generated method stub
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ServiceTicket");
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		Query q = em.createNamedQuery("selectusertype");
		@SuppressWarnings("unchecked")
		List<usertypeinfo> user = (List<usertypeinfo>) q.getResultList();
		em.getTransaction().commit();
		return user;
	}

	public List<ServiceEngineerBean> getserviceengineer() {
		// TODO Auto-generated method stub
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ServiceTicket");
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		Query q = em.createNamedQuery("selectengineers");
		@SuppressWarnings("unchecked")
		List<ServiceEngineerBean> serviceengineer= (List<ServiceEngineerBean>) q.getResultList();
		em.getTransaction().commit();
		return serviceengineer;
	}

	public void delete2(ServiceEngineerBean b) {
		// TODO Auto-generated method stub
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ServiceTicket");
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		Bean bean = em.find(Bean.class, b.getSEusername().getUsername());
		em.remove(bean);
		ServiceEngineerBean x=em.find(ServiceEngineerBean.class, b.getServiceEngineerId());
		em.remove(x);
		em.getTransaction().commit();

	}
	
	public void delete13(Bean b) {
		// TODO Auto-generated method stub
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ServiceTicket");
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		Bean bean = em.find(Bean.class, b.getUsername());
		em.remove(bean);
		em.getTransaction().commit();

	}
}
