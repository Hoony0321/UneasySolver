package com.hunny.uneasySolver.form;

import com.hunny.uneasySolver.domain.Member;
import com.hunny.uneasySolver.domain.Target;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostCreateForm {

    private Long memberId;
    private Long targetId;

    private String content;
    private String title;
    private Integer uneasyIdx;
    private String address;


}
