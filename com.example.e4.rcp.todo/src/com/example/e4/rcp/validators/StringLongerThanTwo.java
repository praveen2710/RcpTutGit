package com.example.e4.rcp.validators;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

public class StringLongerThanTwo implements IValidator{

	@Override
	public IStatus validate(Object value) {
		if(value instanceof String){
			String s = (String) value;
			//checking for length
			if(s.length() >2){
				return ValidationStatus.ok();
			}else{
				return ValidationStatus.error("The String is insufficient length");
			}
		}
		return (IStatus) new RuntimeException("This should not get executed : StringLongerThanTwo");
	}

}
