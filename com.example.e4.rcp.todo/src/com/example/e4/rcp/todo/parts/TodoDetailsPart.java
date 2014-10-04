package com.example.e4.rcp.todo.parts;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

import com.example.StoreInDatabase;
import com.example.e4.rcp.tables.hibernateDB;



public class TodoDetailsPart {
	private Text text;
	private Text name;
	private Text outputData;
	private TableViewer tableViewer;
	
	@PostConstruct
	public void createComposite(Composite parent) {
//	
//	parent.setLayout(new GridLayout(3,false));
//		
//	text = new Text(parent,SWT.BORDER);
//	text.setMessage("Enter Number");
//	text.setLayoutData(new GridData(SWT.FILL,SWT.CENTER, true, false, 1, 1));
//	IValidator validator = new IValidator() {
//		
//		@Override
//		public IStatus validate(Object value) {
//			if(value instanceof Integer){
//				if(value.toString().matches(".*\\d.*")){
//					return ValidationStatus.ok();
//				}
//			}
//			return ValidationStatus.error(value.toString()+"is not a number");
//		}
//	};
//	
//	UpdateValueStrategy strategy = new UpdateValueStrategy();
//	strategy.setBeforeSetValidator(validator);
//	
//	name = new Text(parent,SWT.BORDER);
//	name.setMessage("Enter Name");
//	name.setLayoutData(new GridData(SWT.FILL,SWT.CENTER,true,false,1,1));
//	
//	outputData = new Text(parent,SWT.BORDER);
//	outputData.setLayoutData(new GridData(SWT.FILL,SWT.CENTER, true, true,1,3));
//	
//	WritableValue  value =  new WritableValue();
//	
//	//create the binding
//	DataBindingContext ctx =  new DataBindingContext();
//	IObservableValue target = WidgetProperties.text(SWT.Modify).observe(name);
//	IObservableValue model = BeanProperties.value(hibernateDB.class,"userName").observe(value);
//	 
//	 Binding bindValue = ctx.bindValue(target, model,strategy,null);
//	 ControlDecorationSupport.create(bindValue,SWT.TOP | SWT.LEFT);
//	
//	 //create object 
//	 final hibernateDB user = new hibernateDB();
//	 
//	 value.setValue(user);
//	 
//	 Button button = new Button(parent,SWT.PUSH);
//	 button.setText("Enter in table");
//	 tableViewer = new TableViewer(parent);
//	 button.addSelectionListener(new SelectionAdapter() {
//		 @Override
//		 public void widgetSelected(SelectionEvent e){
//			 int number = Integer.parseInt(text.getText());
//			 String person_name = name.getText();	
//			 System.out.println(name.getText());
//			 
//			 user.setUserId(number);
//			 user.setUserName(person_name);		 
//			 try{
//				 tableViewer.add(number);
//				 tableViewer.add(person_name);
//		
//				 user.setUserId(number);
//				 user.setUserName(person_name);
//				 StoreInDatabase sb = new StoreInDatabase();
//				 sb.writeToDatabase(user);
//				 tableViewer.getTable().setLayoutData(new GridData(GridData.FILL_BOTH));
//	        }catch(Exception e1){
//	        	e1.printStackTrace();
//	        }
//		 }
//	});
	}
	
//	@PostConstruct
//	public void createControls(Composite parent){
//		  System.out.println(this.getClass().getSimpleName() 
//				  + " @PostConstruct method called.");
//
//	}
}
