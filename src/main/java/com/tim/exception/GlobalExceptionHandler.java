package com.tim.exception;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tim.data.EExceptionMessage;
import com.tim.payload.response.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	Logger logger = org.slf4j.LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<String> accessDeniedException(AccessDeniedException e) {
		return new ResponseEntity<>(EExceptionMessage.ACCESS_DENIED.message, HttpStatus.FORBIDDEN);
	}

//	Not found exception
	@ExceptionHandler(value = { UsernameNotFoundException.class })
	public ResponseEntity<String> userNotFoundException(Exception e) {
		return new ResponseEntity<>(EExceptionMessage.USER_NOT_FOUND.message, HttpStatus.UNAUTHORIZED);
	}

//	Validate input exception handler
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
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

//	Custom exception handler
	@ExceptionHandler({CustomException.class, AuthException.class})
	public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
		logger.error("CustomException error: {}", e.getMessage());
		
		String res = e.getMessage() + (StringUtils.isBlank(e.getValue()) ? "" : ": '" + e.getValue() + "'");
		
		ErrorResponse response = new ErrorResponse(e.getCode(), res);

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = PasswordNotMatchException.class)
	public ResponseEntity<String> passwordNotMatchException(CustomException e) {
		logger.error("Password not match error: {}" + e.getMessage() + ": " + e.getCode() + e.getValue());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

//	@ResponseStatus(HttpStatus.NOT_FOUND)
//	@ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleUnwantedException(Exception e) {
//        e.printStackTrace(); 
//        logger.error("Integer system error: {}", e.getMessage());
//        return ResponseEntity.status(500).body("Unknow error");
//    }
}
