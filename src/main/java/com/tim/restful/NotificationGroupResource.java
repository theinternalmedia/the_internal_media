package com.tim.restful;


import com.tim.data.TimApiPath;
import com.tim.dto.ResponseDto;
import com.tim.dto.notification.NotificationGroupDto;
import com.tim.service.NotificationGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = TimApiPath.NotificationGroup.PREFIX)
public class NotificationGroupResource {

    @Autowired
    private NotificationGroupService notificationGroupService;


    @PostMapping(value = TimApiPath.NotificationGroup.GET)
    public ResponseEntity<ResponseDto> create(@RequestBody NotificationGroupDto dto){
         return ResponseEntity.ok(notificationGroupService.create(dto));
    }
}
