package com.example.e4.rcp.todo.parts;

import javax.annotation.PostConstruct;
import javax.annotation.processing.Filer;
import javax.inject.Inject;
import javax.swing.text.View;

import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import com.example.StoreInDatabase;
import com.example.e4.rcp.dialogues.AddPersonDialog;
import com.example.e4.rcp.tables.UserDetails;
import com.exmaple.e4.tableFunctionality.MyViewComparator;
import com.exmaple.e4.tableFunctionality.PersonFilter;
import com.exmaple.e4.tableFunctionality.TypeFilter;
//import com.exmaple.e4.tableFunctionality.edit.UserNameEditingSupport;

import org.eclipse.nebula.widgets.cdatetime.CDT;
import org.eclipse.nebula.widgets.cdatetime.CDateTime;

public class SearchBarViewPart {

	private MyViewComparator comparator;
	private TableViewer viewer;
	private PersonFilter nameFilter;
	private TypeFilter typeFilter;
	private StoreInDatabase sid;
	@Inject
	private ESelectionService selectionService;

	@PostConstruct
	public void createComposite(Composite parent) {

		GridLayout layout = new GridLayout(3, false);
		parent.setLayout(layout);
		Label searchLabel = new Label(parent, SWT.NONE);
		sid = new StoreInDatabase();
		searchLabel.setText("Search:");

		final Text searchText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		searchText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL
				| GridData.HORIZONTAL_ALIGN_FILL));

		searchText.addKeyListener(new KeyAdapter() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.eclipse.swt.events.KeyAdapter#keyReleased(org.eclipse.swt
			 * .events.KeyEvent)
			 */
			@Override
			public void keyReleased(KeyEvent e) {
				System.out.println(searchText.getText());
				viewer.addFilter(nameFilter);
				nameFilter.searchStringText(searchText.getText());
				viewer.refresh();
			}

		});
		
		Label typeLabel = new Label(parent, SWT.NONE);
		typeLabel.setText("Type:");

		final Text typeText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		typeText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL
				| GridData.HORIZONTAL_ALIGN_FILL));

		typeText.addKeyListener(new KeyAdapter() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.eclipse.swt.events.KeyAdapter#keyReleased(org.eclipse.swt
			 * .events.KeyEvent)
			 */
			@Override
			public void keyReleased(KeyEvent e) {
				System.out.println(typeText.getText());
				viewer.addFilter(typeFilter);
				typeFilter.searchStringText(typeText.getText());
				viewer.refresh();
			}

		});
		
		typeFilter = new TypeFilter();
		nameFilter = new PersonFilter();
		final Button searchButton = new Button(parent, SWT.PUSH);
		searchButton.setText("Search");
		createViewer(parent);
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
				
				viewer.setInput(sid.listAllUsers());

				// Set sorter for the table
				// comparator = new MyViewComparator();
				// viewer.setComparator(comparator);
			}
		});
		
		Button newBttn = new Button(parent, SWT.PUSH);
		newBttn.addSelectionListener(new SelectionAdapter() {

			/* (non-Javadoc)
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			@Override
			public void widgetSelected(SelectionEvent e) {
				UserDetails newPerson = new UserDetails();
				Shell shell=null;
				AddPersonDialog dialog = new AddPersonDialog(shell);
				dialog.open();
				if(dialog.getPerson() != null){
//					sid.writeToDatabase(dialog.getPerson());
//					viewer.refresh();
//					viewer.setInput(sid.listAllUsers());
				}
			}
			
			
		});

	}

	private TableViewer createViewer(Composite parent) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		createColumns(parent, viewer);
		final Table table = viewer.getTable();
		viewer.setContentProvider(new ArrayContentProvider());
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection) event
						.getSelection();
				// set the selection to the service
				selectionService.setSelection(selection.size() == 1 ? selection
						.getFirstElement() : selection.toArray());
			}
		});

		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		viewer.getControl().setLayoutData(
				new GridData(SWT.FILL, SWT.FILL, false, true, 3, 1));
		return viewer;
	}

	private void createColumns(Composite parent, TableViewer viewer) {
		String[] titles = { "name", "gender", "age" };
		int[] bounds = { 100, 100, 100, 100 };

		TableViewerColumn col = createTableViewerColumn(titles[0], bounds[0], 0);
		col.setLabelProvider(new ColumnLabelProvider() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.eclipse.jface.viewers.ColumnLabelProvider#update(org.eclipse
			 * .jface.viewers.ViewerCell)
			 */
			@Override
			public void update(ViewerCell cell) {
				cell.setText(((UserDetails) cell.getElement()).getUserName());
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang
			 * .Object)
			 */
			@Override
			public String getText(Object element) {
				UserDetails ud = (UserDetails) element;
				return ud.getUserName();
			}

	});
//		col.setEditingSupport(new UserNameEditingSupport(viewer));

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
				UserDetails ud = (UserDetails) element;
				return ud.getNumber();
			}

		});

		col = createTableViewerColumn(titles[2], bounds[2], 2);
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
				UserDetails ud = (UserDetails) element;
				return Integer.toString(ud.getAge());
			}

		});

	}

	private TableViewerColumn createTableViewerColumn(String title, int bound,
			final int columnNumber) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(viewer,
				SWT.NONE);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(true);
		column.addSelectionListener(getSelectionAdapter(column, columnNumber));
		return viewerColumn;
	}

	public TableViewer getViewer() {
		return viewer;
	}

	private SelectionAdapter getSelectionAdapter(final TableColumn column,
			final int index) {
		SelectionAdapter selectionAdapter = new SelectionAdapter() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse
			 * .swt.events.SelectionEvent)
			 */
			@Override
			public void widgetSelected(SelectionEvent e) {
				comparator.setColumn(index);
				int dir = comparator.getDirection();
				viewer.getTable().setSortDirection(dir);
				viewer.getTable().setSortColumn(column);
				viewer.refresh();
			}
		};
		return selectionAdapter;
	}

	public void setFocus() {
		viewer.getControl().setFocus();
	}

}
