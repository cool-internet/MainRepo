package com.example.demo.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

public class CustomJWT {
    private static String SIGNATURE = "secret";
    public static String generateToken(String phone)
    {
        String token = JWT.create()
                .withClaim("phone",phone)
                .withIssuer("auth")
                .sign(Algorithm.HMAC256(SIGNATURE));
        return token;
    }
    public static JWTVerifier verifyToken(String phone)
    {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SIGNATURE))
                .withClaim("phone",phone)
                .withIssuer("auth").build();
        return jwtVerifier;
    }
}
