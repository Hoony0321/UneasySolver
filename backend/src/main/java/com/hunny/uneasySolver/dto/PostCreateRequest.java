package com.hunny.uneasySolver.dto;

import lombok.Data;

@Data
public class PostCreateRequest {

    private Long id;

    private Long target;
    private Integer uneasyIdx;

    private String title;
    private String content;
    private String address;
    private String file;
    private Integer point;
}
