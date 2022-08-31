package com.hunny.uneasySolver.security;


import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtProvider {

    @Value("${JWT-SECRET-KEY}")
    private String secretKey;
    private static final String BEARER_TYPE = "bearer";
    private static final String AUTHORITIES_KEY = "auth";
    private final Long accessTokenValidTime = 10 * 60 * 1000L; // 10min
    private final Long refreshTokenValidTime = 12 * 60 * 60 * 1000L; // 12hours

    private Key key;

    @PostConstruct
    protected void init(){
        System.out.println(secretKey);
        byte[] keyBytes = Decoders.BASE64.decode(secretKey); // Base64로 인코딩
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    // JWT 생성
    public TokenDto generateToken(Authentication authentication){
        // 권한 가져오기
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();

        // Access Token 생성
        Date accessTokenExpireTime = new Date(now + accessTokenValidTime);
        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())    // payload "sub" : "name"
                .claim(AUTHORITIES_KEY, authorities)     // payload : "auth" : "ROLE_USER" or "ROLE_AUTH"
                .setExpiration(accessTokenExpireTime)    // payload : "exp" : 1516239022(에시)
                .signWith(key, SignatureAlgorithm.HS512) // header "alg" : "HS512"
                .compact();

        // Refresh Token 생성
        Date refreshTokenExpireTime = new Date(now + refreshTokenValidTime);
        String refreshToken = Jwts.builder()
                .setExpiration(refreshTokenExpireTime)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        return TokenDto.builder()
                .grantType(BEARER_TYPE)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public Authentication getAuthentication(String accessToekn){

        //토큰 복호화
        Claims claims = parseClaims(accessToekn);

        if(claims.get(AUTHORITIES_KEY) == null){
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        //클레임에서 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        // UserDetails 객체를 만들어서 Authentication 리턴
        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        }
        catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e){
            log.info("잘못된 JWT 서명입니다.");
        }
        catch (ExpiredJwtException e){
            log.info("만료된 JWT 토큰입니다.");
        }
        catch (UnsupportedJwtException e){
            log.info("지원되지 않는 JWT 토큰입니다.");
        }
        catch (IllegalArgumentException e){
            log.info("올바른 JWT 토큰이 아닙니다.");
        }
        return false;
    }

    private Claims parseClaims(String accessToken){
        try{
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        }
        catch (ExpiredJwtException e){
            return e.getClaims();
        }
    }
}
