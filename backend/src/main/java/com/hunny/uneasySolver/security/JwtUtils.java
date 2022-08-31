package com.hunny.uneasySolver.security;


import com.hunny.uneasySolver.dto.MemberDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
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

@Component
@RequiredArgsConstructor
public class JwtUtils {

    @Value("${JWT-SECRET-KEY}")
    private String secretKey;

    private Key key;
    private long tokenValidTime = 30 * 60 * 1000L; //유효시간 30

    @PostConstruct
    protected void init() {
        System.out.println(secretKey);
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(MemberDTO member){

        //Header 부분 설정
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS256");

        //payload 설정 ( token에 담을 값 )
        Map<String, Object> payloads = new HashMap<>();
        payloads.put("id", member.getId());
        payloads.put("email", member.getEmail());
        payloads.put("nickname", member.getNickname());
        payloads.put("auth", member.getAuthority());

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

    public Claims parseJwt(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token).getBody();
        return claims;
    }

    public Boolean validationToken(String token){
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token).getBody();
            return true;
        }
        catch (ExpiredJwtException e){
            throw e;
        }
        catch(Exception e){
            throw e;
        }
    }

    public Authentication getAuthentication(String token){
        //토큰 복호화
        Claims claims = parseJwt(token);

        // 클레임에서 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("auth").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        UserDetails userDetails = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
    }
}
