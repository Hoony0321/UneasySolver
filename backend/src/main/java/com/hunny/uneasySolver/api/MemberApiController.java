package com.hunny.uneasySolver.api;

import com.hunny.uneasySolver.domain.CommonResponse;
import com.hunny.uneasySolver.dto.*;
import com.hunny.uneasySolver.security.JwtProvider;
import com.hunny.uneasySolver.domain.Member;
import com.hunny.uneasySolver.security.TokenDto;
import com.hunny.uneasySolver.service.AuthService;
import com.hunny.uneasySolver.service.MemberService;
import com.hunny.uneasySolver.service.ResponseService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;
    private final AuthService authService;
    private final ResponseService responseService;

    @Data
    @AllArgsConstructor
    public class LoginRequest{
        private String email;
        private String password;
    }

    @PostMapping("/api/login")
    public CommonResponse<Object> login(@RequestBody MemberLoginRequest request){
        TokenDto tokenDto = authService.login(request);
        return responseService.getSuccessResponse("로그인 성공", tokenDto);
    }

    @PostMapping("/api/register")
    public void register(@RequestBody @Valid MemberRegisterRequest request){
        memberService.join(request);
    }

    @GetMapping("/api/members/check/email")
    public boolean checkEmailDuplicated(@RequestParam(value = "email") String email){
        return memberService.checkEmailDuplicated(email);
    }

    @GetMapping("/api/members/check/nickname")
    public boolean checkNickNameDuplicated(@RequestParam(value = "nickname")String nickname){
        return memberService.checkEmailDuplicated(nickname);
    }

}
