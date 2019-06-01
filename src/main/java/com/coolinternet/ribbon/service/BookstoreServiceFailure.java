package com.coolinternet.ribbon.service;

import org.springframework.stereotype.Service;

@Service
public class BookstoreServiceFailure implements BookstoreService{
    @Override
    public String getStorageContent()
    {
        return "service failed";
    }
}
