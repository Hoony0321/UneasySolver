package com.hunny.uneasySolver.domain;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Post extends BaseTimeEntity{

    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_id")
    private Target target;

    @NotNull
    private String title;

    @NotNull
    private Integer uneasyIdx; //불편지수

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @NotNull
    private PostContent postContent;

    private String address;

    @NotNull
    private Boolean visible = false; //게시 여부 // default = false;

    @NotNull
    private Integer hits = 0; //조회수

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<Comment>();


    // 연관 관계 편의 메서드 //
    public void setAuthor(Member member){
        this.author = member;
        member.getPosts().add(this);
    }



    // 생성 메서드 //
    public static Post createPost(String title, Integer uneasyIdx, String address){
        Post post = new Post();

        post.title = title;
        post.uneasyIdx = uneasyIdx;
        post.address = address;

        return post;
    }

    // set 메서드 //
    public void setComment(Comment comment){
        this.comments.add(comment);
    }

    public void setTarget(Target target){
        this.target = target;
    }

    public void setPostContent(PostContent postContent){
        this.postContent = postContent;
    }


    // 비즈니스 로직 메서드 //
    public void setVisible(){
        this.visible = true;
    }

    public void setUnVisible(){
        this.visible = false;
    }


}
