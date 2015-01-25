package com.example.e4.rcp.todo.handlers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;

import com.example.StoreInDatabase;
import com.example.e4.rcp.dialogues.CreateProductDialog;
import com.example.e4.rcp.tables.DatabaseAccess;

public class CreateProduct {
	@Execute
	  public void execute() {
		System.out.println("Came in create product");
		Shell shell = null;
		CreateProductDialog dialog = new CreateProductDialog(shell);
		dialog.create();
		
		if(dialog.open() == Window.OK){
			List<DatabaseAccess> affectedTables = new ArrayList<DatabaseAccess>();
			StoreInDatabase sd = new StoreInDatabase();
			affectedTables.add(dialog.getNewProduct());
			sd.writeToDatabase(affectedTables);
		}
		
	}
}
