package com.hunny.uneasySolver.repository;

import com.hunny.uneasySolver.domain.Post;
import com.hunny.uneasySolver.domain.PostContent;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class ContentRepository {

    private final EntityManager em;


    public ContentRepository(EntityManager em) {
        this.em = em;
    }

    public Long save(PostContent content){
        em.persist(content);
        return content.getId();
    }

    public Optional<PostContent> findById(Long id){
        PostContent content = em.find(PostContent.class, id);
        return Optional.ofNullable(content);
    }

    public List<PostContent> findAll(){
        List<PostContent> contents = em.createQuery("select p from PostContent p", PostContent.class).getResultList();
        return contents;
    }
}
