package com.example.e4.rcp.dialogues;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.snippets.viewers.Snippet005TreeCustomMenu.MyModel;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.menus.IMenuService;

public class ProductListDialog extends Dialog{

	private TableViewer productListTable;
	public ProductListDialog(Shell parentShell) {
		super(parentShell);
		// TODO Auto-generated constructor stub
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog#create()
	 */
	@Override
	public void create() {
		// TODO Auto-generated method stub
		super.create();
		
		
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.TitleAreaDialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
	
		Composite container = (Composite) super.createDialogArea(parent);
	    GridLayout layout = new GridLayout(4, false);
	    layout.marginRight = 5;
	    layout.marginLeft = 10;
	    container.setLayout(layout);
	    
	    productListTreeCheckBox(parent);
		return super.createDialogArea(parent);
	}
	
	
	private void productListTreeCheckBox(Composite composite) {
		 Tree check = new Tree(composite, SWT.CHECK | SWT.BORDER);
		  GridData  data = new GridData(GridData.FILL_BOTH);
		    check.setLayoutData(data);
		    fillTree(check);
		  
		    MenuManager menuManager = new MenuManager();
		    Menu menu = menuManager.createContextMenu(composite.getTable());
		    // set the menu on the SWT widget
		    viewer.getTable().setMenu(menu);
	}

	private void fillTree(Tree tree) {
		
		MenuManager contextMenu=new MenuManager("#PopUp");
		
		 // Turn off drawing to avoid flicker
	    tree.setRedraw(false);

	    // Create five root items
	    for (int i = 0; i < 5; i++) {
	      TreeItem item = new TreeItem(tree, SWT.NONE);
	      item.setText("Root Item " + i);

	      // Create three children below the root
	      for (int j = 0; j < 3; j++) {
	        TreeItem child = new TreeItem(item, SWT.NONE);
	        child.setText("Child Item " + i + " - " + j);

	        // Create three grandchildren under the child
	        for (int k = 0; k < 3; k++) {
	          TreeItem grandChild = new TreeItem(child, SWT.NONE);
	          grandChild.setText("Grandchild Item " + i + " - " + j + " - " + k);
	        }
	      }
	    }
	    // Turn drawing back on!
	    tree.setRedraw(true);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	@Override
	protected void okPressed() {
		// TODO Auto-generated method stub
		super.okPressed();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.TitleAreaDialog#getInitialSize()
	 */
	@Override
	protected Point getInitialSize() {
		
		return new Point(280,300);
	}
	
}
