package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.example.demo.jwt.CustomJWT;
import com.example.demo.response.NormalResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AuthController {
    @PostMapping(value = "/auth")
    public NormalResponse isLogin(HttpServletRequest httpServletRequest)
    {
        try {
            String token = httpServletRequest.getHeader("token");
            String phone = JWT.decode(token).getClaim("phone").asString();
            JWTVerifier jwtVerifier = CustomJWT.verifyToken(phone);
            jwtVerifier.verify(token);
            JSONObject body = new JSONObject();
            body.put("isLogin","true");
            return NormalResponse.login(body);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return NormalResponse.error();
    }
}
