package com.hunny.uneasySolver.repository;

import com.hunny.uneasySolver.domain.Comment;
import com.hunny.uneasySolver.domain.Member;
import com.hunny.uneasySolver.domain.Post;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepository {

    private final EntityManager em;

    public CommentRepository(EntityManager em) {
        this.em = em;
    }

    public Long save(Comment comment){
        em.persist(comment);
        return comment.getId();
    }

    public Optional<Comment> findById(Long id){
        Comment comment = em.find(Comment.class, id);
        return Optional.ofNullable(comment);
    }

    public List<Comment> findByMember(Member member){
        List<Comment> comments = em.createQuery("select c from Comment c where c.writer = :member", Comment.class)
                .setParameter("member", member)
                .getResultList();
        return comments;
    }

    public List<Comment> findByPost(Post post){
        List<Comment> comments = em.createQuery("select c from Comment c where c.post = :post", Comment.class)
                .setParameter("post", post)
                .getResultList();
        return comments;
    }
}
