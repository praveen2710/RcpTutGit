package com.example.e4.rcp.todo.parts;

import javax.annotation.PostConstruct;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class TextBoxExample {
	
	private Text orgNameTextBox;
	
	 @PostConstruct
	 public void createControls(Composite parent){
		 
		GridLayout layout = new GridLayout(3, false);
		parent.setLayout(layout);
		Label searchLabel = new Label(parent, SWT.NONE);
		searchLabel.setText("OrgName");
		searchLabel.setToolTipText("Enter Name Of Org");
		
		orgNameTextBox = new Text(parent, SWT.SEARCH);
		orgNameTextBox.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL
				| GridData.HORIZONTAL_ALIGN_FILL));
	 }
}
