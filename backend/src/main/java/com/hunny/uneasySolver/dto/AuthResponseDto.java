package com.hunny.uneasySolver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponseDto<T> {
    private Boolean success;
    private String msg;
    private T data;
}
