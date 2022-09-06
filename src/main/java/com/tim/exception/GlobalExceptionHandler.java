package com.tim.exception;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tim.data.ETimMessages;
import com.tim.dto.errorresponse.ErrorResponse;
import com.tim.utils.MessageUtils;

/**
 * 
 * @appName the_internal_media
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	private final Logger logger = org.slf4j.LoggerFactory.getLogger(GlobalExceptionHandler.class);

	// TimException handler
	@ExceptionHandler({TimException.class, TimNotFoundException.class, ValidateException.class})
	public ResponseEntity<ErrorResponse> validateException(TimException e) {
		logger.error(e.getMessage());
		ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), e.getErrMsgs());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	// User not found exception: BadCredentialsException
	@ExceptionHandler(value = { BadCredentialsException.class })
	public ResponseEntity<ErrorResponse> userNotFoundException(BadCredentialsException e) {
		logger.error(e.getMessage());
		ErrorResponse errorResponse = ErrorResponse.builder()
				.message(MessageUtils.get(ETimMessages.USER_NOT_FOUND)).build();
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
		ErrorResponse errorResponse = new ErrorResponse(MessageUtils.get(eTimMessages, e.getObjectName()), errors);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> validateionException(Exception ex) {
		logger.error(ex.getMessage());
		ex.printStackTrace();
		ErrorResponse errorResponse = new ErrorResponse(
				MessageUtils.get(ETimMessages.INTERNAL_SYSTEM_ERROR), null);
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
