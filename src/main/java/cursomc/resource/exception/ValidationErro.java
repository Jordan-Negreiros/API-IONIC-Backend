package cursomc.resource.exception;

import com.jordan.cursomc.resource.exception.FieldMessage;
import com.jordan.cursomc.resource.exception.StandardError;

import java.util.ArrayList;
import java.util.List;

public class ValidationErro extends StandardError {
	private static final long serialVersionUID = 1L;

	private List<com.jordan.cursomc.resource.exception.FieldMessage> errors = new ArrayList<com.jordan.cursomc.resource.exception.FieldMessage>();
	
	public ValidationErro(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
	}

	public List<com.jordan.cursomc.resource.exception.FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String message) {
		errors.add(new FieldMessage(fieldName, message));
	}

	
}
