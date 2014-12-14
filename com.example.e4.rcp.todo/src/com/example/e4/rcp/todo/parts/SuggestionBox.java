package com.example.e4.rcp.todo.parts;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.fieldassist.ComboContentAdapter;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.SimpleContentProposalProvider;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.jface.snippets.viewers.Model;
import org.eclipse.jface.snippets.viewers.MySelectionAdapter;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.jface.viewers.ITableFontProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.nebula.jface.tablecomboviewer.TableComboViewer;
import org.eclipse.nebula.widgets.cdatetime.CDT;
import org.eclipse.nebula.widgets.cdatetime.CDateTime;
import org.eclipse.nebula.widgets.tablecombo.TableCombo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.example.StoreInDatabase;
import com.example.e4.rcp.tables.DatabaseAccess;
import com.example.e4.rcp.tables.OrgDetails;
import com.example.e4.rcp.tables.ShipmentDetails;

public class SuggestionBox {
	
	private Text recieptText;
	private static final String LCL = "abcdefghijklmnopqrstuvwxyz";
    private static final String UCL = LCL.toUpperCase();
    private static final String NUMS = "0123456789";
    private static String[] items= null;
	private static Color darkRed;
	private static Color darkBlue;
	private static Color darkGreen;
	private static Font boldFont;
	private static List modelList;
    private static OrgDetails orgId;
	
	private ShipmentDetails eachShipment;
    @Inject 
    Shell shell; 
    
	@Inject
	public SuggestionBox(Composite parent){
		System.out.println("Woh! this is To Suggestion");
		System.out.println(parent.getLayout().getClass());
		System.out.println(org.hibernate.Version.getVersionString());
		
		// create colors
		darkRed = Display.getCurrent().getSystemColor(SWT.COLOR_DARK_RED);
		darkBlue = Display.getCurrent().getSystemColor(SWT.COLOR_DARK_BLUE);
		darkGreen = Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN);
		
		GridLayout layout = new GridLayout(2,false);
		layout.marginRight = 5;
		parent.setLayout(layout);
		
		Label firstLabel = new Label(parent,SWT.NONE);
		firstLabel.setText("Enter Name");
		
		StoreInDatabase sd = new StoreInDatabase();
		System.out.println(sd.retrieveAllOrgNames().size());
		items = sd.retrieveAllOrgNames().toArray(new String[] {});
		recieptText = new Text(parent,SWT.SEARCH);
		recieptText.setLayoutData(new GridData(SWT.FILL, SWT.FILL,true,false));
        enableContentProposal(recieptText);
        
        Button button = new Button(parent, SWT.PUSH);
        button.addSelectionListener(new MySelectionAdapter(shell)); 
        
		Label label = new Label(parent, SWT.NONE);
		label.setText("Three Columns (With Colors && Fonts && Header):");
		
		// create TableCombo
		TableComboViewer tcv = new TableComboViewer(parent, SWT.BORDER | SWT.READ_ONLY);
		tcv.getTableCombo().setLayoutData(new GridData(125, SWT.DEFAULT));
		tcv.getTableCombo().setShowTableHeader(true);
		
		// set the content provider
		tcv.setContentProvider(ArrayContentProvider.getInstance());
		
		// set the label provider
		tcv.setLabelProvider(new ThreeLabelProvider());
		
		// tell the TableCombo that I want 3 columns autosized with the following column headers.
		tcv.getTableCombo().defineColumns(new String[] { "Id", "Description", "Computed"});
		
		// set which column will be used for the selected item.
		tcv.getTableCombo().setDisplayColumnIndex(1);
		
		//	enableContentProposal(tc);
		tcv.getTableCombo().setEditable(true);
		
		// load the data
		tcv.setInput(sd.retrieveAllOrgAllDetails());
		
		// add listener
		tcv.addSelectionChangedListener(new ItemSelected("Sample5"));
        
		
		Label shipLabel = new Label(parent, SWT.NONE);
		shipLabel.setText("Order Date:");
		
		final CDateTime oDate = new CDateTime(parent, CDT.BORDER | CDT.DROP_DOWN);
		oDate.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true));
		
		Label orderLabel = new Label(parent, SWT.NONE);
		orderLabel.setText("Ship Date:");
		
		final CDateTime sDate = new CDateTime(parent, CDT.BORDER | CDT.DROP_DOWN);
		sDate.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true));
		
		Label recieptLabel = new Label(parent, SWT.NONE);
		recieptLabel.setText("Reciept");
		
		recieptText = new Text(parent,SWT.NONE);
		recieptText.setLayoutData(new GridData(SWT.FILL, SWT.FILL,true,false));
		
		Button saveButton = new Button(parent, SWT.PUSH);
		saveButton.setText("Save");
		eachShipment = new ShipmentDetails();
        saveButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				//add check for ship date be only after order date
				System.out.println(oDate.getSelection());				
				eachShipment.setExpectedShipDate(oDate.getSelection());;
			    eachShipment.setExpectedRecievedDate(sDate.getSelection());
			    eachShipment.setRecieptNumber(recieptText.getText());
				eachShipment.setOrgId(orgId);
				//need to refactor to remove duplication later
				//possible try block
				List<DatabaseAccess> affectedTables = new ArrayList<DatabaseAccess>();
				StoreInDatabase sd = new StoreInDatabase();
				affectedTables.add(eachShipment);
				sd.writeToDatabase(affectedTables);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private static class ThreeLabelProvider extends LabelProvider implements ITableLabelProvider, ITableColorProvider, ITableFontProvider {
		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object, int)
		 */
		public Image getColumnImage (Object element, int columnIndex) {
			return null;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
		 */
		public String getColumnText (Object element, int columnIndex) {
			
			OrgDetails model = (OrgDetails)element;
			
			switch (columnIndex) {
			case 0: return String.valueOf(model.getId());
			case 1: return model.getOrgName();
			case 2: return model.getTin();
			}
			return "";
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ITableColorProvider#getBackground(java.lang.Object, int)
		 */
		public Color getBackground(Object arg0, int arg1) {
			// TODO Auto-generated method stub
			return null;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ITableColorProvider#getForeground(java.lang.Object, int)
		 */
		public Color getForeground(Object element, int columnIndex) {
			OrgDetails item = (OrgDetails)element;
			
			if (item.getId() == 1 || item.getId() == 15) {
				return darkRed;
			}
			else if (item.getId() == 5 || item.getId() == 20) {
				return darkBlue;
			}
			else if (item.getId() == 10) {
				return darkGreen;
			}
			else {
				return null;
			}
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ITableFontProvider#getFont(java.lang.Object, int)
		 */
		public Font getFont(Object element, int index) {
			OrgDetails item = (OrgDetails)element;
			
			if (item.getId() == 1 || item.getId() == 15) {
				return boldFont;
			}
			else if (item.getId() == 5 || item.getId() == 20) {
				return boldFont;
			}
			else if (item.getId() == 10) {
				return boldFont;
			}
			else {
				return null;
			}			
		}		
	}
	
	/**
	 * load a list of rows with 3 columns
	 * @return
	 */
	
		private static List loadThreeColumnDataset(Table table) {
		List rowList = new ArrayList();
		StoreInDatabase sd = new StoreInDatabase();
		modelList = sd.retrieveAllOrgAllDetails();
		int total = (modelList == null ? 0 : modelList.size());
		
		for (int index=0; index < total; index++) {
			TableItem ti = new TableItem(table, SWT.NONE);
			OrgDetails model = (OrgDetails)modelList.get(index);
			ti.setText(new String[] { String.valueOf(model.getId()), model.getOrgName(), 
				String.valueOf(model.getTin())});
			rowList.add(ti);			
		}
		return rowList;
	}
	
	/**
	 * load a list of rows with 3 columns that includes colors and fonts.
	 * @return
	 */
	private static List loadThreeColumnDatasetWithColorsAndFonts(Table table) {
		List list = loadThreeColumnDataset(table);
		int total = (list == null ? 0 : list.size());
		
		for (int index=0; index < total; index++) {
			TableItem ti = ((TableItem)(list.get(index)));
			
			if (index == 0 || index == 14) {
				ti.setForeground(darkRed);
//				ti.setFont(boldFont);
			}
			else if (index == 4 || index == 19) {
				ti.setForeground(darkBlue);
//				ti.setFont(boldFont);
			}
			else if (index == 9) {
				ti.setForeground(darkGreen);
//				ti.setFont(boldFont);
			}
		}
		
		return list;
	}
	
	private static class ItemSelected implements ISelectionChangedListener {
		
		private String text;
		
		public ItemSelected(String text) {
			this.text = text;
		}

		public void selectionChanged(SelectionChangedEvent event) {
			OrgDetails model = (OrgDetails)((IStructuredSelection)event.getSelection()).getFirstElement();
			orgId = model;
		}
	}
	
	static  void enableContentProposal(Control control)
    {
         
        SimpleContentProposalProvider proposalProvider = null;
        ContentProposalAdapter proposalAdapter = null;
        if (control instanceof Combo)
        {
            Combo combo = (Combo) control;
            proposalProvider = new SimpleContentProposalProvider(combo.getItems());
            proposalAdapter = new ContentProposalAdapter(
                combo,
                new ComboContentAdapter(),
                proposalProvider,
                getActivationKeystroke(),
                getAutoactivationChars());
        }
        else if (control instanceof Text)
        {
             
            Text text = (Text) control;
            proposalProvider = new SimpleContentProposalProvider(items);
            proposalAdapter = new ContentProposalAdapter(
                text,
                new TextContentAdapter(),
                proposalProvider,
                getActivationKeystroke(),
                getAutoactivationChars());
        }
        proposalProvider.setFiltering(true);
        proposalAdapter.setPropagateKeys(true);
        proposalAdapter.setProposalAcceptanceStyle(ContentProposalAdapter.PROPOSAL_REPLACE);

    }
	
	 // this logic is  from swt addons project
    static char[] getAutoactivationChars() {

       // To enable content proposal on deleting a char
        
       String delete = new String(new char[] { 8 });
       String allChars = LCL + UCL + NUMS + delete;
       return allChars.toCharArray();
   }

    static KeyStroke getActivationKeystroke() {
       KeyStroke instance = KeyStroke.getInstance(
               new Integer(SWT.CTRL).intValue(), new Integer(' ').intValue());
       return instance;
   }
}
