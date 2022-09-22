package com.tim.exception;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
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

	/** 
	 * TimException, TimNotFoundException, ValidateException
	 * 
	 * @author minhtuanitk43
	 * @param e
	 * @return
	 */
	@ExceptionHandler({TimException.class, TimNotFoundException.class, ValidateException.class})
	public ResponseEntity<ErrorResponse> timException(TimException e) {
		logger.error(e.getMessage(), e);
		ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), e.getErrMsgs());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	/**
	 * BadCredentialsException
	 * 
	 * @author minhtuanitk43
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = { BadCredentialsException.class })
	public ResponseEntity<ErrorResponse> badCredentialsException(BadCredentialsException e) {
		logger.error(e.getMessage(), e);
		ErrorResponse errorResponse = ErrorResponse.builder()
				.message(MessageUtils.get(ETimMessages.USER_NOT_FOUND)).build();
		return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
	}
	
	/**
	 * AccessDeniedException
	 * 
	 * @author minhtuanitk43
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = { AccessDeniedException.class })
	public ResponseEntity<ErrorResponse> accessDeniedException(AccessDeniedException e) {
		logger.error(e.getMessage(), e);
		ErrorResponse errorResponse = ErrorResponse.builder()
				.message(MessageUtils.get(ETimMessages.FORBIDDEN)).build();
		return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
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
	
	/**
	 * Exception
	 * 
	 * @author minhtuanitk43
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> exception(Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
		ErrorResponse errorResponse = new ErrorResponse(
				MessageUtils.get(ETimMessages.INTERNAL_SYSTEM_ERROR), null);
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
