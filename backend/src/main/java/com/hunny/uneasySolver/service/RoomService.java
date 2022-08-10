package com.hunny.uneasySolver.service;

import com.hunny.uneasySolver.domain.ChatRoom;
import com.hunny.uneasySolver.domain.Member;
import com.hunny.uneasySolver.domain.Post;
import com.hunny.uneasySolver.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Long createRoom(Post post, Member problemer, Member solver){
        ChatRoom room = ChatRoom.createRoom(post, problemer, solver);
        roomRepository.save(room);

        return room.getId();
    }

    public ChatRoom findById(Long id){
        return roomRepository.findById(id).get();
    }

    public List<ChatRoom> findByPost(Post post){
        return roomRepository.findByPost(post);
    }

    public List<ChatRoom> findByProblemer(Member problemer){
        return roomRepository.findByProblemer(problemer);
    }

    public List<ChatRoom> findBySolver(Member solver){
        return roomRepository.findBySolver(solver);
    }


}
