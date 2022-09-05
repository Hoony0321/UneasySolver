package com.hunny.uneasySolver.dto;

import lombok.Data;

@Data
public class AuthRequestDto {
    private Long id;
    private String accessToken;
    private String refreshToken;
}
