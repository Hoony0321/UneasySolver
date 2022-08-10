package com.hunny.uneasySolver.service;

import com.hunny.uneasySolver.domain.*;
import com.hunny.uneasySolver.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class PostServiceTest {

    @Autowired private PostService postService;

    @Autowired private ContentRepository contentRepository;
    @Autowired private MemberRepository memberRepository;
    @Autowired private TargetRepository targetRepository;
    @Autowired private FileRepository fileRepository;


    @Test
    public void POST저장() throws Exception{
        //given
        File file = File.createFile("testPath", Long.parseLong("100"), Long.parseLong("100"));
        PostContent content = PostContent.createContent("testContent", file);

        //target 저장
        Target target = Target.createTarget("testTarget");
        targetRepository.save(target);

        //Member 생성
        Member member = Member.createMember("testEmail@gmail.com", "password", "nickname", "region", null, null, null, null, null);
        memberRepository.save(member);

        //when
        Long saveId = postService.publishPost(member, target, content, "title", 3, "region");
        Post post = postService.findById(saveId).get();

        //then
        assertThat(post.getPostContent()).isEqualTo(content); // postContent 검증
        assertThat(post.getTarget()).isEqualTo(target); //target 검증
        assertThat(post.getAuthor()).isEqualTo(member); //member 검증
        assertThat(post.getPostContent().getFiles().get(0)).isEqualTo(file); //file 검증

        assertThat(post.getTitle()).isEqualTo("title"); //title 검증
        assertThat(post.getUneasyIdx()).isEqualTo(3); //uneasyIdx 검증
     }

}