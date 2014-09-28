package com.example.e4.rcp.todo.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.example.e4.rcp.tables.ContactDetails;
import com.example.e4.rcp.tables.Geneder;
import com.example.e4.rcp.tables.HibernateUtil;
import com.example.e4.rcp.tables.Status;

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
	    Configuration config = new Configuration();
		config.configure();
		StandardServiceRegistryBuilder ssrb =new StandardServiceRegistryBuilder().applySettings(config.getProperties());
		sessionFactory = config.buildSessionFactory(ssrb.build());
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(cd);
		session.getTransaction().commit();
		sessionFactory.close();
	  }
}
