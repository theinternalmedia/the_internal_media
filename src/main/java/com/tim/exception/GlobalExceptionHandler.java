package com.tim.exception;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import com.tim.dto.errorresponse.ErrorResponse;
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

	// Validation Exception
	@ExceptionHandler(ValidateException.class)
	public ResponseEntity<ErrorResponse> validateException(ValidateException e) {
		ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), e.getErrMsgs());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	// User not found exception
	@ExceptionHandler(value = { BadCredentialsException.class })
	public ResponseEntity<String> userNotFoundException(BadCredentialsException e) {
		return new ResponseEntity<>(GetMessages.getMessage(ETimMessages.USER_NOT_FOUND), HttpStatus.UNAUTHORIZED);
	}

	// Validate input exception handler
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleInValidInputException(MethodArgumentNotValidException e) {
		logger.error("Data input invalid: {}", e.getMessage());
		List<String> errors = new ArrayList<>();
		e.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.add(fieldName + ": " + errorMessage);
		});
		ETimMessages eTimMessages = ETimMessages.VALIDATION_ERR_MESSAGE;
		ErrorResponse errorResponse = new ErrorResponse(
				GetMessages.getMessage(eTimMessages, e.getObjectName()), errors);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	// ConstraintViolationException
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<List<String>> validateionException(Exception ex) {
//		logger.info(ex.getClass().getName());
//		//
//		try {
//			ConstraintViolationException ex1 = (ConstraintViolationException) ex;
//			final List<String> errors = new ArrayList<String>();
//			for (final ConstraintViolation<?> violation : ex1.getConstraintViolations()) {
//				errors.add(violation.getPropertyPath() + ": " + violation.getMessage());
//			}
//			return new ResponseEntity<List<String>>(errors, new HttpHeaders(), HttpStatus.BAD_REQUEST);
//		} catch (Exception e) {
//			try {
//				BadCredentialsException ex2 = (BadCredentialsException) ex;
//				return new ResponseEntity<List<String>>(Arrays.asList(ex2.getMessage()), new HttpHeaders(),
//						HttpStatus.BAD_REQUEST);
//			} catch (Exception e2) {
//
//			}
//			return new ResponseEntity<List<String>>(new ArrayList<>(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
//		}
//	}

}
