package com.tim.restful;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tim.data.ETimMessages;
import com.tim.utils.Utility;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/test")
public class TestResource extends AbstractResource {
	
	@GetMapping("/message")
	public String testGetExceptionMessage() {
		return Utility.getMessage(ETimMessages.ACCESS_DENIED);
	}
}