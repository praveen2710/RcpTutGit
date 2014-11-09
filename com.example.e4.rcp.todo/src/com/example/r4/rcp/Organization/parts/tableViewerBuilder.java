package com.example.r4.rcp.Organization.parts;

import java.util.List;

import javax.annotation.PostConstruct;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import com.example.StoreInDatabase;
import com.example.e4.rcp.tables.OrgDetails;

import de.ralfebert.rcputils.random.RandomData;
import de.ralfebert.rcputils.tables.ColumnBuilder;
import de.ralfebert.rcputils.tables.TableViewerBuilder;

public class tableViewerBuilder {
	private TableViewer tableViewer;
	private List<OrgDetails> retrievedData;
	private StoreInDatabase sid;

	@PostConstruct
	public void createComposite(Composite parent) {
		
		GridLayout layout = new GridLayout(3, false);
		parent.setLayout(layout);
		
		final TableViewerBuilder t = new TableViewerBuilder(parent);
		sid = new StoreInDatabase();
		retrievedData = (List<OrgDetails>)sid.retrieveFromDatabase("mahaveer");
		System.out.println(retrievedData.toString());
		t.setInput(retrievedData);
		tableViewer = t.getTableViewer();
		
		
		final Button searchButton = new Button(parent, SWT.PUSH);
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
				

				// Set sorter for the table
				// comparator = new MyViewComparator();
				// viewer.setComparator(comparator);
			}
		});
		
		
		
		ColumnBuilder name = t.createColumn("Organization");
		name.bindToProperty("orgName");
		name.setPercentWidth(60);
		name.useAsDefaultSortColumn();
		name.makeEditable();
		name.build();
		
		ColumnBuilder neighborCity = t.createColumn("Contacts");
		neighborCity.bindToProperty("contacts.contactNumber");
		neighborCity.setPixelWidth(100);
//		ComboBoxViewerCellEditor cityComboEditor = new ComboBoxViewerCellEditor(t.getTable(), SWT.READ_ONLY);
//		cityComboEditor.setContenProvider(new ArrayContentProvider());
//		cityComboEditor.setLabelProvider(new LabelProvider());
////		cityComboEditor.setInput(contacts);
//		neighborCity.makeEditable(cityComboEditor);
		neighborCity.build();
	}
	
	
	public void RetirevedOrganization(List<OrgDetails> listOfOrganizations){
		
	}
}
