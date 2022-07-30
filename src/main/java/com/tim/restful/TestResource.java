package com.tim.restful;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tim.data.ETimMessages;
import com.tim.utils.GetMessages;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/test")
public class TestResource extends AbstractResource {
	
	@GetMapping("/message1")
	public String testGetValidateMessage() {
		return GetMessages.getMessage(ETimMessages.VTEACHER_002);
	}
	
	@GetMapping("/message2")
	public String testGetExceptionMessage() {
		return GetMessages.getMessage(ETimMessages.CAN_NOT_CHANGE_ADMIN_ROOT);
	}
}