package com.hunny.uneasySolver.dto;

import com.hunny.uneasySolver.domain.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PostListResponse {

    private Long id;

    private String author;

    private LocalDate date;

    private String target;

    private String title;

    private Integer uneasyIdx; //불편지수

    private String content;

    private Integer hits = 10;

    private Integer comments = 5;

    private String[] tags = {"tag1", "tag2", "tag3"};

    public PostListResponse(){}

    public PostListResponse(Post post){
        this.id = post.getId();
        this.setAuthor(post.getAuthor().getNickname());
        this.setTarget(post.getTarget().getName());
        this.setTitle(post.getTitle());
        this.setUneasyIdx(post.getUneasyIdx());
        this.content = post.getPostContent().getContent();
        this.date = post.getCreatedDate().toLocalDate();
    }



}
