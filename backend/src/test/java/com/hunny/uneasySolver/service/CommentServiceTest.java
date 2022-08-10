package com.hunny.uneasySolver.service;

import com.hunny.uneasySolver.domain.*;
import com.hunny.uneasySolver.repository.ContentRepository;
import com.hunny.uneasySolver.repository.MemberRepository;
import com.hunny.uneasySolver.repository.TargetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class CommentServiceTest {

    @Autowired private CommentService commentService;
    @Autowired private PostService postService;

    @Autowired private MemberRepository memberRepository;
    @Autowired private ContentRepository contentRepository;
    @Autowired private TargetRepository targetRepository;

    @Test
    void writeComment() {
        //given
        File file = File.createFile("testPath", Long.parseLong("100"), Long.parseLong("100"));
        PostContent content = PostContent.createContent("testContent", file);

        //target 저장
        Target target = Target.createTarget("testTarget");
        targetRepository.save(target);

        //Member 생성
        Member member = Member.createMember("testEmail@gmail.com", "password", "nickname", "region", null, null, null, null, null);
        memberRepository.save(member);

        //Post 생성
        Long saveId = postService.publishPost(member, target, content, "title", 3, "region");
        Post post = postService.findById(saveId).get();

        //when
        Long id = commentService.writeComment(member, post, "THIS IS TEST COMMENT");


        //then
        Comment comment = commentService.findById(id).get();

        assertThat(comment.getWriter()).isEqualTo(member);
        assertThat(comment.getPost()).isEqualTo(post);
        assertThat(comment.getContent()).isEqualTo("THIS IS TEST COMMENT");
    }

    @Test
    void findByMember() {
        //given
        File file = File.createFile("testPath", Long.parseLong("100"), Long.parseLong("100"));
        PostContent content = PostContent.createContent("testContent", file);

        //target 저장
        Target target = Target.createTarget("testTarget");
        targetRepository.save(target);

        //Member 생성
        Member member = Member.createMember("testEmail@gmail.com", "password", "nickname", "region", null, null, null, null, null);
        memberRepository.save(member);

        //Post 생성
        Long saveId = postService.publishPost(member, target, content, "title", 3, "region");
        Post post = postService.findById(saveId).get();

        //when
        commentService.writeComment(member, post, "THIS IS TEST COMMENT1");
        commentService.writeComment(member, post, "THIS IS TEST COMMENT2");
        commentService.writeComment(member, post, "THIS IS TEST COMMENT3");
        commentService.writeComment(member, post, "THIS IS TEST COMMENT4");



        //then
        List<Comment> result = commentService.findByMember(member);

        assertThat(result.size()).isEqualTo(4);
    }

    @Test
    void findByPost() {
        //given
        File file = File.createFile("testPath", Long.parseLong("100"), Long.parseLong("100"));
        PostContent content = PostContent.createContent("testContent", file);

        //target 저장
        Target target = Target.createTarget("testTarget");
        targetRepository.save(target);


        //Member 생성
        Member member1 = Member.createMember("testEmail1@gmail.com", "password", "nickname1", "region", null, null, null, null, null);
        memberRepository.save(member1);

        //Member 생성
        Member member2 = Member.createMember("testEmail2@gmail.com", "password", "nickname2", "region", null, null, null, null, null);
        memberRepository.save(member2);

        //Member 생성
        Member member3 = Member.createMember("testEmail3@gmail.com", "password", "nickname3", "region", null, null, null, null, null);
        memberRepository.save(member3);

        //Post 생성
        Long saveId = postService.publishPost(member3, target, content, "title", 3, "region");
        Post post = postService.findById(saveId).get();

        //when
        commentService.writeComment(member1, post, "THIS IS TEST COMMENT2");
        commentService.writeComment(member2, post, "THIS IS TEST COMMENT3");
        commentService.writeComment(member3, post, "THIS IS TEST COMMENT4");



        //then
        List<Comment> comments = commentService.findByPost(post);

        assertThat(comments.size()).isEqualTo(3);
    }
}