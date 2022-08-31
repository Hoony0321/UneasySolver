package com.hunny.uneasySolver.security;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class TokenDto {

    private String grantType;
    private String accessToken;

    @NotNull
    private String refreshToken;

    @Builder
    TokenDto(String grantType, String accessToken, String refreshToken){
        this.grantType = grantType;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
