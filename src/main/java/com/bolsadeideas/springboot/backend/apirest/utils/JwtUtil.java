package com.bolsadeideas.springboot.backend.apirest.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    @Value("${spring.application.name}")
    private String appName;
    @Value("${jwt.time-expiration}")
    private String timeExp;
    @Value("${jwt.secret-word}")
    private String secretWord;

    public String createJwtToken(String user, String role, Map<String, Object> claims) {

        int jwtTimeExp = Integer.parseInt(timeExp) * 60000;
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(role);
        String token = Jwts
                .builder()
                .setId(appName)
                .setSubject(user)
                .addClaims(claims)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtTimeExp))
                .signWith(SignatureAlgorithm.HS512,
                        secretWord.getBytes())
                .compact();

        return "Bearer " + token;

    }
}
