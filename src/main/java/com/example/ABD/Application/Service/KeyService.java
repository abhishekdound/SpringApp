package com.example.ABD.Application.Service;

import com.example.ABD.Application.Model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class KeyService {

    private String key;

    public KeyService() throws NoSuchAlgorithmException {

        key=this.wKey();

    }


    public String getKey(String name) {

        Map<String,Object> claim=new HashMap<>();
        return Jwts.builder().setClaims(claim).setSubject(name).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis()+(1000*60*3))).signWith(createKey(), SignatureAlgorithm.HS256).compact();

    }

    private Key createKey() {

        byte[] byate= Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(byate);
    }

    private String wKey() throws NoSuchAlgorithmException {

        KeyGenerator keyGen=KeyGenerator.getInstance("HmacSHA256");

        SecretKey sk=keyGen.generateKey();

        return Base64.getEncoder().encodeToString(sk.getEncoded());

    }

    public String extractUserName(String token) {
        // extract the username from jwt token
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token)  {
        return Jwts.parserBuilder()
                .setSigningKey(createKey())
                .build().parseClaimsJws(token).getBody();
    }


    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
