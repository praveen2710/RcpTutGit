package com.example.e4.rcp.tables;

import java.io.File;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory sessionFactory = null; 
	 public static SessionFactory getSessionFactory() { 
	 if (sessionFactory == null) { 
	 try { 
	 sessionFactory = new Configuration(). 
	 configure(new File("hibernate.cfg.xml")).buildSessionFactory(); 
	 } 
	 catch (Throwable ex) { 
	 throw new ExceptionInInitializerError(ex); 
	 } 
	 } 
	 return sessionFactory; 
	 } 
}
