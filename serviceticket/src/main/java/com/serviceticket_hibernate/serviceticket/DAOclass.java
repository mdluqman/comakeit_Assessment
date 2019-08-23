package com.serviceticket_hibernate.serviceticket;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import BeanClasses.Bean;
import BeanClasses.usertypeinfo;

public class DAOclass {
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ServiceTicket");
	EntityManager em = entityManagerFactory.createEntityManager();

	public String clientlogin(Bean AccountHolder) {
		em.getTransaction().begin();
		Bean accountholder = new Bean();
		String uname = AccountHolder.getUsername();
		System.out.println("hi2");
		accountholder = em.find(Bean.class, uname);
		if(accountholder==null)
		{
			return "NO USER FOUND WITH SUCH USERNAME";
		}
		else
		{
			if(accountholder.getPassword().equals(AccountHolder.getPassword()))
			{
				em.getTransaction().commit();
				return accountholder.getUsertype().getTypeOfUser();				
			}
			else
				return "INCORRECT PASSWORD!";
		}

	}


}
