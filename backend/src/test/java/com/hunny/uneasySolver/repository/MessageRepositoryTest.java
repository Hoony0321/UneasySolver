package com.hunny.uneasySolver.repository;

import com.hunny.uneasySolver.domain.ChatRoom;
import com.hunny.uneasySolver.domain.Member;
import com.hunny.uneasySolver.domain.Message;
import com.hunny.uneasySolver.domain.Post;
import com.hunny.uneasySolver.service.MemberService;
import com.hunny.uneasySolver.service.PostService;
import com.hunny.uneasySolver.service.RoomService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MessageRepositoryTest {

    @Autowired private MessageRepository messageRepository;
    @Autowired private RoomService roomService;
    @Autowired private PostService postService;
    @Autowired private MemberService memberService;


    @Test
    public void 메세지_생성() throws Exception {
        //given
        ChatRoom chatRoom = roomService.findAll().get(0);
        Post post = chatRoom.getPost();
        Member solver = chatRoom.getSolver();
        Member problemer = chatRoom.getProblemer();

        //when
        Message message = Message.createMessage(chatRoom, solver, "THIS IS MESSAGE SAND BY SOLVER");
        messageRepository.save(message);

        //then
        assertThat(messageRepository.findByRoom(chatRoom).get(0).getContent()).isEqualTo(message.getContent());
    }


    @Test
    public void 메세지_다중생성() throws Exception {
        //given
        ChatRoom chatRoom = roomService.findAll().get(0);
        Post post = chatRoom.getPost();
        Member solver = chatRoom.getSolver();
        Member problemer = chatRoom.getProblemer();

        //when
        Message message1 = Message.createMessage(chatRoom, solver, "THIS IS MESSAGE SAND BY SOLVER");
        messageRepository.save(message1);

        Message message2 = Message.createMessage(chatRoom, problemer, "THIS IS MESSAGE SAND BY PROBLEMER");
        messageRepository.save(message2);

        Message message3 = Message.createMessage(chatRoom, problemer, "THIS IS MESSAGE SAND BY PROBLEMER");
        messageRepository.save(message3);

        //then
        List<Message> messages = messageRepository.findByRoom(chatRoom);

        assertThat(messages.size()).isEqualTo(3);

        int count = 0;
        for (Message message : messages) {
            if(message.getSender() == problemer) count += 1;
        }
        assertThat(count).isEqualTo(2);


    }


}