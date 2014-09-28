package com.example.e4.rcp.todo.parts;




import java.util.List;

import javax.inject.Inject;









import org.eclipse.core.databinding.AggregateValidationStatus;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.example.e4.rcp.tables.UserDetails;
import com.example.e4.rcp.validators.StringLongerThanTwo;
import com.example.sampleData;

public class TodoOverviewpart{
	public static final String ID="com.example.e4.rcp.todo.parts.todoView";
	private UserDetails details;
	private Text userName;
	private Text ageText;
	private Button marriedButton;
	private Combo genderCombo;
	private Text countryText;
	private Label errorLabel;
	private ListViewer viewer;
	private WritableList input;
	
	@Inject
	public TodoOverviewpart(Composite parent){
		System.out.println("Woh! this is To Do View");
		System.out.println(parent.getLayout().getClass());
//		
//		details = new UserDetails();
//		
		GridLayout layout = new GridLayout(2,false);
		layout.marginRight = 5;
		parent.setLayout(layout);
		
		Label firstLabel = new Label(parent,SWT.NONE);
		firstLabel.setText("Enter Name");
		userName = new Text(parent,SWT.BORDER);
		userName.setLayoutData(new GridData(SWT.FILL,SWT.CENTER,true,false));
		
		Label ageLabel = new Label(parent, SWT.NONE);
		ageLabel.setText("Age:");
		ageText = new Text(parent,SWT.BORDER);
		ageText.setLayoutData(new GridData(SWT.FILL,SWT.CENTER,true,false));
		
		Label marriedLabel = new Label(parent,SWT.NONE);
		marriedLabel.setText("married");
		marriedButton = new Button(parent, SWT.CHECK);
		
		Label genderLabel = new Label(parent, SWT.NONE);
		genderLabel.setText("Gender:");
		genderCombo = new Combo(parent, SWT.NONE);
		genderCombo.add("Male");
		genderCombo.add("Female");
		
		Label countryLabel = new Label(parent, SWT.NONE);
		countryLabel.setText("Country");
		countryText = new Text(parent, SWT.BORDER);
		countryText.setLayoutData(new GridData(100,25));
		
//		Button button1 = new Button(parent, SWT.PUSH);
//		button1.setText("Write To Model");
//		button1.addSelectionListener(new SelectionAdapter() {
//
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				System.out.println("UserName"+details.getUserName());
//			}
//			
//		});
//		
//		//this displays all error for all bindings
//		Label errorLabelText = new Label(parent, SWT.NONE);
//		errorLabelText.setText("Validation Issues");
//		errorLabel = new Label(parent, SWT.BORDER);
//		errorLabel.setLayoutData(new GridData(SWT.FILL,SWT.CENTER,true,false));
//
//		
//		viewer = new ListViewer(parent);
//		viewer.getControl().setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,2,2));
//		
//		ObservableListContentProvider contentProvider = new ObservableListContentProvider();
//		viewer.setContentProvider(contentProvider);
//
//		IObservableSet knownElements = contentProvider.getKnownElements();
//		final IObservableMap firstName = BeanProperties.value(UserDetails.class,"userName").observeDetail(knownElements);
//		final IObservableMap age = BeanProperties.value(UserDetails.class,"age").observeDetail(knownElements);
//		
//		IObservableMap[] labelMaps = {firstName, age};
//		
//		ILabelProvider labelProvider = new ObservableMapLabelProvider(labelMaps){
//			public String getText(Object element){
//				return firstName.get(element)+" "+age.get(element);
//			}
//		};
//		
//		viewer.setLabelProvider(labelProvider);
//		List<UserDetails> detailList = sampleData.sampleSet();
//		input = new WritableList(detailList,UserDetails.class);
//		viewer.setInput(input);
//		
		Button add = new Button(parent, SWT.PUSH);
		add.setText("add");
//		add.addSelectionListener(new SelectionAdapter() {
//
//			/* (non-Javadoc)
//			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
//			 */
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				addUser();
//			}
//			
//		});
//		
		Button delete = new Button(parent, SWT.PUSH);
//		delete.setLayoutData(new GridData(horizontalAlignment, verticalAlignment, grabExcessHorizontalSpace, grabExcessVerticalSpace));
		delete.setText("delete");
//		add.addSelectionListener(new SelectionAdapter() {
//
//			/* (non-Javadoc)
//			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
//			 */
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				deleteUser();
//			}
//			
//		});
//		
//		bindValues();
//	}
//
//
//	private void bindValues() {
//		DataBindingContext ctx = new DataBindingContext();
//		IObservableValue widgetValue = WidgetProperties.text(SWT.Modify)
//				.observe(userName);
//		IObservableValue modelValue = BeanProperties.value(UserDetails.class,"userName").observe(details);
//		ctx.bindValue(widgetValue, modelValue);
//		
//		UpdateValueStrategy update = new UpdateValueStrategy();
//		update.setAfterConvertValidator(new StringLongerThanTwo());
//		ctx.bindValue(widgetValue, modelValue,update,null);
//		
//		widgetValue = WidgetProperties.text(SWT.Modify).observe(ageText); // the swt to be monitored
//		modelValue = BeanProperties.value(UserDetails.class,"age").observe(details);
//		
//		IValidator intValidator = new IValidator() {
//			
//			@Override
//			public IStatus validate(Object value) {
//				if(value instanceof Integer){
//					String s = String.valueOf(value);
//					if(s.matches("\\d*")){
//						return ValidationStatus.ok();
//					}
//				}
//				return ValidationStatus.error("Not a Number");
//			}
//		};
//		
//		UpdateValueStrategy strategy = new UpdateValueStrategy();
//		strategy.setBeforeSetValidator(intValidator);
//	
//		Binding bindValue = ctx.bindValue(widgetValue, modelValue,strategy,null);
//		
//		ControlDecorationSupport.create(bindValue, SWT.TOP|SWT.LEFT);
//		
//		widgetValue = WidgetProperties.selection().observe(marriedButton);
//		modelValue  = BeanProperties.value(UserDetails.class,"married").observe(details);
//		ctx.bindValue(widgetValue, modelValue);
//		
//		widgetValue = WidgetProperties.text(SWT.Modify).observe(countryText);
//		modelValue = BeanProperties.value(UserDetails.class,"address.country").observe(details);
//		ctx.bindValue(widgetValue, modelValue);
//		
//		final IObservableValue errorObservable  = WidgetProperties.text().observe(errorLabel);
//		ctx.bindValue(errorObservable,new AggregateValidationStatus(ctx.getBindings(),AggregateValidationStatus.MAX_SEVERITY),null,null);
	}
	
	public void addUser(){
		UserDetails u1 = new UserDetails();
		u1.setUserId(14);
		u1.setUserName("ali");
		input.add(u1);
	}
	
	public void deleteUser(){
		if(!viewer.getSelection().isEmpty()){
			IStructuredSelection selection = (IStructuredSelection)viewer.getSelection();
			UserDetails u1 = (UserDetails) selection.getFirstElement();
			input.remove(u1);
		}
	}
}
