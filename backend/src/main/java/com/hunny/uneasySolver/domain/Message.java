package com.hunny.uneasySolver.domain;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
public class Message extends BaseTimeEntity{

    @Id @GeneratedValue
    @Column(name = "message_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private ChatRoom chatRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private Member sender;

    @NotNull
    private String content;

    // 생성 메서드 //
    public static Message createMessage(ChatRoom chatRoom, Member sender, String content){
        Message message = new Message();
        message.chatRoom = chatRoom;
        message.sender = sender;
        message.content = content;

        return message;
    }
}
