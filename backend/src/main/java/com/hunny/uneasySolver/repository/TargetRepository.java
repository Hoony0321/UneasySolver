package com.hunny.uneasySolver.repository;

import com.hunny.uneasySolver.domain.Post;
import com.hunny.uneasySolver.domain.Target;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class TargetRepository {

    private final EntityManager em;

    public TargetRepository(EntityManager em) {
        this.em = em;
    }

    public Long save(Target target){
        em.persist(target);
        return target.getId();
    }

    public Optional<Target> findById(Long id){
        Target target = em.find(Target.class, id);
        return Optional.ofNullable(target);
    }

    public Optional<Target> findByName(String name){
        Target target = em.createQuery("select t from Target t where t.name = :name", Target.class)
                .setParameter("name", name)
                .getSingleResult();

        return Optional.ofNullable(target);
    }

    public List<Target> findAll(){
        List<Target> targets = em.createQuery("select t from Target t", Target.class).getResultList();
        return targets;
    }
}
