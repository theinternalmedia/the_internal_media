package com.tim.restful;

import com.tim.dto.UserDto;
import com.tim.utils.Messeage;
import org.springframework.web.bind.annotation.*;

import com.tim.data.ETimMessages;
import com.tim.utils.GetMessages;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/test")
public class TestResource extends AbstractResource {
	
	@GetMapping("/message")
	public String testGetExceptionMessage() {
		return GetMessages.getMessage(ETimMessages.USER_NOT_FOUND);
	}
}