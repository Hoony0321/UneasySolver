package com.hunny.uneasySolver.api;

import com.hunny.uneasySolver.domain.CommonResponse;
import com.hunny.uneasySolver.dto.*;
import com.hunny.uneasySolver.security.TokenDto;
import com.hunny.uneasySolver.service.AuthService;
import com.hunny.uneasySolver.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final ResponseService responseService;

    @PostMapping("/api/refresh")
    public CommonResponse<TokenDto> reissue(@RequestBody TokenRequestDto request){
        TokenDto tokenDto = authService.reissueToken(request);
        return responseService.getSuccessResponse("새로운 access token이 발급되었습니다.", tokenDto);
    }

    @GetMapping("/api/auth")
    public CommonResponse<Object> checkAuth(){
        return responseService.getSuccessResponse("토큰 인증에 성공했습니다.", null);
    }


    // * 테스트 코드
    @PostMapping("/api/test/signup")
    public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberRequestDto request){
        return ResponseEntity.ok(authService.signup(request));
    }

    @PostMapping("/api/test/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberLoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }

}
