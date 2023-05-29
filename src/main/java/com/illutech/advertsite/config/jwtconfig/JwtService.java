package com.illutech.advertsite.config.jwtconfig;

import com.illutech.advertsite.config.customconfigs.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY="26452948404D635166546A576E5A7234753778217A25432A462D4A614E645267";





    public boolean isTokenValid(String token,CustomUserDetails userDetails){
        final String username = extractUserName(token);
        return (username.equals(userDetails.getUsername()))&& !isTokenExpired(token);
    }
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    public String generateToken( CustomUserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }
    public String generateToken(Map<String, Object> extraClaims, CustomUserDetails userDetails){
    return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis())).
            setExpiration(new Date(System.currentTimeMillis()+ 1000*60*24)).
            signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
    }
    public String extractUserName(String jwtToken) {

        return extractClaim(jwtToken,Claims::getSubject);
    }
    private <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
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
