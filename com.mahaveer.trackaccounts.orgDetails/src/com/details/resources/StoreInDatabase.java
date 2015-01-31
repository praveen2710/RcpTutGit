package com.details.resources;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.details.tables.Address;
import com.details.tables.DatabaseAccess;
import com.details.tables.OrgDetails;
import com.details.tables.ProductsTable;

public class StoreInDatabase {
	private static SessionFactory sessionFactory;
	

	
	public void writeToDatabase(List<DatabaseAccess> affectedTables){
		Configuration config = new Configuration();
		config.configure();
		StandardServiceRegistryBuilder ssrb =new StandardServiceRegistryBuilder().applySettings(config.getProperties());
		sessionFactory = config.buildSessionFactory(ssrb.build());
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		for(DatabaseAccess tablesList: affectedTables){
		session.save(tablesList);
		}
		session.getTransaction().commit();
		sessionFactory.close();
	}
	
//	public  List<UserDetails> listAllUsers(){
//		Configuration config = new Configuration();
//		config.configure();
//		StandardServiceRegistryBuilder ssrb =new StandardServiceRegistryBuilder().applySettings(config.getProperties());
//		sessionFactory = config.buildSessionFactory(ssrb.build());
//		Session session = sessionFactory.openSession();
//		Transaction tx = null;
//		List users = null;
//		try{
//			tx = session.beginTransaction();
//			// class name not the data table name
//			users  = session.createQuery("from UserDetails").list();
//			for(Iterator iterator = users.iterator(); iterator.hasNext();){
//				UserDetails user = (UserDetails) iterator.next();
//				System.out.println("Name"+user.getUserName());
//			}
//		    tx.commit();
//		   
//		}catch(HibernateException e){
//			if(tx!=null) tx.rollback();
//			e.printStackTrace();
//		}finally{
//			session.close();
//		}
//		return users;
//	}
//
//	public void updateUserName(int userId,String newName){
//		Configuration config = new Configuration();
//		config.configure();
//		StandardServiceRegistryBuilder ssrb =new StandardServiceRegistryBuilder().applySettings(config.getProperties());
//		sessionFactory = config.buildSessionFactory(ssrb.build());
//		Session session = sessionFactory.openSession();
//		Transaction tx = null;
//		try{
//			tx = session.beginTransaction();
//			UserDetails ud = (UserDetails)session.get(UserDetails.class,userId);
//			ud.setUserName(newName);
//			session.update(ud);
//			tx.commit();
//		}catch(HibernateException e){
//			if(tx!=null) tx.rollback();
//			e.printStackTrace();
//		}finally{
//			session.close();
//		}
//	}
	
	public List<?> retrieveFromDatabase(String parameter){
		Configuration config = new Configuration();
		config.configure();
		StandardServiceRegistryBuilder ssrb =new StandardServiceRegistryBuilder().applySettings(config.getProperties());
		sessionFactory = config.buildSessionFactory(ssrb.build());
		Session session = sessionFactory.openSession();
		List<?> orgs = null;
		try{
			Query query = session.createQuery("from OrgDetails where orgName = :name");
			query.setParameter("name",parameter);
			 orgs = query.list();
			
		}catch(HibernateException e){
			e.printStackTrace();
		}
		finally{
			session.close();
		}
		return orgs;
	}
	
	public List<?> retrieveAllOrgNames(){
		Configuration config = new Configuration();
		config.configure();
		StandardServiceRegistryBuilder ssrb =new StandardServiceRegistryBuilder().applySettings(config.getProperties());
		sessionFactory = config.buildSessionFactory(ssrb.build());
		Session session = sessionFactory.openSession();
		List<?> orgsNames = null;
		Criteria criteria = session.createCriteria(OrgDetails.class);
		criteria.setProjection(Projections.property("orgName"));

		return criteria.list();
	}
	
	public List<?> retrieveAllOrgAllDetails(){
		Configuration config = new Configuration();
		config.configure();
		StandardServiceRegistryBuilder ssrb =new StandardServiceRegistryBuilder().applySettings(config.getProperties());
		sessionFactory = config.buildSessionFactory(ssrb.build());
		Session session = sessionFactory.openSession();
		List<?> orgsDetails = null;
		Query query = session.createQuery("from OrgDetails");
		
		orgsDetails = query.list();
		
		return orgsDetails;
	}
	
	public void updateOrgDetails(OrgDetails orgUpdate,Address addressUpdate,Long orgId){
		Configuration config = new Configuration();
		config.configure();
		StandardServiceRegistryBuilder ssrb =new StandardServiceRegistryBuilder().applySettings(config.getProperties());
		sessionFactory = config.buildSessionFactory(ssrb.build());
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();	
			OrgDetails od = (OrgDetails)session.get(OrgDetails.class,orgId); 
			System.out.println(orgUpdate.getId());
			od.setOrgName(orgUpdate.getOrgName());
			od.setOrgName(orgUpdate.getOrgName());
			od.setTin(orgUpdate.getTin());
			od.setPrimaryNumber(orgUpdate.getPrimaryNumber());
			od.setPrimaryPerson(orgUpdate.getPrimaryNumber());
			Address ad = (Address) session.get(Address.class,od.getOrgAddress().getId());
			ad.setAddress(addressUpdate.getAddress());
			ad.setPostalCode(addressUpdate.getPostalCode());
			tx.commit();
		}catch(HibernateException e){
			if(tx!=null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	public List<?> retrieveAllProducts(){
		Configuration config = new Configuration();
		config.configure();
		StandardServiceRegistryBuilder ssrb =new StandardServiceRegistryBuilder().applySettings(config.getProperties());
		sessionFactory = config.buildSessionFactory(ssrb.build());
		Session session = sessionFactory.openSession();
		List<?> orgsDetails = null;
		Query query = session.createQuery("from ProductsTable");
		
		orgsDetails = query.list();
		
		return orgsDetails;
	}
	
	public Object retrieveProductQuery(String parameter){
		
		Configuration config = new Configuration();
		config.configure();
		StandardServiceRegistryBuilder ssrb =new StandardServiceRegistryBuilder().applySettings(config.getProperties());
		sessionFactory = config.buildSessionFactory(ssrb.build());
		Session session = sessionFactory.openSession();
		Object orgData = null;
		try{
			Criteria cr = session.createCriteria(ProductsTable.class);
			cr.add(Restrictions.like("productName", parameter));
//			cr.setProjection(Projections.property("productName"));						
			if(cr.list().isEmpty()) 
				orgData = null;
			else
				orgData = cr.list().get(0);
		}catch(HibernateException e){
			e.printStackTrace();
		}
		finally{
			session.close();
		}
		return orgData;
	}
}

