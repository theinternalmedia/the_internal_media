package com.tim.exception;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tim.data.ETimMessages;
import com.tim.utils.GetMessages;

/**
 * 
 * @appName the_internal_media
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	private final Logger logger = org.slf4j.LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<String> accessDeniedException(AccessDeniedException e) {
		return new ResponseEntity<>(GetMessages.getMessage(ETimMessages.ACCESS_DENIED), HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<String> entityNotFoundException(EntityNotFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	// User not found exception
	@ExceptionHandler(value = { BadCredentialsException.class })
	public ResponseEntity<String> userNotFoundException(BadCredentialsException e) {
		return new ResponseEntity<>(GetMessages.getMessage(ETimMessages.USER_NOT_FOUND), HttpStatus.UNAUTHORIZED);
	}

	// Validate input exception handler
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleInValidInputException(MethodArgumentNotValidException e) {
		logger.error("Data input invalid: {}", e.getMessage());
		Map<String, String> errors = new HashMap<>();
		e.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}

	// Custom exception handler
//	@ExceptionHandler({ CustomException.class, AuthException.class })
//	public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
//		logger.error("CustomException error: {}", e.getMessage());
//
//		String res = e.getMessage() + (StringUtils.isBlank(e.getValue()) ? "" : ": '" + e.getValue() + "'");
//
//		ErrorResponse response = new ErrorResponse(e.getCode(), res);
//
//		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//	}
	
	// ConstraintViolationException
	@ExceptionHandler(Exception.class)
	public ResponseEntity<List<String>> validateionException(Exception ex) {
		logger.info(ex.getClass().getName());
	    //
	    try {
	    	ConstraintViolationException ex1 = (ConstraintViolationException) ex;
	        final List<String> errors = new ArrayList<String>();
	        for (final ConstraintViolation<?> violation : ex1.getConstraintViolations()) {
	            errors.add(violation.getPropertyPath() + ": " + violation.getMessage());
	        }
	        return new ResponseEntity<List<String>>(errors, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
//			try {
//				BadCredentialsException ex2 = (BadCredentialsException) ex;
//			} catch (Exception e2) {
//				// TODO: handle exception
//			}
			 return new ResponseEntity<List<String>>(new ArrayList<>(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}

}
