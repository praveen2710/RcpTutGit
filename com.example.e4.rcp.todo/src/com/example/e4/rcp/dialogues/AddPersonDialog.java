package com.example.e4.rcp.dialogues;


import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.example.e4.rcp.tables.ContactDetails;
import com.example.e4.rcp.tables.UserDetails;

public class AddPersonDialog extends TitleAreaDialog{

	private Text personName;
	private Text contactNumber;
	private ContactDetails person;
	
	public ContactDetails getPerson(){
		return person;
	}
	
	public AddPersonDialog(Shell parentShell) {
		super(parentShell);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.TitleAreaDialog#createContents(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void create() {
		super.create();
		setTitle("Add a new Person");
		setMessage("Please enter person details",IMessageProvider.INFORMATION);
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.TitleAreaDialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		GridLayout layout = new GridLayout(2,false);
		parent.setLayout(layout);
		
		Label userNameLabel = new Label(parent,SWT.NONE);
		userNameLabel.setText("Enter Person's Name");
		personName = new Text(parent, SWT.NONE);
		
		Label ageLabel = new Label(parent,SWT.NONE);
		ageLabel.setText("Phone Number");
		contactNumber = new Text(parent,SWT.NONE);
		contactNumber.setTextLimit(10);
			
		return parent;
	}
	
	
	@Override
	protected void okPressed(){
		person = new ContactDetails();
		
		if(personName.getText().length()!=0){
			person.setPersonName(personName.getText());
		}else{
			setErrorMessage("Please Enter Valid datA");
		}
		
		try{
			System.out.println(contactNumber.getText().length());
			if(contactNumber.getText().length()==10){		
				person.setContactNumber(Long.parseLong(contactNumber.getText()));
				close();
			}else{
				setErrorMessage("Please Enter Valid Number");
			}
		}catch(NumberFormatException npe){
			setErrorMessage("number Format Exception");
			npe.printStackTrace();
		}
			
	}
	

}
