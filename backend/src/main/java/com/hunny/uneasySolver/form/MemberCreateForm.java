package com.hunny.uneasySolver.form;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberCreateForm {

    // * 필수 사항
    private String email;
    private String password;
    private String nickname;


    // * 선택 사항
    private Integer jobCode;

    private String address;

    private Integer age;

    private String sex;

    private String phoneNumber;


}
