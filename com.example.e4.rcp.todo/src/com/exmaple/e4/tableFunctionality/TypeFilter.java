package com.exmaple.e4.tableFunctionality;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import com.example.e4.rcp.tables.UserDetails;

public class TypeFilter extends PersonFilter {
		
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if(searchString == null || searchString.length()==0){
			return true;
		}
		UserDetails ud = (UserDetails) element;
		
		//this is a temparoray fix and I do not the overall consequence of doing this
		//but it works for now
		if(ud.getNumber() == null)
			ud.setNumber("");
					
		if(ud.getNumber().matches(searchString)){
			return true;
		}else
			return false;
	}
}
