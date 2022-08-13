package com.hunny.uneasySolver.service;

import com.hunny.uneasySolver.domain.ChatRoom;
import com.hunny.uneasySolver.domain.Member;
import com.hunny.uneasySolver.domain.Message;
import com.hunny.uneasySolver.domain.Post;
import com.hunny.uneasySolver.repository.MessageRepository;
import com.hunny.uneasySolver.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoomService {

    private final RoomRepository roomRepository;
    private final MessageRepository messageRepository;

    public RoomService(RoomRepository roomRepository, MessageRepository messageRepository) {
        this.roomRepository = roomRepository;
        this.messageRepository = messageRepository;
    }

    public List<ChatRoom> findAll(){
        return roomRepository.findAll();
    }

    public Long createRoom(Post post, Member solver){
        ChatRoom room = ChatRoom.createRoom(post, solver);
        roomRepository.save(room);

        return room.getId();
    }

    public Long createMessage(Message message){
        return messageRepository.save(message);
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
