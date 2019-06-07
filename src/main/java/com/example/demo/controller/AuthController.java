package com.example.demo.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.example.demo.jwt.CustomJWT;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AuthController {
    @GetMapping(value = "/auth")
    public boolean isLogin(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
        String token = httpServletRequest.getHeader("token");
        String phone = JWT.decode(token).getClaim("phone").asString();
        JWTVerifier jwtVerifier = CustomJWT.verifyToken(phone);
        jwtVerifier.verify(token);
        return true;
    }
}
