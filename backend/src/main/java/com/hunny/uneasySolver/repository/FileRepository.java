package com.hunny.uneasySolver.repository;

import com.hunny.uneasySolver.domain.File;
import com.hunny.uneasySolver.domain.Post;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class FileRepository {

    private final EntityManager em;

    public FileRepository(EntityManager em) {
        this.em = em;
    }

    public Long save(File file){
        em.persist(file);
        return file.getId();
    }

    public Optional<File> findById(Long id){
        File file = em.find(File.class, id);
        return Optional.ofNullable(file);
    }

    public List<File> findAll(){
        List<File> files = em.createQuery("select f from File f", File.class).getResultList();
        return files;
    }

}
