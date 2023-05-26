package com.illutech.advertsite.config.jwtconfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
public class JwtService {
    private static final String SECRET_KEY="26452948404D635166546A576E5A7234753778217A25432A462D4A614E645267";

    public String extractUserName(String jwtToken) {
        //TODO extractUserName
        return null;
    }
    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] keyBits = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBits);
    }
}
