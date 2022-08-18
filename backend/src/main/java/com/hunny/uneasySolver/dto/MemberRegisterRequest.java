package com.hunny.uneasySolver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class MemberRegisterRequest {

    private String email;
    private String password;
    private String nickname;
    private String address;

    private Boolean sex;
    private Integer age;
    private String job;
    private String phoneNumber;

    public MemberRegisterRequest(String email, String password, String nickname, String address, String sex, String age, String job, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.address = address;

        this.sex = sex.equals("") ? null : sex.equals("남");
        this.age = age.equals("") ? null : Integer.parseInt(age);
        this.job = job.equals("") ? null : job;
        this.phoneNumber = phoneNumber.equals("") ? null : phoneNumber;
    }
}
