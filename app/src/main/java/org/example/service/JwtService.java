package org.example.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.example.entities.UserInfo;
import java.security.Key;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public class JwtService {

    public static String SECRET = "zv6dk1onwo2vipqz96haklhopa1kbvv753rebpslybhhgbg83pzn7axbc4twj89pwsko9ew2xz4r5ib2kw3km4w3wyarqripyp1rwpaftvbibd94kfkc7z85g5jk3o6m";

    public String extractUserName(String token){
        return this.extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token){
        return this.extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsTFunction) {
        Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Boolean validateToken(String token, UserInfo userInfo){
        return  extractUserName(token).equals(userInfo.getUsername()) && !isExpired(token);
    }

    public Boolean isExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public String createToken(Map<String, Object> claims, String username){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*1))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
    }
}
