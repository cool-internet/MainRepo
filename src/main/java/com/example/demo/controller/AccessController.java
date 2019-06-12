package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.User;
import com.example.demo.jwt.CustomJWT;
import com.example.demo.repository.UserRepository;
import com.example.demo.request.RegistrationRequest;
import com.example.demo.response.NormalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccessController {
    @Autowired
    private UserRepository userRepository;
    @PostMapping(value = "/access")
    public NormalResponse register(@RequestBody RegistrationRequest request)
    {
        try {
            if (userRepository.existsById(request.getUsername())) {
                User user = userRepository.getOne(request.getUsername());
                if (user.getPassword().equals(request.getPassword())) {
                    String token = CustomJWT.generateToken(user.getUsername());
                    JSONObject body = new JSONObject();
                    body.put("token", token);
                    return NormalResponse.login(body);
                }
                return NormalResponse.error();
            } else {
                User user = new User(request.getUsername(), request.getPassword());
                userRepository.save(user);
                String token = CustomJWT.generateToken(user.getUsername());
                JSONObject body = new JSONObject();
                body.put("token", token);
                return NormalResponse.login(body);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return NormalResponse.error();
    }
}
