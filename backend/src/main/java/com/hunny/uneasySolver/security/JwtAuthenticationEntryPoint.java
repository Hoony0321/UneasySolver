package com.hunny.uneasySolver.security;

import com.hunny.uneasySolver.domain.CommonResponse;
import com.hunny.uneasySolver.repository.RoomRepository;
import com.hunny.uneasySolver.service.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ResponseService responseService;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        CommonResponse<Object> commonResponse = responseService.getFailureResponse(
                HttpStatus.UNAUTHORIZED.value(),
                "토큰 인증 과정 중에 오류가 발생했습니다. 다시 로그인하세요.",
                null
        );

        responseService.convertObjectToResposne(response, commonResponse);
    }
}
