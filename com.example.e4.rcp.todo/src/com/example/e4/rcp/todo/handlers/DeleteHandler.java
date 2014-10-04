package com.example.e4.rcp.todo.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.example.StoreInDatabase;
import com.example.e4.rcp.tables.ContactDetails;
import com.example.e4.rcp.tables.Geneder;
import com.example.e4.rcp.tables.Status;
import com.example.e4.rcp.tables.UserDetails;
import com.example.e4.rcp.tables.hibernateDB;

public class DeleteHandler {
	private static SessionFactory sessionFactory;
	@Execute
	  public void execute() {
		StoreInDatabase sb = new StoreInDatabase();
		sb.listAllUsers();
	  }
}
