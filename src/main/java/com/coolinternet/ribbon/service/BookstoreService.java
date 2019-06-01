package com.coolinternet.ribbon.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
@FeignClient(value = "service-bookstore",fallback = BookstoreServiceFailure.class)
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
