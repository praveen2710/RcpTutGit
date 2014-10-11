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

import com.example.e4.rcp.tables.UserDetails;

public class AddPersonDialog extends TitleAreaDialog{

	private Text text1;
	private Text text2;
	private UserDetails person;
	private Button button1;
	private Combo genderSelection;
	
	public UserDetails getPerson(){
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
	public Control createContents(Composite parent) {
		Control contents = super.createContents(parent);
		setTitle("Add a new Person");
		setMessage("Please enter person details",IMessageProvider.INFORMATION);
		return contents;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.TitleAreaDialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		GridLayout layout = new GridLayout(2,false);
		parent.setLayout(layout);
		
		Label userNameLabel = new Label(parent,SWT.NONE);
		userNameLabel.setText("Enter Name");
		text1 = new Text(parent, SWT.NONE);
		
		Label ageLabel = new Label(parent,SWT.NONE);
		ageLabel.setText("Age");
		text2 = new Text(parent,SWT.NONE);
		
		Label genderLabel = new Label(parent, SWT.READ_ONLY);
		genderLabel.setText("Gender");
		genderSelection = new Combo(parent, SWT.READ_ONLY);
		genderSelection.add("Male");
		genderSelection.add("female");
		
		return parent;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		((GridLayout) parent.getLayout()).numColumns++;
		
		Button button = new Button(parent, SWT.PUSH);
		button.setText("OK");
		button.setFont(JFaceResources.getDialogFont());
		button.addSelectionListener(new SelectionAdapter() {

			/* (non-Javadoc)
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(text1.getText().length()!=0 && text2.getText().length()!=0 && genderSelection.getItem(genderSelection.getSelectionIndex()).length() !=0){
					person = new UserDetails(text1.getText(),text2.getText());
					close();
				}else{
					setErrorMessage("Please Enter All datA");
				}
			}
		});
	}

}
