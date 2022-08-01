package com.tim;

import com.tim.dto.UserDto;

import static com.tim.utils.Messeage.getMessage;

public class test11 {
    public static void main(String[] args) {
        UserDto userDto = new UserDto();
        userDto.setName("");
        System.out.println(getMessage(userDto.getClass()));
    }
}
