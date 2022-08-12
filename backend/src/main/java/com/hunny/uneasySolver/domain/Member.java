package com.hunny.uneasySolver.domain;

import com.hunny.uneasySolver.form.MemberCreateForm;
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
    private String address;

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
        member.address = region;
        member.job = job;
        member.age = age;
        member.sex = sex;
        member.phoneNumber = phoneNumber;
        member.point = point;

        return member;
    }

    public static Member createMemberByForm(MemberCreateForm form){
        Member member = new Member();
        member.email = form.getEmail();
        member.password = form.getPassword();
        member.nickname = form.getNickname();
        member.address = form.getAddress();
        member.age = form.getAge();
        member.sex = form.getSex().equals("남") ? true : false;
        member.phoneNumber = form.getPhoneNumber();

        return member;
    }

    //TODO 배포 시 삭제할 것
    public static Member createDummyMember(Integer num){
        Member member = new Member();
        member.email = "dummyEmail" + num;
        member.password = "dummyPW" + num;
        member.nickname = "dummyNickname" + num;
        member.address = "dummyAddress" + num;
        member.age = num;
        member.sex = false;
        member.phoneNumber = "010-0000-000" + num;

        return member;
    }

    //=== set 메서드 ===//
    public void setComment(Comment comment){
        this.comments.add(comment);
    }
    public void setUneasyRoom(ChatRoom room){ this.uneasyRoom.add(room); }
    public void setSolveRoom(ChatRoom room){ this.solveRoom.add(room); }

}
