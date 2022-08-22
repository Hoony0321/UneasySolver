package com.hunny.uneasySolver.repository;

import com.hunny.uneasySolver.domain.Job;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JobRepository {
    
    private final EntityManager em;

    public Long save(Job Job){
        em.persist(Job);
        return Job.getId();
    }

    public Optional<Job> findById(Long id){
        Job Job = em.find(Job.class, id);
        return Optional.ofNullable(Job);
    }

    public Optional<Job> findByName(String name){
        Job Job = em.createQuery("select t from Job t where t.name = :name", Job.class)
                .setParameter("name", name)
                .getSingleResult();

        return Optional.ofNullable(Job);
    }

    public List<Job> findAll(){
        List<Job> Jobs = em.createQuery("select t from Job t", Job.class).getResultList();
        return Jobs;
    }
}
