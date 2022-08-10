package com.hunny.uneasySolver.service;

import com.hunny.uneasySolver.domain.Comment;
import com.hunny.uneasySolver.domain.Member;
import com.hunny.uneasySolver.domain.Post;
import com.hunny.uneasySolver.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Long writeComment(Member writer, Post post, String content){
        Comment comment = Comment.createComment(writer, post, content);
        commentRepository.save(comment);

        return comment.getId();
    }

    public Optional<Comment> findById(Long id){
        return commentRepository.findById(id);
    }

    public List<Comment> findByMember(Member member){
        return commentRepository.findByMember(member);
    }

    public List<Comment> findByPost(Post post){
        return commentRepository.findByPost(post);
    }

}
