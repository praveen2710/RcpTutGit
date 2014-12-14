package com.example.e4.rcp.todo.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.example.StoreInDatabase;
//import com.example.e4.rcp.dialogues.MyTitleAreaDialog;
import com.example.e4.rcp.tables.ContactDetails;
import com.example.e4.rcp.tables.Geneder;
import com.example.e4.rcp.tables.Status;
import com.example.e4.rcp.tables.UserDetails;
import com.example.e4.rcp.tables.hibernateDB;
import com.sun.java.swing.plaf.windows.resources.windows;

public class DeleteHandler {
	@Execute
	  public void execute() {
		Shell shell = null;
//		MyTitleAreaDialog dialog = new MyTitleAreaDialog(shell);
//		dialog.create();
//		if(dialog.open() == Window.OK){
//			System.out.println(dialog.getFirstName());;
//		}
	  }
}
