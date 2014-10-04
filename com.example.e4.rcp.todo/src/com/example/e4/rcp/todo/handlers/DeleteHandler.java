package com.example.e4.rcp.todo.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.example.e4.rcp.tables.ContactDetails;
import com.example.e4.rcp.tables.Geneder;
import com.example.e4.rcp.tables.Status;
import com.example.e4.rcp.tables.UserDetails;
import com.example.e4.rcp.tables.hibernateDB;

public class DeleteHandler {
	private static SessionFactory sessionFactory;
	@Execute
	  public void execute() {
		
	    System.out.println((this.getClass().getSimpleName() + " called"));
	    ContactDetails cd = new ContactDetails();
	    cd.setAge(13);
	    cd.setName("ASD");
	    cd.setStatus(Status.Married);
	    cd.setGender(Geneder.MALE);
	    
	    UserDetails us = new UserDetails();
	    us.setUserName("asd");
	    us.setAge(14);
	 
	    hibernateDB hdb = new hibernateDB();
	    hdb.setUserId(1);
	    hdb.setUserName("asd");
	    Configuration config = new Configuration();
		config.configure();
		StandardServiceRegistryBuilder ssrb =new StandardServiceRegistryBuilder().applySettings(config.getProperties());
		sessionFactory = config.buildSessionFactory(ssrb.build());
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(hdb);
		session.getTransaction().commit();
		sessionFactory.close();
	  }
}
