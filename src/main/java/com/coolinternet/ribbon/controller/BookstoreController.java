package com.coolinternet.ribbon.controller;

import com.coolinternet.ribbon.service.BookstoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookstoreController {
    @Autowired
    BookstoreService bookstoreService;
    @GetMapping(value = "/")
    public String getStorages(){
        return bookstoreService.getStorageContent();
    }
}
