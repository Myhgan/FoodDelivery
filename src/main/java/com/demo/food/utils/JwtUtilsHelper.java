package com.demo.food.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Locator;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;

@Component
public class JwtUtilsHelper {
    @Value("${jwt.privateKey}")
    private String privateKet;
    public String generateToken(String data){
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKet));
        String jws = Jwts.builder().subject(data).signWith(key).compact();
        return jws;
    }
    public boolean verifyToken(String token){
        try{
            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKet));
            Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parse(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
