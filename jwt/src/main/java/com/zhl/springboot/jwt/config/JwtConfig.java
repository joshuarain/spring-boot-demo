package com.zhl.springboot.jwt.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Lenovo
 * @title: JwtConfig
 * @projectName spring-boot-emo
 * @description: TODO
 * @date 2019/12/28 21:52
 */
@ConfigurationProperties(prefix = "config.jwt")
@Component
public class JwtConfig {

    @Value("${config.jwt.expire}")
    private Integer expire;

    @Value("${config.jwt.secret}")
    private String secret;

    @Value("${config.jwt.header}")
    private String header;

    public String getToken(String identityId){
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime()+1000*expire);
        return Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setSubject(identityId)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.ES512,secret)
                .compact();
    }

    public Claims getTokenClaim(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public boolean isTokenExpired(Date expirationTime){
        return expirationTime.before(new Date());
    }

    public String getHeader(){
        return this.header;
    }
}
