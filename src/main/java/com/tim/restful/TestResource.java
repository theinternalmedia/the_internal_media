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
	
	@GetMapping("/message1")
	public String testGetValidateMessage() {
		return GetMessages.getMessage(ETimMessages.VTEACHER_002);
	}
	
	@PostMapping("/message2")
	public String testGetExceptionMessage(/*@RequestBody UserDto userDto*/) {
//		return Messeage.getMessage(userDto.getClass());
		return GetMessages.getMessage(ETimMessages.CAN_NOT_CHANGE_ADMIN_ROOT);
	}
}