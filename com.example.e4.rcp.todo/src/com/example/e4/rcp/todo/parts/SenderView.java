package com.example.e4.rcp.todo.parts;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;



public class SenderView {
	  @Inject
	  private IEventBroker eventBroker;
	  
	  private Button b;
	 
	  @PostConstruct
	  void init(Composite parent) {
		System.out.println("In sender View");
	    parent.setLayout(new GridLayout());
	    b = new Button(parent, SWT.PUSH);
	    b.setText("Send Event");
	    b.addSelectionListener(new SelectionAdapter() {
	      @Override
	      public void widgetSelected(SelectionEvent e) {
	        String d = " hahahahahah";
	        eventBroker.send("viewcommunication/syncEvent",d);
	        eventBroker.post("viewcommunication/asyncEvent", d);
	      }
	    });
	  }
	 
	  @Focus
	  void focus() {
	    b.setFocus();
	  }
	}