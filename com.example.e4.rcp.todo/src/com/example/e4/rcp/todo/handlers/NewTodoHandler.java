package com.example.e4.rcp.todo.handlers;

import org.eclipse.e4.core.di.annotations.Execute;

public class NewTodoHandler {
	@Execute
	  public void execute() {
	    System.out.println((this.getClass().getSimpleName() + " called"));
//	    try {
//	        HandlerUtil.getActiveWorkbenchWindowChecked(event).
//	          getActivePage().showView("myplugin.mycustomviewID");
//	      } catch (PartInitException e) {
//	        e.printStackTrace();
//	      }
//	      return null;
//	    }
	  }
}
