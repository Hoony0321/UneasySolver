package com.hunny.uneasySolver.repository;

import com.hunny.uneasySolver.domain.Member;
import com.hunny.uneasySolver.domain.Post;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class PostRepository {

    private  final EntityManager em;

    public PostRepository(EntityManager em) {
        this.em = em;
    }

    public Long save(Post post){
        em.persist(post);
        return post.getId();
    }

    public Optional<Post> findById(Long id){
        Post post = em.find(Post.class, id);
        return Optional.ofNullable(post);
    }

    public List<Post> findAll(){
        List<Post> posts = em.createQuery("select p from Post p", Post.class).getResultList();
        return posts;
    }
}
