package com.hunny.uneasySolver.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Member {

    @GeneratedValue @Id
    @Column(name = "member_id")
    private Long Id;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    private String password;

    @NotNull
    @Column(unique = true)
    private String nickname;

    @NotNull
    private String region;

    private Integer age;

    private Boolean sex;

    private String phoneNumber;

    private Integer point;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @OneToMany(mappedBy = "writer")
    private List<Comment> comments = new ArrayList<Comment>();

    @OneToMany(mappedBy = "author")
    private List<Post> posts = new ArrayList<Post>();

    @OneToMany(mappedBy = "problemer")
    private List<ChatRoom> uneasyRoom = new ArrayList<ChatRoom>();

    @OneToMany(mappedBy = "solver")
    private List<ChatRoom> solveRoom = new ArrayList<ChatRoom>();

    //=== 생성 메서드 ====//
    public static Member createMember(String email, String password, String nickname, String region, Job job, Integer age, Boolean sex, String phoneNumber, Integer point) {
        Member member = new Member();
        member.email = email;
        member.password = password;
        member.nickname = nickname;
        member.region = region;
        member.job = job;
        member.age = age;
        member.sex = sex;
        member.phoneNumber = phoneNumber;
        member.point = point;

        return member;
    }

    //=== set 메서드 ===//
    public void setComment(Comment comment){
        this.comments.add(comment);
    }
    public void setUneasyRoom(ChatRoom room){ this.uneasyRoom.add(room); }
    public void setSolveRoom(ChatRoom room){ this.solveRoom.add(room); }

}
