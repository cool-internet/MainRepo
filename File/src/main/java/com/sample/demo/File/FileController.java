package com.sample.demo.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    FileService fs;

    @Autowired
    RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void multiImport(@RequestParam("uploadedfile") MultipartFile[] uploadFile) {

        logger.info("上传成功");
        Map<String, Object> result = new HashMap<String, Object>();
        System.out.println(uploadFile.length);
        for (MultipartFile multipartFile:uploadFile) {
            File f= new File();
            f.id=multipartFile.getOriginalFilename();
            f.id=f.id.substring(0,f.id.lastIndexOf('.'));
            try{
                InputStream inputStream = multipartFile.getInputStream();
                InputStreamReader is = new InputStreamReader(inputStream);
                BufferedReader br = new BufferedReader(is);
                String s = "";
                String res="";

                while ((s = br.readLine()) != null) {
                    res+=s;
                }
                f.content=res;
            }catch(IOException e) {}
            this.fs.save(f);
        }
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String get(@PathVariable String id) {
        return restTemplate.getForObject("http://service-auth/auth", String.class);
        //return this.fs.get(id);
    }
}
