package com.example.r4.rcp.Organization.parts;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import com.example.StoreInDatabase;
import com.example.e4.rcp.dialogues.AddOrganizationDialog;
import com.example.e4.rcp.tables.ContactDetails;
import com.example.e4.rcp.tables.DatabaseAccess;
import com.example.e4.rcp.tables.OrgDetails;
import com.exmaple.e4.tableFunctionality.DropDownInTable;
//import com.exmaple.e4.tableFunctionality.edit.ComBoxDisplaySupport;

public class SearchOrganization {
	private Text searchText;
	private TableViewer orgTable;
	private Button searchButton;
	private List<OrgDetails> retrievedData;
	
	@Inject
	private ESelectionService selectionService;
	
	@PostConstruct
	public void createComposite(Composite parent){
		GridLayout layout = new GridLayout(3, false);
		parent.setLayout(layout);
		createViewer(parent);
		
		
		Label searchLabel = new Label(parent, SWT.NONE);
		searchLabel.setText("Search:");
		
		final Text searchText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		searchText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL
				| GridData.HORIZONTAL_ALIGN_FILL));
		
		searchButton = new Button(parent, SWT.PUSH);
		searchButton.setText("Search");
		searchButton.addSelectionListener(new SelectionAdapter() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse
			 * .swt.events.SelectionEvent)
			 */
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println(searchText.getText());
				StoreInDatabase sd = new StoreInDatabase();
				retrievedData = (List<OrgDetails>)sd.retrieveFromDatabase(searchText.getText());
				System.out.println("In widget"+retrievedData);
				orgTable.setInput(retrievedData);
				for(OrgDetails a : retrievedData){
					System.out.println(a.getOrgAddress().getPostalCode());
				}
			}
		});

	}
	

	private TableViewer createViewer(Composite parent) {
		orgTable = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		
		
		createColumns(parent, orgTable);
		
		final Table table = orgTable.getTable();
		orgTable.setContentProvider(new ArrayContentProvider());
		
		orgTable.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection) event
						.getSelection();
				OrgDetails org = (OrgDetails) selection.getFirstElement();
				if(org != null){
					System.out.println("Selected : "+ org.getOrgName());
				}
				else{
					System.out.println("Selection cleared!");
				}
			}
//				// set the selection to the service
//				selectionService.setSelection(selection.size() == 1 ? selection
//						.getFirstElement() : selection.toArray());
//			}
		});
		
		orgTable.addDoubleClickListener(new IDoubleClickListener() {
			
			@Override
			public void doubleClick(DoubleClickEvent event) {
				System.out.println("Double CLikc works");
				IStructuredSelection sel = (IStructuredSelection) event.getSelection();
				OrgDetails org = (OrgDetails) sel.getFirstElement();
				if(org != null){
					Shell shell = null;
					AddOrganizationDialog dialog = new AddOrganizationDialog(shell);
					dialog.create();
					dialog.selectedFromTable(org);
					if(dialog.open() == Window.OK){
						List<DatabaseAccess> affectedTables = new ArrayList<DatabaseAccess>();
						StoreInDatabase sd = new StoreInDatabase();
						affectedTables.add(dialog.getOneOrgAddress());
						affectedTables.add(dialog.getOneOrgDetails());
						for(DatabaseAccess eachContact : dialog.getContacts()){
							affectedTables.add(eachContact);
						}
						sd.writeToDatabase(affectedTables);
						System.out.println();
					}
					System.out.println("Double-click on : "+ org.getOrgName()+ " " + org.getTin());
				}
			}
		});
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		orgTable.getControl().setLayoutData(
				new GridData(SWT.FILL, SWT.FILL, false, true, 3, 1));
		return orgTable;
	}
	
	private void createColumns(Composite parent, TableViewer viewer) {
		String[] titles = { "name", "gender", "contacts"};
		int[] bounds = { 100, 100, 100};
	
		TableViewerColumn col = createTableViewerColumn(titles[0], bounds[0], 0);
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
				OrgDetails od = (OrgDetails) element;
				return od.getOrgName();
			}

		});
		
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
				OrgDetails od = (OrgDetails) element;
				return od.getTin();
			}

		});
	
		TableViewerColumn valcol = createTableViewerColumn(titles[2], bounds[2], 2);
		valcol.setLabelProvider(new ColumnLabelProvider() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang
			 * .Object)
			 */
			@Override
			public String getText(Object element) {
				System.out.println("gettext"+element.toString());
				OrgDetails od = (OrgDetails) element;
				List<ContactDetails> contactsList = new ArrayList<ContactDetails>(od.getContacts());
				System.out.println(Long.toString(contactsList.get(0).getContactNumber()));
				return Long.toString(contactsList.get(0).getContactNumber());
			}

		});
//		System.out.println("jjjj"+valcol.getViewer().);
		DropDownInTable dot = new DropDownInTable(valcol.getViewer());
		valcol.setEditingSupport(dot);
		

	}

	private TableViewerColumn createTableViewerColumn(String title, int bound,
			final int columnNumber) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(orgTable,
				SWT.NONE);
	
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(true);
		return viewerColumn;
	}
	
	public TableViewer getViewer() {
		return orgTable;
	}
	
	private class TableLabelProvider extends LabelProvider implements ITableLabelProvider{

		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getColumnText(Object element, int columnIndex) {
			OrgDetails eachRetrievedRow = (OrgDetails) element;
			 switch (columnIndex) {
		      case 0:
		        return eachRetrievedRow.getTin();
		      case 1:
		        return eachRetrievedRow.getTransactionType();
		      default:
		        return "";
		      }
		}
		
	}
	
}
