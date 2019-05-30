package com.coolinternet.ribbon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Service
@FeignClient(value = "service-bookstore")
public interface BookstoreService {
    @GetMapping(value = "/storages")
    String getStorageContent();
//    @Autowired
//    RestTemplate restTemplate;
//
//    public String getStorageContent(){
//        return restTemplate.getForObject("http://service-bookstore/storages",String.class);
//    }
}
