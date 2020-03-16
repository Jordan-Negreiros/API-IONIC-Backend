package cursomc.resource.exception;

import javax.servlet.http.HttpServletRequest;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.jordan.cursomc.resource.exception.StandardError;
import com.jordan.cursomc.resource.exception.ValidationErro;
import com.jordan.cursomc.services.exceptions.AuthorizationException;
import com.jordan.cursomc.services.exceptions.FileException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jordan.cursomc.services.exceptions.DataIntegrityException;
import com.jordan.cursomc.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<com.jordan.cursomc.resource.exception.StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		
		com.jordan.cursomc.resource.exception.StandardError error = new com.jordan.cursomc.resource.exception.StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<com.jordan.cursomc.resource.exception.StandardError> dataIntefrity(DataIntegrityException e, HttpServletRequest request) {
		
		com.jordan.cursomc.resource.exception.StandardError error = new com.jordan.cursomc.resource.exception.StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<com.jordan.cursomc.resource.exception.StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
		
		com.jordan.cursomc.resource.exception.ValidationErro error = new ValidationErro(HttpStatus.BAD_REQUEST.value(), "Erro de Validação", System.currentTimeMillis());
		
		for (FieldError xError : e.getBindingResult().getFieldErrors()) {
			error.addError(xError.getField(), xError.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<com.jordan.cursomc.resource.exception.StandardError> authorization(AuthorizationException e, HttpServletRequest request) {

		com.jordan.cursomc.resource.exception.StandardError error = new com.jordan.cursomc.resource.exception.StandardError(HttpStatus.FORBIDDEN.value(), e.getMessage(), System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
	}

	@ExceptionHandler(FileException.class)
	public ResponseEntity<com.jordan.cursomc.resource.exception.StandardError> file(FileException e, HttpServletRequest request) {

		com.jordan.cursomc.resource.exception.StandardError error = new com.jordan.cursomc.resource.exception.StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(AmazonServiceException.class)
	public ResponseEntity<com.jordan.cursomc.resource.exception.StandardError> amazonService(AmazonServiceException e, HttpServletRequest request) {

		HttpStatus code = HttpStatus.valueOf(e.getErrorCode());

		com.jordan.cursomc.resource.exception.StandardError error = new com.jordan.cursomc.resource.exception.StandardError(code.value(), e.getMessage(), System.currentTimeMillis());

		return ResponseEntity.status(code).body(error);
	}

	@ExceptionHandler(AmazonClientException.class)
	public ResponseEntity<com.jordan.cursomc.resource.exception.StandardError> amazonClient(AmazonClientException e, HttpServletRequest request) {

		com.jordan.cursomc.resource.exception.StandardError error = new com.jordan.cursomc.resource.exception.StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(AmazonS3Exception.class)
	public ResponseEntity<com.jordan.cursomc.resource.exception.StandardError> amazonS3(AmazonS3Exception e, HttpServletRequest request) {

		com.jordan.cursomc.resource.exception.StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
}
