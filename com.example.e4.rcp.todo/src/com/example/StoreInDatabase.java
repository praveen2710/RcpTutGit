package com.example;


import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.example.e4.rcp.tables.UserDetails;
import com.example.e4.rcp.tables.hibernateDB;

public class StoreInDatabase {
	private static SessionFactory sessionFactory;
	
	public void writeToDatabase(UserDetails details){
		Configuration config = new Configuration();
		config.configure();
		StandardServiceRegistryBuilder ssrb =new StandardServiceRegistryBuilder().applySettings(config.getProperties());
		sessionFactory = config.buildSessionFactory(ssrb.build());
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(details);
		session.getTransaction().commit();
		sessionFactory.close();
	}
	
	public  List<UserDetails> listAllUsers(){
		Configuration config = new Configuration();
		config.configure();
		StandardServiceRegistryBuilder ssrb =new StandardServiceRegistryBuilder().applySettings(config.getProperties());
		sessionFactory = config.buildSessionFactory(ssrb.build());
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List users = null;
		try{
			tx = session.beginTransaction();
			// class name not the data table name
			users  = session.createQuery("from UserDetails").list();
			for(Iterator iterator = users.iterator(); iterator.hasNext();){
				UserDetails user = (UserDetails) iterator.next();
				System.out.println("Name"+user.getUserName());
			}
		    tx.commit();
		   
		}catch(HibernateException e){
			if(tx!=null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return users;
	}
}
