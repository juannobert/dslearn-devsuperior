package com.devsuperior.curso.dslearn.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{
	
	private List<FieldMessage> errors = new ArrayList<>();
	
	public ValidationError() {}

	public void add(String fieldName,String fieldMessage) {
		errors.add(new FieldMessage(fieldName, fieldMessage));
	}
	public List<FieldMessage> getErrors() {
		return errors;
	}
	
	

}
