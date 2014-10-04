package com.example.e4.rcp.todo.parts;



import javax.annotation.PostConstruct;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import com.example.StoreInDatabase;
import com.example.e4.helperClasses.ModelProvider;
import com.example.e4.rcp.tables.UserDetails;

public class SearchBarViewPart {
	
	private TableViewer viewer;
	
	@PostConstruct
	public void createComposite(final Composite parent){
		GridLayout layout = new GridLayout(3,false);
		parent.setLayout(layout);
		
		Label searchLabel = new Label(parent,SWT.NONE);
		searchLabel.setText("Search:");
		
		final Text searchText = new Text(parent,SWT.BORDER|SWT.SEARCH);
		searchText.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		final Button searchButton = new Button(parent, SWT.PUSH);
		searchButton.setText("Search");
		searchButton.addSelectionListener(new SelectionAdapter() {

			/* (non-Javadoc)
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			@Override
			public void widgetSelected(SelectionEvent e) {
				createViewer(parent);
			}
			
		});
		
		
		
	}
	private void createViewer(Composite parent) {
		viewer = new TableViewer(parent,SWT.MULTI|SWT.H_SCROLL|SWT.V_SCROLL|SWT.FULL_SELECTION|SWT.BORDER);
		createColumns(parent,viewer);
		final Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		viewer.setContentProvider(new ArrayContentProvider());
		StoreInDatabase sid = new StoreInDatabase();
		viewer.setInput(sid.listAllUsers());
		
		viewer.getControl().setLayoutData(new GridData(SWT.FILL,SWT.FILL, false,true,2,1));
	}
	
	private void createColumns(Composite parent, TableViewer viewer2) {
		String[] titles={"name","gender","age"};
		int[] bounds = {100,100,100,100};
		
		TableViewerColumn col = createTableViewerColumn(titles[0],bounds[0],0);
		col.setLabelProvider(new ColumnLabelProvider(){

			/* (non-Javadoc)
			 * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
			 */
			@Override
			public String getText(Object element) {
				UserDetails ud = (UserDetails) element;
				return ud.getUserName();
			}
			
		});
		
		 col = createTableViewerColumn(titles[1],bounds[1],1);
		col.setLabelProvider(new ColumnLabelProvider(){

			/* (non-Javadoc)
			 * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
			 */
			@Override
			public String getText(Object element) {
				UserDetails ud = (UserDetails) element;
				return ud.getGender();
			}
			
		});
		
		 col = createTableViewerColumn(titles[2],bounds[2],2);
		col.setLabelProvider(new ColumnLabelProvider(){

			/* (non-Javadoc)
			 * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
			 */
			@Override
			public String getText(Object element) {
				UserDetails ud = (UserDetails) element;
				return Integer.toString(ud.getAge());
			}
			
		});
		
	}
	private TableViewerColumn createTableViewerColumn(String title, int bound,final int colum) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.NONE);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(true);
		return viewerColumn;
	}
	
	public TableViewer getViewer() {
		return viewer;
	}
	

	public void setFocus(){
		viewer.getControl().setFocus();
	}
}
