package com.example.e4.rcp.todo.parts;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

import com.example.StoreInDatabase;
import com.example.e4.rcp.tables.UserDetails;
import com.example.e4.rcp.tables.hibernateDB;



public class TodoDetailsPart {
	
	protected Text comboSelection;
	protected Text listSelection;
	
	@PostConstruct
	public void createComposite(Composite parent) {
	GridLayout layout = new GridLayout(2,false);
	parent.setLayout(layout);
	
	Label selectPerson = new Label(parent, SWT.NONE);
	selectPerson.setText("Select a type");
	
	final ComboViewer viewer = new ComboViewer(parent, SWT.MULTI|SWT.READ_ONLY);
	
	viewer.setContentProvider(ArrayContentProvider.getInstance());
	
	viewer.setLabelProvider(new LabelProvider(){

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
		 */
		@Override
		public String getText(Object element) {
			if(element instanceof UserDetails){
				UserDetails name = (UserDetails) element;
				return name.getUserName();
			}
			return super.getText(element);
		}
	});
	StoreInDatabase sd = new StoreInDatabase();
	List<UserDetails> allNames = sd.listAllUsers();
	
	viewer.setInput(allNames);
	
	viewer.addSelectionChangedListener(new ISelectionChangedListener() {
		
		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			IStructuredSelection selection = (IStructuredSelection) event.getSelection();
			if(selection.size()>0){
				System.out.println(((UserDetails)selection.getFirstElement()).getNumber());
				comboSelection.setText(((UserDetails)selection.getFirstElement()).getNumber());
			}
			
		}
	});
	
	comboSelection = new Text(parent, SWT.READ_ONLY);
	comboSelection.setLayoutData(new GridData(SWT.FILL,SWT.CENTER,true,true));
	
	listSelection = new Text(parent, SWT.READ_ONLY);
	listSelection.setLayoutData(new GridData(SWT.FILL,SWT.CENTER,true,true));
	
	final ListViewer lists = new ListViewer(parent, SWT.MULTI|SWT.V_SCROLL|SWT.H_SCROLL);
	lists.setContentProvider(ArrayContentProvider.getInstance());
	lists.setLabelProvider(new LabelProvider(){

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
		 */
		@Override
		public String getText(Object element) {
			if(element instanceof UserDetails){
				UserDetails name = (UserDetails) element;
				return name.getUserName();
			}
			return super.getText(element);
		}
	});
	lists.setInput(allNames);
	lists.addSelectionChangedListener(new ISelectionChangedListener() {
		
		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			IStructuredSelection selection = (IStructuredSelection) event.getSelection();
			StringBuffer sb = new StringBuffer("Selection- ");
			sb.append("Total"+selection.size()+"items selected:");
			for(Iterator iterator = selection.iterator(); iterator.hasNext();){
				sb.append(iterator.next()+",");
			}
			System.out.println("Lists:"+sb);
			listSelection.setText(sb.toString());
		}
	});
	
	
	
	
	}
	
}
