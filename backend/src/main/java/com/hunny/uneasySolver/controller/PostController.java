package com.hunny.uneasySolver.controller;

import com.hunny.uneasySolver.form.PostCreateForm;
import com.hunny.uneasySolver.repository.TargetRepository;
import com.hunny.uneasySolver.service.MemberService;
import com.hunny.uneasySolver.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostController {

    private final PostService postService;
    private final MemberService memberService;
    private final TargetRepository targetRepository;

    public PostController(PostService postService, MemberService memberService, TargetRepository targetRepository) {
        this.postService = postService;
        this.memberService = memberService;
        this.targetRepository = targetRepository;
    }

    @GetMapping("posts")
    public String postList(Model model){
        model.addAttribute("posts", postService.findAll());
        return "posts/listPage";
    }

    @GetMapping("posts/new")
    public String createPostForm(Model model){
        model.addAttribute("form", new PostCreateForm());
        model.addAttribute("members", memberService.findAll());
        model.addAttribute("targets", targetRepository.findAll());
        return "posts/createPage";
    }

    @PostMapping("posts/new")
    public String createPost(PostCreateForm form){
        postService.createPostByForm(form);

        return "redirect:/posts";
    }


}
