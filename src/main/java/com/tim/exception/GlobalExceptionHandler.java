package com.tim.exception;

import com.tim.data.ETimMessages;
import com.tim.dto.errorresponse.ErrorResponse;
import com.tim.utils.Utility;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @appName the_internal_media
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	private final Logger logger = org.slf4j.LoggerFactory.getLogger(GlobalExceptionHandler.class);


	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<String> entityNotFoundException(NotFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	// Validation Exception
	@ExceptionHandler(ValidateException.class)
	public ResponseEntity<ErrorResponse> validateException(ValidateException e) {
		ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), e.getErrMsgs());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	// User not found exception: BadCredentialsException
	@ExceptionHandler(value = { BadCredentialsException.class })
	public ResponseEntity<ErrorResponse> userNotFoundException(BadCredentialsException e) {
		ErrorResponse errorResponse = ErrorResponse.builder()
				.message(Utility.getMessage(ETimMessages.USER_NOT_FOUND)).build();
		return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
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
		ErrorResponse errorResponse = new ErrorResponse(Utility.getMessage(eTimMessages, e.getObjectName()), errors);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	// Custom exception handler
	@ExceptionHandler({ CustomException.class, AuthException.class })
	public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
		logger.error("CustomException error: {}", e.getMessage());

		ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), null);
		
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	/*@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException e){
		logger.error("Data input constrain violation: {}", e.getMessage());
		List<String> errors = new ArrayList<>();
		e.getConstraintViolations().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = ((FieldError) error).getDefaultMessage();
			errors.add((fieldName + ": " + errorMessage));
		});
		ETimMessages eTimMessages = ETimMessages.CONSTRAIN_VAIOLATION_MESSAGE;
		ErrorResponse errorResponse = new ErrorResponse(Utility.getMessage(eTimMessages, e.getMessage()), errors);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ErrorResponse> validateionException(DataIntegrityViolationException ex) {
		logger.info(ex.getClass().getName());
		ErrorResponse errorResponse = new ErrorResponse(
				Utility.getMessage(ETimMessages.CONSTRAIN_VAIOLATION_MESSAGE), null);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}*/

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> validateionException(Exception ex) {
		logger.info(ex.getMessage());
		ErrorResponse errorResponse = new ErrorResponse(
				Utility.getMessage(ETimMessages.INTERNAL_SYSTEM_ERROR), null);
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
