package com.hunny.uneasySolver.repository;

import com.hunny.uneasySolver.domain.Message;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class MessageRepository {

    private final EntityManager em;

    public MessageRepository(EntityManager em) {
        this.em = em;
    }

    public Long save(Message message){
        em.persist(message);
        return message.getId();
    }

    public Optional<Message> findById(Long id){
        Message message = em.find(Message.class, id);
        return Optional.ofNullable(message);
    }

    public List<Message> findAll(){
        List<Message> messages = em.createQuery("select m from Message m", Message.class).getResultList();
        return messages;
    }
}
