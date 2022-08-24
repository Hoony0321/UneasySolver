package com.hunny.uneasySolver.service;

import com.hunny.uneasySolver.domain.Member;
import com.hunny.uneasySolver.domain.Post;
import com.hunny.uneasySolver.domain.PostContent;
import com.hunny.uneasySolver.domain.Target;
import com.hunny.uneasySolver.dto.PostCreateRequest;
import com.hunny.uneasySolver.form.PostCreateForm;
import com.hunny.uneasySolver.repository.PostRepository;
import com.hunny.uneasySolver.repository.TargetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final MemberService memberService;
    private final TargetRepository targetRepository;

    public PostService(PostRepository postRepository, MemberService memberService, TargetRepository targetRepository) {
        this.postRepository = postRepository;
        this.memberService = memberService;
        this.targetRepository = targetRepository;
    }


    public void createPostByForm(PostCreateForm form) {
        PostContent content = PostContent.createContent(form.getContent());
        Member member = memberService.findById(form.getMemberId()).get();
        Target target = targetRepository.findById(form.getTargetId()).get();

        this.publishPost(member, target, content, form.getTitle(), form.getUneasyIdx(), form.getAddress());
    }

    public void createPostByRequest(PostCreateRequest request){
        Member member = memberService.findById(request.getId()).get();
        System.out.println(request.getTarget());
        Target target = targetRepository.findById(request.getTarget()).get();
        PostContent content = PostContent.createContent(request.getContent());

        this.publishPost(member, target, content, request.getTitle(), request.getUneasyIdx(), request.getAddress());
    }

    public Long publishPost(Member author, Target target, PostContent postContent,String title, Integer uneasyIdx, String address){
        Post post = Post.createPost(title, uneasyIdx, address);
        post.setAuthor(author);
        post.setTarget(target);
        post.setPostContent(postContent);
        return postRepository.save(post);
    }

    public Optional<Post> findById(Long id){
        return postRepository.findById(id);
    }

    public List<Post> findAll(){
        return postRepository.findAll();
    }

}
