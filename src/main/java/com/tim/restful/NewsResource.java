package com.tim.restful;


import com.tim.data.TimApiPath;
import com.tim.dto.ResponseDto;
import com.tim.dto.news.NewsDto;
import com.tim.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = TimApiPath.News.PREFIX)
public class NewsResource {

    @Autowired
    private NewsService newsService;


    @PostMapping(value = TimApiPath.News.SAVE)
    public ResponseEntity<ResponseDto> create(@RequestBody NewsDto newsDto){
        return ResponseEntity.ok(newsService.create(newsDto));
    }

    @PutMapping(value = TimApiPath.News.UPDATE)
    public ResponseEntity<ResponseDto> update(@RequestBody NewsDto newsDto){
        return ResponseEntity.ok(newsService.update(newsDto));
    }

    @GetMapping(value = TimApiPath.News.GET)
    public ResponseEntity<ResponseDto> getOne(@RequestParam Long id){
        return ResponseEntity.ok(newsService.getOne(id));
    }

    @DeleteMapping(value = TimApiPath.News.DELETE)
    public ResponseEntity<ResponseDto> delete(@RequestParam Long id){
        return ResponseEntity.ok(newsService.toogleStatus(id));
    }
}
