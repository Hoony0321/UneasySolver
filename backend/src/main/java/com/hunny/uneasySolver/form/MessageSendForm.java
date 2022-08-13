package com.hunny.uneasySolver.form;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MessageSendForm {

    private Long roomId;
    private Long senderId;
    private String content;
}
