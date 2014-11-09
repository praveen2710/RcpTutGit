package com.example.r4.rcp.Organization.parts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.eclipse.nebula.widgets.nattable.NatTable;
import org.eclipse.nebula.widgets.nattable.data.IColumnPropertyAccessor;
import org.eclipse.nebula.widgets.nattable.data.IDataProvider;
import org.eclipse.nebula.widgets.nattable.data.ListDataProvider;
import org.eclipse.nebula.widgets.nattable.grid.GridRegion;
import org.eclipse.nebula.widgets.nattable.grid.data.DefaultColumnHeaderDataProvider;
import org.eclipse.nebula.widgets.nattable.grid.layer.ColumnHeaderLayer;
import org.eclipse.nebula.widgets.nattable.layer.CompositeLayer;
import org.eclipse.nebula.widgets.nattable.layer.DataLayer;
import org.eclipse.nebula.widgets.nattable.layer.ILayer;
import org.eclipse.nebula.widgets.nattable.selection.SelectionLayer;
import org.eclipse.nebula.widgets.nattable.viewport.ViewportLayer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.example.StoreInDatabase;
import com.example.e4.rcp.tables.OrgDetails;


public class SAmplePart {
	
	private Text searchText;
	private Button searchButton;
	private List<OrgDetails> retrievedData;
	

	    private String[] propertyNames;
	  
	    private Map<String, String> propertyToLabels;

	    @PostConstruct
	    public  void createComposite(final Composite parent) {
	    	
	    	GridLayout layout = new GridLayout(3, false);
			parent.setLayout(layout);
			
			
			List<OrgDetails> dummyList= new ArrayList<OrgDetails>();
			OrgDetails dummyOrg1 = new OrgDetails();
			dummyOrg1.setOrgName("Dummy1");
			
			OrgDetails dummyOrg2 = new OrgDetails();
			dummyOrg2.setOrgName("Dummy2");
			
			dummyList.add(dummyOrg1);
			dummyList.add(dummyOrg2);
			
	        
	        
	  
	        
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
					
				}
			});
			
			
			IColumnPropertyAccessor<OrgDetails> columnPropertyAccessor = new OrganizationDetailsPropertyAccessor();
			IDataProvider bodyDataProvider = new ListDataProvider<OrgDetails>(retrievedData, columnPropertyAccessor);
			final DataLayer bodyDataLayer = new DataLayer(bodyDataProvider);
	        final SelectionLayer selectionLayer = new SelectionLayer(bodyDataLayer);
	        ViewportLayer viewportLayer = new ViewportLayer(selectionLayer);
	        
	        ILayer columnHeaderLayer = new ColumnHeaderLayer(
	  					new DataLayer(createColumnHeaderDataProvider()),
	  					viewportLayer, 
	  					selectionLayer);
	        
	        CompositeLayer compositeLayer = new CompositeLayer(1, 2);
			compositeLayer.setChildLayer(GridRegion.COLUMN_HEADER, columnHeaderLayer, 0, 0);
			compositeLayer.setChildLayer(GridRegion.BODY, viewportLayer, 0, 1);
			NatTable natTable = new NatTable(parent, compositeLayer);

	        natTable.setLayoutData(
					new GridData(SWT.FILL, SWT.FILL, false, true, 3, 1));
			
			
			
	    }

	    private IDataProvider createColumnHeaderDataProvider() {
	       
	        propertyNames = new String[] {"orgName","blah","blah","blah","blah"};
	        
	        propertyToLabels = new HashMap<String, String>();
	        propertyToLabels.put(DataModelConstants.ORGNAME_PROPERTYNAME,"Party Name");
	        
	        return new DefaultColumnHeaderDataProvider(propertyNames,propertyToLabels);
	    }

	    
	    class OrganizationDetailsPropertyAccessor implements IColumnPropertyAccessor<OrgDetails>{
	    	
			@Override
			public Object getDataValue(OrgDetails rowObject, int columnIndex) {
				switch (columnIndex){
					case DataModelConstants.ORGNAME_COLUMN_POSITION:
						return rowObject.getOrgName();
				}
				return "";
			}

			@Override
			public void setDataValue(OrgDetails rowObject, int columnIndex,
					Object newValue) {
				switch (columnIndex) {
				case DataModelConstants.ORGNAME_COLUMN_POSITION:
					rowObject.setOrgName((String)newValue);
					break;
				}
				
			}

			@Override
			public int getColumnCount() {
				return DataModelConstants.ORGANIZATION_NUMBER_OF_COLUMNS;
			}

			@Override
			public String getColumnProperty(int columnIndex) {
				return DataModelConstants.ORAGANIZATION_PROPERTY_NAMES[columnIndex];
			}

			@Override
			public int getColumnIndex(String propertyName) {
				return Arrays.asList(DataModelConstants.ORAGANIZATION_PROPERTY_NAMES).indexOf(propertyName);
			}
	    	
	    }
}

