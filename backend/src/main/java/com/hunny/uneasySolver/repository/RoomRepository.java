package com.hunny.uneasySolver.repository;

import com.hunny.uneasySolver.domain.ChatRoom;
import com.hunny.uneasySolver.domain.Member;
import com.hunny.uneasySolver.domain.Post;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class RoomRepository {

    private final EntityManager em;

    public RoomRepository(EntityManager em) {
        this.em = em;
    }

    public Long save(ChatRoom room){
        em.persist(room);
        return room.getId();
    }

    public Optional<ChatRoom> findById(Long id){
        ChatRoom room = em.find(ChatRoom.class, id);
        return Optional.ofNullable(room);
    }

    public List<ChatRoom> findByPost(Post post){
        List<ChatRoom> rooms = em.createQuery("select r from ChatRoom r where r.post = :post", ChatRoom.class)
                .setParameter("post", post)
                .getResultList();
        return rooms;
    }

    public List<ChatRoom> findByProblemer(Member problemer){
        List<ChatRoom> rooms = em.createQuery("select r from ChatRoom r where r.problemer = :problemer", ChatRoom.class)
                .setParameter("problemer", problemer)
                .getResultList();
        return rooms;
    }

    public List<ChatRoom> findBySolver(Member solver){
        List<ChatRoom> rooms = em.createQuery("select r from ChatRoom r where r.solver = :solver", ChatRoom.class)
                .setParameter("solver", solver)
                .getResultList();
        return rooms;
    }
}
