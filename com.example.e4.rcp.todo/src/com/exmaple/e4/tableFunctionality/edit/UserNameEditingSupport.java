//package com.exmaple.e4.tableFunctionality.edit;
//
//import org.eclipse.jface.viewers.CellEditor;
//import org.eclipse.jface.viewers.EditingSupport;
//import org.eclipse.jface.viewers.TableViewer;
//import org.eclipse.jface.viewers.TextCellEditor;
//
//import com.example.StoreInDatabase;
//import com.example.e4.rcp.tables.ContactDetails;
//import com.example.e4.rcp.tables.UserDetails;
//
//public class UserNameEditingSupport extends EditingSupport{
//	
//	private final TableViewer viewer;
//	private final CellEditor editor;
//	
//	public UserNameEditingSupport(TableViewer viewer) {
//		super(viewer);
//		this.viewer = viewer;
//		this.editor =  new TextCellEditor(viewer.getTable());
//		
//	}
//	@Override
//	protected CellEditor getCellEditor(Object element) {
//		return editor;
//	}
//
//	@Override
//	protected boolean canEdit(Object element) {
//		return true;
//	}
//
//	@Override
//	protected Object getValue(Object element) {
//		return ((ContactDetails) element).getPersonName();
//	}
//
//	@Override
//	protected void setValue(Object element, Object value) {
//		((ContactDetails)element).setPersonName(String.valueOf(value));
//		new StoreInDatabase().updateUserName(((ContactDetails)element).getId(),String.valueOf(value));
//		viewer.update(element,null);
//	}
//
//}
