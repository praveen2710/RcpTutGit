package com.exmaple.e4.tableFunctionality;



import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.viewers.ObservableValueEditingSupport;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.example.e4.rcp.tables.ContactDetails;
import com.example.e4.rcp.tables.OrgDetails;

public class DropDownInTable extends EditingSupport {
	
	private ComboBoxViewerCellEditor cellEditor=null;
	
	public DropDownInTable(ColumnViewer viewer) {
		super(viewer);
		
		cellEditor = new ComboBoxViewerCellEditor((Composite) getViewer().getControl(),SWT.READ_ONLY);
		cellEditor.setLabelProvider(new LabelProvider());
		cellEditor.setContenProvider(new ArrayContentProvider());
	}

	@Override
	protected CellEditor getCellEditor(Object element) {
		// TODO Auto-generated method stub
		System.out.println(element.toString());
		OrgDetails od = (OrgDetails) element;
		Collection<ContactDetails> contactList = od.getContacts();
		Collection<Long> allContacts = new HashSet<Long>();
//		System.out.println(contactList.toString());
		
		for(ContactDetails eachContact: contactList){
			allContacts.add(eachContact.getContactNumber());
		}
//		System.out.println("allContacts"+allContacts.toString());
		cellEditor.setInput(od.getContacts());
		return cellEditor;
	}

	@Override
	protected boolean canEdit(Object element) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected Object getValue(Object element) {
		System.out.println("cam in getValue");
		if(element instanceof OrgDetails){
			OrgDetails singleOrg = (OrgDetails) element;
			return singleOrg.getContacts();
		}
		return null;
	}

	@Override
	protected void setValue(Object element, Object value) {
		System.out.println("came in set value");
		if(element instanceof OrgDetails && value instanceof Long){
			OrgDetails singleOrg = (OrgDetails) element;
			Long updateContact = (Long) value;
			
		}
	}
	
}
