package lk.ijse.hello_shoes_shop_backend.Service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import lk.ijse.hello_shoes_shop_backend.Service.JWTService;
import lk.ijse.hello_shoes_shop_backend.entity.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Service
public class JWTServiceIMPL implements JWTService {
    private long accessTokenValidity = 1*60*24;

    @Value("${token.key}")
    private String jwtKey;


    @Override
    public String extractUserName(String token) {
        return extractClaim(token,Claims::getSubject);
    }


    @Override
    public String generateToken(UserEntity userEntity) {
        return generateToken(new HashMap<>(),userEntity);
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        String userName = extractUserName(token);
        return userName.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
    //actual process

    private <T> T extractClaim(String token, Function<Claims,T> claimResolve) {
        Claims allClaims = getAllClaims(token);
        return claimResolve.apply(allClaims);
    }

    private String generateToken(Map<String,Object> extractClaims, UserEntity userEntity){
        Claims claims = Jwts.claims().setSubject(userEntity.getEmail());
        claims.put("name",userEntity.getName());
        //claims.put("role",userEntity.getRole());
        extractClaims.put("role",userEntity.getAuthorities());

        Date tokenCreateTime = new Date();
        Date tokenValidity = new Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(accessTokenValidity));
        return Jwts.builder()
                .setClaims(extractClaims)
                .setSubject(userEntity.getEmail())
                .setExpiration(tokenValidity)
                .signWith(SignatureAlgorithm.HS256, jwtKey)
                .compact();




    }

    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }

    private Claims getAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
    }

    private Key getSignKey(){
        byte[] decode = Decoders.BASE64.decode(jwtKey);
        return Keys.hmacShaKeyFor(decode);
    }


}
