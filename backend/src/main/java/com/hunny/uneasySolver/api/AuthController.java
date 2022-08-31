package com.hunny.uneasySolver.api;

import com.hunny.uneasySolver.dto.MemberLoginRequest;
import com.hunny.uneasySolver.security.TokenDto;
import com.hunny.uneasySolver.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/members/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberLoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }
}
