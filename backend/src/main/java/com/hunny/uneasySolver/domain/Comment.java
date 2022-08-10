package com.hunny.uneasySolver.domain;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
public class Comment extends BaseTimeEntity{

    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @NotNull
    private Member writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    @NotNull
    private Post post;

    @NotNull
    private String content; //댓글 내용

    @NotNull
    private Integer likeNum = 0; //좋아요 수

    // 연관관계 편의 메서드 //
    public void setWriter(Member writer) {
        this.writer = writer;
        writer.setComment(this);
    }

    public void setPost(Post post){
        this.post = post;
        post.setComment(this);
    }

    // 생성 메서드 //
    public static Comment createComment(Member writer, Post post, String content){
        Comment comment = new Comment();

        comment.setWriter(writer);
        comment.setPost(post);
        comment.content = content;

        return comment;
    }

}
