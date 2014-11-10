package com.example.e4.rcp.dialogues;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.jface.action.Action;
//import org.eclipse.swt.widgets.Menu;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import com.example.e4.rcp.tables.Address;
import com.example.e4.rcp.tables.ContactDetails;
import com.example.e4.rcp.tables.OrgDetails;
import com.example.e4.rcp.tables.UserDetails;
import com.exmaple.e4.tableFunctionality.edit.UserNameEditingSupport;

public class AddOrganizationDialog extends TitleAreaDialog {
	
	private Text orgName;
	private Text orgTIN;
	private Text orgAddress;
	private Text orgCity;
	private Text zipCode;
	private Text allBusType;
	private OrgDetails selectedOrg;
	
	private ComboViewer transactionType;
	private ListViewer busType;
	private Button addPerson;
	private TableViewer personTable;
	private boolean existingRecord=false;
	
	private Collection<ContactDetails> contacts;
	private OrgDetails oneOrgDetails;
	private Address oneOrgAddress;
	
	@Inject
	private ESelectionService selectionService;
	
	/**
	 * @return the oneOrgDetails
	 */
	public OrgDetails getOneOrgDetails() {
		return oneOrgDetails;
	}

	/**
	 * @return the oneOrgAddress
	 */
	public Address getOneOrgAddress() {
		return oneOrgAddress;
	}

	/**
	 * @return the contacts
	 */
	public Collection<ContactDetails> getContacts() {
		return contacts;
	}

	public AddOrganizationDialog(Shell parentShell) {
		super(parentShell);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog#create()
	 */
	@Override
	public void create() {
		super.create();
		setTitle("Add Or Update Organization here");
		setMessage("Add all required Organization details here with persons of contacts");
		contacts =  new ArrayList<ContactDetails>();
		personTable.setInput(contacts);
//		getShell().setSize(700,500);

	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.TitleAreaDialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		GridLayout layout = new GridLayout(4,false);
		container.setLayout(layout);
		
		createOrganizationDetails(container);
		addContactDetails(container);
		addBuisnessDetails(container);
		createViewer(parent);
		
		return area;
	}

	
	private void addContactDetails(Composite container) {
		addPerson = new Button(container, SWT.PUSH);
		addPerson.setText("Add Contact");
		addPerson.addSelectionListener(new SelectionAdapter() {

			/* (non-Javadoc)
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			@Override
			public void widgetSelected(SelectionEvent e) {
				Shell shell=null;
				AddPersonDialog dialog = new AddPersonDialog(shell);
				dialog.open();
				if(dialog.getPerson() != null){
					System.out.println("Came out of dialog person");
					contacts.add(dialog.getPerson());
					personTable.refresh();
				}
			}
			
			
		});
		
	}

	private void createOrganizationDetails(Composite container) {
		Label orgNameLabel = new Label(container,SWT.NONE);
		orgNameLabel.setText("Enter Organization Name:");
		orgName = new Text(container, SWT.NONE);
		orgName.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		Label orTINLabel = new Label(container,SWT.NONE);
		orTINLabel.setText("TIN:");
		orgTIN = new Text(container, SWT.NONE);
		orgTIN.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		Label orgAddressLabel = new Label(container,SWT.NONE);
		orgAddressLabel.setText("Enter Address:");
		orgAddress = new Text(container, SWT.NONE);
		orgAddress.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		
		Label orgCityLabel = new Label(container,SWT.NONE);
		orgCityLabel.setText("Enter City:");
		orgCity = new Text(container, SWT.NONE);
		orgCity.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label orgZipLabel = new Label(container,SWT.NONE);
		orgZipLabel.setText("Enter Postal Code:");
		zipCode = new Text(container, SWT.NONE);
		zipCode.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
	}
	
	private void addBuisnessDetails(Composite container) {
		
		transactionType = new ComboViewer(container, SWT.MULTI|SWT.READ_ONLY);
		transactionType.add("Creditor");
		transactionType.add("Debtor");
		
		final ListViewer lists = new ListViewer(container, SWT.MULTI|SWT.V_SCROLL|SWT.H_SCROLL);
		lists.getControl().setLayoutData(new GridData(SWT.CENTER,SWT.CENTER, false,false));
		ObservableListContentProvider contentProvider = new ObservableListContentProvider();
		lists.setContentProvider(contentProvider);
		lists.add("a");
		lists.add("b");
		lists.add("a");
		lists.add("b");
		lists.add("a");
		lists.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection) event.getSelection();
				StringBuffer sb = new StringBuffer();
				sb.append("Total"+selection.size()+"items selected:");
				for(Iterator iterator = selection.iterator(); iterator.hasNext();){
					sb.append(iterator.next()+",");
				}
				System.out.println("Lists:"+sb);
				allBusType.setText(sb.toString());
			}
		});
	}
	
	private TableViewer createViewer(Composite parent) {
		personTable = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		createColumns(parent, personTable);
		final Table table = personTable.getTable();
		personTable.setContentProvider(new ArrayContentProvider());
//		personTable.addSelectionChangedListener(new ISelectionChangedListener() {
//
//			@Override
//			public void selectionChanged(SelectionChangedEvent event) {
//				IStructuredSelection selection = (IStructuredSelection) event
//						.getSelection();
//				// set the selection to the service
//				selectionService.setSelection(selection.size() == 1 ? selection
//						.getFirstElement() : selection.toArray());
//			}
//		});
		
		MenuManager manager = new MenuManager();
		personTable.getControl().setMenu(manager.createContextMenu(personTable.getControl()));

		manager.add(new Action("MENU ITEM TEXT") {
		    @Override
		    public void run() {
		        // get the current selection of the tableviewer
		        IStructuredSelection selection = (IStructuredSelection) personTable.getSelection();
		        System.out.println("came in menu run");
		        Shell shell = null;
				MessageDialog.openConfirm(shell, "Confirm", "Please confirm");
		        contacts.remove(selection.getFirstElement());
		        personTable.refresh();
		        // do something
		    }
		});
		
//		final Action a = new Action("") {
//		};
//		final MenuManager mgr = new MenuManager();  
//		mgr.setRemoveAllWhenShown(true);
//		
//		mgr.addMenuListener(new IMenuListener() {
//			
//			@Override
//			public void menuAboutToShow(IMenuManager manager) {
//				IStructuredSelection selection = (IStructuredSelection) personTable.getSelection();
//				if (!selection.isEmpty()) {
//					System.out.println("came in menu");
//					a.setText("Delete");
//					mgr.add(a);
//				}
//				personTable.getControl().setMenu(mgr.createContextMenu(personTable.getControl()));
//			}
//		});
		
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		personTable.getControl().setLayoutData(
				new GridData(SWT.FILL, SWT.FILL, false, true, 3, 1));
		return personTable;
	}

	private void createColumns(Composite parent, TableViewer personTable) {
			String[] titles = { "Name","Number" };
			int[] bounds = { 400,400};

			TableViewerColumn col = createTableViewerColumn(titles[0], bounds[0], 0);
			col.setLabelProvider(new ColumnLabelProvider() {

				/*
				 * (non-Javadoc)
				 * 
				 * @see
				 * org.eclipse.jface.viewers.ColumnLabelProvider#update(org.eclipse
				 * .jface.viewers.ViewerCell)
				 */
//				@Override
//				public void update(ViewerCell cell) {
//					cell.setText(((UserDetails) cell.getElement()).getUserName());
//				}

				/*
				 * (non-Javadoc)
				 * 
				 * @see
				 * org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang
				 * .Object)
				 */
				@Override
				public String getText(Object element) {
					ContactDetails cd = (ContactDetails) element;
					return cd.getPersonName();
				}

		});
//			need to modify this for this particular table.
//			col.setEditingSupport(new UserNameEditingSupport(personTable));

			col = createTableViewerColumn(titles[1], bounds[1], 1);
			col.setLabelProvider(new ColumnLabelProvider() {

				/*
				 * (non-Javadoc)
				 * 
				 * @see
				 * org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang
				 * .Object)
				 */
				@Override
				public String getText(Object element) {
					ContactDetails cd = (ContactDetails) element;
					return Long.toString(cd.getContactNumber());
				}

			});
	}
		

	private TableViewerColumn createTableViewerColumn(String title, int bound,
			final int columnNumber) {
		final TableViewerColumn contactColumn = new TableViewerColumn(personTable,
				SWT.NONE);
		final TableColumn column = contactColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(true);
//		column.addSelectionListener(getSelectionAdapter(column, columnNumber));
		return contactColumn;
	}
	
	@Override
	protected boolean isResizable(){
		return true;
	}
	
	@Override
	protected void okPressed() {
		oneOrgDetails = new OrgDetails();
		oneOrgAddress = new Address();
		
		oneOrgDetails.setOrgName(orgName.getText());
		oneOrgDetails.setTin(orgTIN.getText());
		oneOrgDetails.setOrgAddress(oneOrgAddress);
//		oneOrgDetails.setBusType(allBusType.getText());
		oneOrgDetails.setTransactionType(transactionType.getSelection().toString());
		oneOrgDetails.setContacts(contacts);
		
		oneOrgAddress.setAddress(orgAddress.getText());
		oneOrgAddress.setCity(orgCity.getText());
		oneOrgAddress.setPostalCode(Integer.parseInt(zipCode.getText()));
		//dummy for now need to add a combobox for states
		oneOrgAddress.setCity("AP");
		
		super.okPressed();
	}
	
	public void selectedFromTable(OrgDetails selected){
		this.selectedOrg = selected;
		existingRecord = true;
		System.out.println("In dialogue"+selectedOrg.getOrgName());
		orgName.setText(selectedOrg.getOrgName()); 
		contacts = selectedOrg.getContacts();
		personTable.setInput(contacts);
	}
	
//	private void bindValues() {
//		DataBindingContext ctx = new DataBindingContext();
//		IObservableValue widgetValue = WidgetProperties.text(SWT.Modify)
//				.observe(orgName);
//		IObservableValue modelValue = BeanProperties.value(OrgDetails.class,"orgName").observe(selectedOrg);
//		ctx.bindValue(widgetValue, modelValue);
//	}
}
