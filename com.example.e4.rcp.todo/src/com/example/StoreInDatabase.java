package com.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.example.e4.rcp.tables.UserDetails;
import com.example.e4.rcp.tables.hibernateDB;

public class StoreInDatabase {
	private static SessionFactory sessionFactory;
	
	public void writeToDatabase(hibernateDB user){
		Configuration config = new Configuration();
		config.configure();
		StandardServiceRegistryBuilder ssrb =new StandardServiceRegistryBuilder().applySettings(config.getProperties());
		sessionFactory = config.buildSessionFactory(ssrb.build());
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		sessionFactory.close();
	}
}
