package com.hunny.uneasySolver.service;

import com.hunny.uneasySolver.domain.Member;
import com.hunny.uneasySolver.domain.Post;
import com.hunny.uneasySolver.domain.PostContent;
import com.hunny.uneasySolver.domain.Target;
import com.hunny.uneasySolver.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Long publishPost(Member author, Target target, PostContent postContent,String title, Integer uneasyIdx, String region){
        Post post = Post.createPost(title, uneasyIdx, region);
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
