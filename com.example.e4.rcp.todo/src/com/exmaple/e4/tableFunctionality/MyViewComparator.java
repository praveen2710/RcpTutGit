package com.exmaple.e4.tableFunctionality;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;

import com.example.e4.rcp.tables.UserDetails;
/**
 * 
 * This is to be used to sort data in table according to specific rows.
 * @author PB033954
 *
 */
public class MyViewComparator extends ViewerComparator {
	private int propertyIndex;
	private static final int DESCENDING  = 1;
	private int direction = DESCENDING;
	
	public MyViewComparator(){
		this.propertyIndex = 0;
		direction = DESCENDING;
	}
	
	public int getDirection() {
		return direction == 1?SWT.DOWN:SWT.TOP;
	}
	
	public void setColumn(int column){
		if(column == this.propertyIndex){
			direction = 1 - direction;
		}else{
			this.propertyIndex = column;
			direction = DESCENDING;
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ViewerComparator#compare(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		UserDetails u1 = (UserDetails) e1;
		UserDetails u2 = (UserDetails) e2;
		int rc = 0;
		System.out.println(propertyIndex+u1.getUserName()+" "+u2.getUserName());;
		switch(propertyIndex){
		case 0:
			rc = u1.getUserName().compareTo(u2.getUserName());
		case 1:
			rc = u1.getGender().compareTo(u2.getGender());
		// need to check how to do same for integer's
		default:
			rc=0;
		}	
		if(direction == DESCENDING){
			rc =-rc;
		}
		return rc;
	}
}
