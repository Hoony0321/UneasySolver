package com.hunny.uneasySolver.service;

import com.hunny.uneasySolver.domain.*;
import com.hunny.uneasySolver.repository.MemberRepository;
import com.hunny.uneasySolver.repository.MessageRepository;
import com.hunny.uneasySolver.repository.TargetRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class RoomServiceTest {

    @Autowired private RoomService roomService;
    @Autowired private PostService postService;

    @Autowired private MemberRepository memberRepositoryp;
    @Autowired private TargetRepository targetRepository;
    @Autowired private MessageRepository messageRepository;

    @Test
    public void 채팅방_생성() throws Exception{
        //given
        Member problemer = Member.createMember("testEmail1@gmail.com", "password", "nickname", "region", null, null, null, null, null);
        Member solver = Member.createMember("testEmail2@gmail.com", "password", "nickname", "region", null, null, null, null, null);

        memberRepositoryp.save(problemer);
        memberRepositoryp.save(solver);

        Target target = Target.createTarget("target");
        targetRepository.save(target);
        PostContent postContent = PostContent.createContent("content");

        Long saveId = postService.publishPost(problemer, target, postContent, "title", 3, "region");
        Post post = postService.findById(saveId).get();

        //when
        Long id = roomService.createRoom(post, solver);

        //then
        ChatRoom room = roomService.findById(id);

        assertThat(room.getPost()).isEqualTo(post);
        assertThat(room.getProblemer()).isEqualTo(problemer);
        assertThat(room.getSolver()).isEqualTo(solver);
    }

    @Test
    public void 채팅방_메세지_생성() throws Exception{
        //given
        Member problemer = Member.createMember("testEmail1@gmail.com", "password", "nickname", "region", null, null, null, null, null);
        Member solver = Member.createMember("testEmail2@gmail.com", "password", "nickname", "region", null, null, null, null, null);

        memberRepositoryp.save(problemer);
        memberRepositoryp.save(solver);

        Target target = Target.createTarget("target");
        targetRepository.save(target);
        PostContent postContent = PostContent.createContent("content");

        Long saveId = postService.publishPost(problemer, target, postContent, "title", 3, "region");
        Post post = postService.findById(saveId).get();

        Long id = roomService.createRoom(post, solver);
        ChatRoom room = roomService.findById(id);

        //when
        Message message1 = Message.createMessage(room, solver, "SOLVER");
        Message message2 = Message.createMessage(room, problemer, "PROBLEMER");
        Long id1 = roomService.createMessage(message1);
        Long id2 = roomService.createMessage(message2);

        //then
        Message findMsg1 = messageRepository.findById(id1).get();
        Message findMsg2 = messageRepository.findById(id2).get();

        assertThat(findMsg1.getContent()).isEqualTo("SOLVER");
        assertThat(findMsg2.getContent()).isEqualTo("PROBLEMER");
    }

}