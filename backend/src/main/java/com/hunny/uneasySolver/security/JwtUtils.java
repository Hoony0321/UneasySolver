package com.hunny.uneasySolver.security;

import com.hunny.uneasySolver.dto.MemberDTO;
import com.hunny.uneasySolver.service.MemberService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtUtils {
    private final String secretKey = "UneasySecretKey1111asdiojofvaekjdopiavjkajcoidcjoiacjeoiajoijvjavsdjovjdiaosjekvasjl"; // TODO 추후에 따로 secret 파일 만들어서 숨기기.
    private Key key;
    private long tokenValidTime = 30 * 60 * 1000L; //유효시간 30

    @PostConstruct
    protected void init() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(Long id){

        //Header 부분 설정
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS256");

        //payload 설정 ( token에 담을 값 )
        Map<String, Object> payloads = new HashMap<>();
        payloads.put("id", id);

        //현재 시간 가져오기
        Date now = new Date();

        //토큰 Builder
        String jwtToken = Jwts.builder()
                .setHeader(headers)
                .setClaims(payloads)
                .setExpiration(new Date((now.getTime() + tokenValidTime)))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact(); // 토큰 생성

        return jwtToken;
    }

    public String getMemberIdFromJWT(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token).getBody();

        return claims.getSubject();
    }

    public Boolean validationToken(String token){
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token).getBody();


            System.out.println(claims);

            return true;
        }
        catch (ExpiredJwtException e){
            throw e;
        }
        catch(Exception e){
            throw e;
        }
    }
}
