package com.coolinternet.ribbon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BookstoreService {
    @Autowired
    RestTemplate restTemplate;

    public String getStorageContent(){
        return restTemplate.getForObject("http://service-bookstore/storages",String.class);
    }
}
