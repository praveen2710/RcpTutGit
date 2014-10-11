package com.example.e4.rcp.views;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import com.example.StoreInDatabase;
import com.exmaple.e4.tableFunctionality.PersonFilter;

public class SearchView extends ViewPart {

	private TableViewer viewer;
	private PersonFilter filter;
	private Text searchText; 
	
	@Override
	public void createPartControl(Composite parent) {
	  GridLayout layout = new GridLayout(2, false);
	    parent.setLayout(layout);
	    Label searchLabel = new Label(parent, SWT.NONE);
	    searchLabel.setText("Search: ");
	    searchText = new Text(parent, SWT.BORDER | SWT.SEARCH);
	    searchText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL
	        | GridData.HORIZONTAL_ALIGN_FILL));
	    createViewer(parent);
	    // Set the sorter for the table
//	    comparator = new MyViewerComparator();
//	    viewer.setComparator(comparator);

	    // New to support the search
	    searchText.addKeyListener(new KeyAdapter() {
	      public void keyReleased(KeyEvent ke) {
	        filter.searchStringText(searchText.getText());
	        viewer.refresh();
	      }

	    });
	    filter = new PersonFilter();
	    viewer.addFilter(filter);
		
	}

	private void createViewer(Composite parent) {
	    viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
	            | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
        createColumns(parent, viewer);
        final Table table = viewer.getTable();
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        viewer.setContentProvider(new ArrayContentProvider());
        // Get the content for the viewer, setInput will call getElements in the
        // contentProvider
        StoreInDatabase sid = new StoreInDatabase();
        viewer.setInput(sid.listAllUsers());
        // make the selection available to other views
        getSite().setSelectionProvider(viewer);

        // Layout the viewer
        GridData gridData = new GridData();
        gridData.verticalAlignment = GridData.FILL;
        gridData.horizontalSpan = 2;
        gridData.grabExcessHorizontalSpace = true;
        gridData.grabExcessVerticalSpace = true;
        gridData.horizontalAlignment = GridData.FILL;
        viewer.getControl().setLayoutData(gridData);

		
	}

	private void createColumns(Composite parent, TableViewer viewer) {
		// TODO Auto-generated method stub
		
	}
	
	public TableViewer getViewer() {
	    return viewer;
	  }

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}

}
