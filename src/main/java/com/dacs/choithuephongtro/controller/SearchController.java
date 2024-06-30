package com.dacs.choithuephongtro.controller;

import com.dacs.choithuephongtro.model.Room;
import com.dacs.choithuephongtro.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/rooms")
    public ResponseEntity<List<Room>> searchRooms(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice,
            @RequestParam(required = false) Integer minSize,
            @RequestParam(required = false) Integer maxSize) {

        List<Room> rooms = searchService.searchRooms(location, minPrice, maxPrice, minSize, maxSize);
        return ResponseEntity.ok(rooms);
    }
}
