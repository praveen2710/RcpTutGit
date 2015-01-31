package com.details.handler;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;

import com.details.dialogues.AddOrganizationDialog;
import com.details.resources.StoreInDatabase;
import com.details.tables.DatabaseAccess;


public class AddOrganization {
	@Execute
	  public void execute() {
		Shell shell = null;
		AddOrganizationDialog dialog = new AddOrganizationDialog(shell);
		dialog.create();
		
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
	  }
}
