package com.hunny.uneasySolver.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommonResponse<T> {

    private Boolean success;
    private int code;
    private String msg;
    private T data;
}
