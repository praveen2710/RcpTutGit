package com.exmaple.e4.tableFunctionality;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.example.e4.rcp.tables.ContactDetails;

public final class ExampleTableLabelProvider extends LabelProvider implements ITableLabelProvider {
	  public Image getColumnImage(Object element, int columnIndex) {
	    return null;
	  }
	  public String getColumnText(Object element, int columnIndex) {
	    ContactDetails data = (ContactDetails) element;
	    switch (columnIndex) {
	      case 0:
	        return data.getPersonName();
	      case 1:
	        return Long.toString(data.getContactNumber());
	      default:
	        return "";
	      }
	  }
	         
	}