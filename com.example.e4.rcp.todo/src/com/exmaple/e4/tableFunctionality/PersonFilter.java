package com.exmaple.e4.tableFunctionality;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import com.example.e4.rcp.tables.UserDetails;

public class PersonFilter extends ViewerFilter {

	protected String searchString;
	
	public void searchStringText(String s){
		
		this.searchString = ".*"+s+".*";
	}
	
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if(searchString == null || searchString.length()==0){
			return true;
		}
		UserDetails ud = (UserDetails) element;
		if(ud.getUserName().matches(searchString)){
			return true;
		}
		return false;
	}

}
