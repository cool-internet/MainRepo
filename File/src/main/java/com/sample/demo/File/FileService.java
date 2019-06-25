package com.sample.demo.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {
    @Autowired FileRepo fr;
    void save(File f){
        fr.save(f);
    }
    String get(String id){
        return fr.findById(id).get().content;
    }
}
