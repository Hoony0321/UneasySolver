package com.hunny.uneasySolver.controller;

import com.hunny.uneasySolver.domain.ChatRoom;
import com.hunny.uneasySolver.domain.Member;
import com.hunny.uneasySolver.domain.Message;
import com.hunny.uneasySolver.domain.Post;
import com.hunny.uneasySolver.form.ChatRoomCreateForm;
import com.hunny.uneasySolver.form.ChatRoomMoveForm;
import com.hunny.uneasySolver.form.MessageSendForm;
import com.hunny.uneasySolver.repository.MessageRepository;
import com.hunny.uneasySolver.service.MemberService;
import com.hunny.uneasySolver.service.PostService;
import com.hunny.uneasySolver.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ChatRoomController {

    private final RoomService roomService;
    private final PostService postService;
    private final MemberService memberService;

    private final MessageRepository messageRepository;


    public ChatRoomController(RoomService roomService, PostService postService, MemberService memberService, MessageRepository messageRepository) {
        this.roomService = roomService;
        this.postService = postService;
        this.memberService = memberService;
        this.messageRepository = messageRepository;
    }

    @GetMapping("chatrooms")
    public String RoomList(Model model){
        List<ChatRoom> chatRooms = roomService.findAll();
        model.addAttribute("rooms", chatRooms);
        model.addAttribute("form", new ChatRoomMoveForm());
        return "chatrooms/listPage";
    }

    @PostMapping("chatrooms")
    public ModelAndView MoveRoom(ChatRoomMoveForm form, Model model){
        ChatRoom room = roomService.findById(form.getRoomId());

        ModelAndView MAV = new ModelAndView();
        MAV.addObject("roomId", room.getId());
        MAV.setViewName("redirect:/chatrooms/chat");
        return MAV;
    }

    @GetMapping("chatrooms/chat")
    public String showChatRoom(@ModelAttribute("roomId") Long roomId, Model model){

        ChatRoom room = roomService.findById(roomId);

        List<Message> messages = messageRepository.findByRoom(room);
        model.addAttribute("room_exac_id", roomId);
        model.addAttribute("messages", messages);
        model.addAttribute("room", room);
        model.addAttribute("form", new MessageSendForm());

        return "chatrooms/chatPage";
    }

    @PostMapping("chatrooms/chatPage")
    public ModelAndView SendMessage(MessageSendForm form){

        System.out.println("HERE : " + form.getRoomId());
        System.out.println("HERE : " + form.getSenderId());
        System.out.println("HERE : " + form.getContent());

        ChatRoom room = roomService.findById(form.getRoomId());
        Member member = memberService.findById(form.getSenderId()).get();

        Message message = Message.createMessage(room, member, form.getContent());
        messageRepository.save(message);

        ModelAndView MAV = new ModelAndView();
        MAV.addObject("roomId", room.getId());
        MAV.setViewName("redirect:/chatrooms/chat");
        System.out.println("ROOM 실행");
        return MAV;
    }

    @GetMapping("chatrooms/new")
    public String createRoomForm(Model model){
        model.addAttribute("posts", postService.findAll());
        model.addAttribute("members", memberService.findAll());
        model.addAttribute("form", new ChatRoomCreateForm());
        return "chatrooms/createPage";
    }

    @PostMapping("chatrooms/new")
    public String createRoom(ChatRoomCreateForm form){
        Member member = memberService.findById(form.getSolverId()).get();
        Post post = postService.findById(form.getPostId()).get();
        roomService.createRoom(post,member);
        return "redirect:/chatrooms";
    }
}
