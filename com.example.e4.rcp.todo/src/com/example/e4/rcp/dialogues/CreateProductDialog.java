package com.example.e4.rcp.dialogues;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.example.StoreInDatabase;
import com.example.e4.rcp.tables.ContactDetails;
import com.example.e4.rcp.tables.ProductsTable;

public class CreateProductDialog  extends TitleAreaDialog{
	
	private Text productName;
	private Text productDescription;
	private Collection<ProductsTable> productList;
	private ProductsTable newProduct;
	
	/**
	 * @return the newProduct
	 */
	public ProductsTable getNewProduct() {
		return newProduct;
	}

	public CreateProductDialog(Shell parentShell) {
		super(parentShell);
		// TODO Auto-generated constructor stub
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog#create()
	 */
	@Override
	public void create() {
		// TODO Auto-generated method stub
		System.out.println("came in create product dialog");
		super.create();
		setTitle("Create a Product");
		setMessage("Create Product with unique name and a brief description",IMessageProvider.INFORMATION);
//		productList = new ArrayList<ProductsTable>();
		
	}
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.TitleAreaDialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		GridLayout layout = new GridLayout(2,false);
		container.setLayout(layout);
		
		createProductDetails(container);
		
		return area;
	}
	
	private void createProductDetails(Composite container) {
		Label orgNameLabel = new Label(container,SWT.NONE);
		orgNameLabel.setText("Enter Product Name:");
		productName = new Text(container, SWT.NONE);
		productName.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

		Label orTINLabel = new Label(container,SWT.NONE);
		orTINLabel.setText("Product Description:");
		productDescription = new Text(container, SWT.NONE);
		productDescription.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	@Override
	protected void okPressed() {
		// TODO Auto-generated method stub
		newProduct = new ProductsTable();
		newProduct.setProductName(productName.getText());
		StoreInDatabase sd = new StoreInDatabase();
		ProductsTable inDatabase =(ProductsTable) sd.retrieveProductQuery(newProduct.getProductName());
		if(newProduct.getProductName().isEmpty() || inDatabase != null){
			if(inDatabase !=null){
			String errorMessage = "Product with same name exists"+"\n"
								 +"Product Name : "+inDatabase.getProductName()+"\n"
								 +"Product Description : " + inDatabase.getProductDescription();
			MessageDialog.openError(getShell(), "Error",errorMessage);
			}else{
				MessageDialog.openError(getShell(), "Error", "Invalid Data");
			}
		}else{
		newProduct.setProductName(productName.getText());
		newProduct.setProductDescription(productDescription.getText());
		super.okPressed();
		}
	}
}
