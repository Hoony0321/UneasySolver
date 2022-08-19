//package com.hunny.uneasySolver.security;
//
//import com.hunny.uneasySolver.service.MemberService;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//public class JwtRequestFilter extends OncePerRequestFilter {
//
//    private final MemberService memberService;
//    private final JwtUtils jwtUtils;
//
//    public JwtRequestFilter(MemberService memberService, JwtUtils jwtUtils) {
//        this.memberService = memberService;
//        this.jwtUtils = jwtUtils;
//    }
//
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//        try{
//            String jwt = getJwtFromRequest(request);
//            if(jwt != null && jwtUtils.validationToken(jwt)) {
//                Object content = jwtUtils.getContentFromJWT(jwt);
//            }
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//
//    }
//
//    private String getJwtFromRequest(HttpServletRequest request) {
//        String bearerToken = request.getHeader("Authorization");
//        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
//            return bearerToken.substring("Bearer ".length());
//        }
//        return null;
//    }
//}
