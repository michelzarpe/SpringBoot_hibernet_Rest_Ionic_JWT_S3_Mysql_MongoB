package com.michelzarpelon.cursomcmz.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;

	private List<FieldMessage> errors = new ArrayList<>();

	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);

	}

	/*modificando depois do get, muda tambem o nome do parametro no Json*/
	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fildName, String messagem) {
		errors.add(new FieldMessage(fildName, messagem));
	}

}
