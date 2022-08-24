package com.hunny.uneasySolver.api;

import com.hunny.uneasySolver.dto.PostCreateRequest;
import com.hunny.uneasySolver.form.PostCreateForm;
import com.hunny.uneasySolver.repository.TargetRepository;
import com.hunny.uneasySolver.security.JwtUtils;
import com.hunny.uneasySolver.service.MemberService;
import com.hunny.uneasySolver.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;
    private final MemberService memberService;
    private final TargetRepository targetRepository;
    private final JwtUtils jwtUtils;

    @GetMapping("/api/posts")
    public String postList(Model model){
        model.addAttribute("posts", postService.findAll());
        return "posts/listPage";
    }


    @PostMapping("/api/auth/posts/create")
    public String createPost(HttpServletRequest request, @RequestBody @Valid PostCreateRequest post){
        log.info(request.getHeader("auth"));
        if(jwtUtils.validationToken(request.getHeader("auth"))){
            postService.createPostByRequest(post);
        }
        return "redirect:/";
    }
}
