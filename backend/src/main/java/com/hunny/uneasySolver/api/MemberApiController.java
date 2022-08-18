package com.hunny.uneasySolver.api;

import com.hunny.uneasySolver.security.JwtUtils;
import com.hunny.uneasySolver.domain.Member;
import com.hunny.uneasySolver.dto.MemberLoginRequest;
import com.hunny.uneasySolver.dto.MemberLoginResponse;
import com.hunny.uneasySolver.dto.MemberRegisterRequest;
import com.hunny.uneasySolver.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class MemberApiController {

    private final MemberService memberService;
    private final JwtUtils jwtUtils;

    public MemberApiController(MemberService memberService, JwtUtils jwtUtils) {
        this.memberService = memberService;
        this.jwtUtils = jwtUtils;
    }

    @Data
    @AllArgsConstructor
    public class LoginRequest{
        private String email;
        private String password;
    }

    @PostMapping("/api/members/login")
    public String login(@RequestBody @Valid MemberLoginRequest request){
        Member member = memberService.login(request);

        MemberLoginResponse response = new MemberLoginResponse(member.getId());

        String token = jwtUtils.generateToken(member.getId());

//        return response;
        return token;
    }

    @PostMapping("/api/members/register")
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
