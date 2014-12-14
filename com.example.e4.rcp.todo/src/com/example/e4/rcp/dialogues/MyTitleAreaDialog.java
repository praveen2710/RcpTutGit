//package com.example.e4.rcp.dialogues;
//
//import org.eclipse.core.databinding.DataBindingContext;
//import org.eclipse.core.databinding.beans.BeanProperties;
//import org.eclipse.core.databinding.observable.value.IObservableValue;
//import org.eclipse.jface.databinding.dialog.TitleAreaDialogSupport;
//import org.eclipse.jface.databinding.swt.WidgetProperties;
//import org.eclipse.jface.dialogs.TitleAreaDialog;
//import org.eclipse.jface.viewers.ArrayContentProvider;
//import org.eclipse.jface.viewers.ColumnLabelProvider;
//import org.eclipse.jface.viewers.TableViewer;
//import org.eclipse.jface.viewers.TableViewerColumn;
//import org.eclipse.jface.viewers.ViewerCell;
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.events.SelectionAdapter;
//import org.eclipse.swt.events.SelectionEvent;
//import org.eclipse.swt.layout.GridData;
//import org.eclipse.swt.layout.GridLayout;
//import org.eclipse.swt.widgets.Button;
//import org.eclipse.swt.widgets.Combo;
//import org.eclipse.swt.widgets.Composite;
//import org.eclipse.swt.widgets.Control;
//import org.eclipse.swt.widgets.Label;
//import org.eclipse.swt.widgets.Shell;
//import org.eclipse.swt.widgets.Table;
//import org.eclipse.swt.widgets.TableColumn;
//import org.eclipse.swt.widgets.Text;
//
//import com.example.StoreInDatabase;
//import com.example.e4.rcp.tables.OrgDetails;
//import com.example.e4.rcp.tables.UserDetails;
//import com.exmaple.e4.tableFunctionality.MyViewComparator;
//import com.exmaple.e4.tableFunctionality.edit.UserNameEditingSupport;
///**
// * SAmple dialog for proof of concept will implement this
// * in detials view for now
// * @author PB033954
// *
// */
//public class MyTitleAreaDialog extends TitleAreaDialog {
//
//	private Text txtOrgName;
//	private Text txtUserName;
//	private Text txtAddress;
//	private Text txtContact;
//	private Text txtCity;
//	private Combo comBusType;
//	
//	private StoreInDatabase sd;
//	private UserDetails user;
//	private TableViewer viewer;
//	private OrgDetails orgDetails;
//	private String orgName;
//	
//	public MyTitleAreaDialog(Shell parentShell) {
//		super(parentShell);
//		// TODO Auto-generated constructor stub
//	}
//	
//	/* (non-Javadoc)
//	 * @see org.eclipse.jface.dialogs.Dialog#create()
//	 */
//	@Override
//	public void create() {
//		super.create();
//		setTitle("Please Add New Organization here");
//		setMessage("Add Organization Details here");
//	}
//	
//	/* (non-Javadoc)
//	 * @see org.eclipse.jface.dialogs.TitleAreaDialog#createDialogArea(org.eclipse.swt.widgets.Composite)
//	 */
//	@Override
//	protected Control createDialogArea(Composite parent) {
//		Composite area = (Composite) super.createDialogArea(parent);
//		Composite container = new Composite(area, SWT.NONE);
//		container.setLayoutData(new GridData(GridData.FILL_BOTH));
//		GridLayout layout = new GridLayout(4,false);
//		container.setLayout(layout);
//		
//		createFirstName(container);
//		createAddressDetails(container);
//		createPersonDetails(container);
//		createBuisnessDetails(container);
//		sd = new StoreInDatabase();
//		orgDetails = new OrgDetails();
//		user = new UserDetails();
//		createViewer(parent);
//		
//		bindValues();
//		return area;
//	}
//
//	private void createBuisnessDetails(Composite container) {
//		Label busType = new Label(container,SWT.NONE);
//		busType.setText("Buisnees Type");
//		comBusType = new Combo(container, SWT.NONE);
//		comBusType.add("Borrower",0);
//		comBusType.add("Lender",1);
//	}
//
//	private void createFirstName(Composite container) {
//		Label orgName = new Label(container,SWT.NONE);
//		orgName.setText("Organization Name");
//		txtOrgName = new Text(container, SWT.NONE);
//		txtOrgName.setLayoutData(new GridData(SWT.FILL,SWT.CENTER, true, false,2,1));	
//	}
//	
//	private void createPersonDetails(Composite container) {
//		Label userName = new Label(container,SWT.NONE);
//		userName.setText("Persons Name");
//		txtUserName = new Text(container,SWT.NONE);
//		txtUserName.setLayoutData(new GridData(SWT.CENTER,SWT.CENTER,false,false,1,1));
//		
//		Label contactInfo = new Label(container,SWT.NONE);
//		contactInfo.setText("Phone Number");
//		txtContact = new Text(container,SWT.NONE);
//		txtContact.setLayoutData(new GridData(SWT.FILL,SWT.CENTER,false,false));
//		
//		final Button searchButton = new Button(container, SWT.PUSH);
//		searchButton.setText("Add Person");
//		searchButton.addSelectionListener(new SelectionAdapter() {
//
//			/* (non-Javadoc)
//			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
//			 */
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				sd.writeToDatabase(user);
//				viewer.setContentProvider(new ArrayContentProvider());
//				System.out.println("In dialog add user button"+user.getUserName());
//				viewer.setInput(sd.listAllUsers());
////				 getSite().setSelectionProvider(viewer);	
//			}
//		});	
//	}
//	
//	private void createAddressDetails(Composite container) {
//		Label orgAddress = new Label(container,SWT.NONE);
//		orgAddress.setText("Address");
//		txtAddress = new Text(container,SWT.NONE);
//		txtAddress.setLayoutData(new GridData(SWT.CENTER,SWT.CENTER,false,false,2,1));
//		
//		Label orgCity = new Label(container,SWT.NONE);
//		orgCity.setText("City");
//		txtCity = new Text(container, SWT.NONE);
//		txtCity.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true,false));
//	}
//		
//	/**
//	 * this method binds text box to database bean class
//	 */
//	private void bindValues() {
//		DataBindingContext ctx = new DataBindingContext();
//		IObservableValue widgetValue = WidgetProperties.text(SWT.Modify).observe(txtOrgName);
//		IObservableValue modelValue = BeanProperties.value(OrgDetails.class,"orgName").observe(orgDetails);
//		ctx.bindValue(widgetValue, modelValue);
//		
//		widgetValue = WidgetProperties.text(SWT.Modify).observe(txtAddress); // the swt to be monitored
//		modelValue = BeanProperties.value(OrgDetails.class,"orgAddress").observe(orgDetails);
//		ctx.bindValue(widgetValue, modelValue);
//		
//		widgetValue = WidgetProperties.text(SWT.Modify).observe(txtCity); // the swt to be monitored
//		modelValue = BeanProperties.value(OrgDetails.class,"orgCity").observe(orgDetails);
//		ctx.bindValue(widgetValue, modelValue);
//		
//		widgetValue = WidgetProperties.selection().observe(comBusType); // the swt to be monitored
//		modelValue = BeanProperties.value(OrgDetails.class,"orgType").observe(orgDetails);
//		ctx.bindValue(widgetValue, modelValue);
//		
//		widgetValue = WidgetProperties.text(SWT.Modify).observe(txtUserName); // the swt to be monitored
//		modelValue = BeanProperties.value(UserDetails.class,"userName").observe(user);
//		ctx.bindValue(widgetValue, modelValue);
//		
//		widgetValue = WidgetProperties.text(SWT.Modify).observe(txtContact); // the swt to be monitored
//		modelValue = BeanProperties.value(UserDetails.class,"number").observe(user);
//		ctx.bindValue(widgetValue, modelValue);
//	}
//		
//	public String getFirstName() {
//	    return orgName;
//	 }
//	
//	/**
//	 * THis method helps to customize the table such as size , multi select and other options
//	 * @param parent
//	 * @return
//	 */
//	private TableViewer createViewer(Composite parent) {
//		viewer = new TableViewer(parent,SWT.MULTI|SWT.H_SCROLL|SWT.V_SCROLL|SWT.FULL_SELECTION|SWT.BORDER);
//		createColumns(parent,viewer);
//		final Table table = viewer.getTable();
//		table.setHeaderVisible(true);
//		table.setLinesVisible(true);
//		viewer.getControl().setLayoutData(new GridData(SWT.FILL,SWT.FILL, false,true,3,1));
//		return viewer;
//	}
//	
//	/**
//	 * THis method declares the columns name and fill data in the columns
//	 * TODO : when add person is clicked data from text box should be added to table and the 
//	 * text box gets cleared
//	 * @param parent
//	 * @param viewer
//	 */
//	private void createColumns(Composite parent, TableViewer viewer) {
//		String[] titles={"Name","Number"};
//		int[] bounds = {100,100};
//		
//		TableViewerColumn col = createTableViewerColumn(titles[0],bounds[0],0);
//		col.setLabelProvider(new ColumnLabelProvider(){
//
//			/* (non-Javadoc)
//			 * @see org.eclipse.jface.viewers.ColumnLabelProvider#update(org.eclipse.jface.viewers.ViewerCell)
//			 */
//			@Override
//			public void update(ViewerCell cell) {
//				cell.setText(((UserDetails) cell.getElement()).getUserName());
//			}
//
//			/* (non-Javadoc)
//			 * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
//			 */
//			@Override
//			public String getText(Object element) {
////				UserDetails ud = (UserDetails) element;
//				return user.getUserName();
//			}
//			
//		});
//		col.setEditingSupport(new UserNameEditingSupport(viewer));
//		
//		 col = createTableViewerColumn(titles[1],bounds[1],1);
//		col.setLabelProvider(new ColumnLabelProvider(){
//
//			/* (non-Javadoc)
//			 * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
//			 */
//			@Override
//			public String getText(Object element) {
//				UserDetails ud = (UserDetails) element;
//				return ud.getNumber();
//			}
//			
//		});	
//	}
//	
//	private TableViewerColumn createTableViewerColumn(String title, int bound,final int columnNumber) {
//		final TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.NONE);
//		final TableColumn column = viewerColumn.getColumn();
//		column.setText(title);
//		column.setWidth(bound);
//		column.setResizable(true);
//		column.setMoveable(true);
////		column.addSelectionListener(getSelectionAdapter(column, columnNumber));
//		return viewerColumn;
//	}
//	
//	/**
//	 * This is the point when data is added to the database
//	 * TODO : Option to Extract Database and make modification
//	 * TODO : Add a update button if possible
//	 */
//	@Override
//	protected void okPressed(){
//		sd.writeToDatabase(orgDetails);
//		super.okPressed();
//	}
//}
