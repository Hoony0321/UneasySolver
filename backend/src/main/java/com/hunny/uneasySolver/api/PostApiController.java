package com.hunny.uneasySolver.api;

import com.hunny.uneasySolver.domain.Post;
import com.hunny.uneasySolver.dto.PostCreateRequest;
import com.hunny.uneasySolver.dto.PostListResponse;
import com.hunny.uneasySolver.repository.TargetRepository;
import com.hunny.uneasySolver.security.JwtProvider;
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
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;

    @GetMapping("/api/posts")
    public List<PostListResponse> postList(Model model){
        List<PostListResponse> result = new ArrayList<PostListResponse>();

        List<Post> posts = postService.findAll();

        for(Post post : posts){
            PostListResponse postInfo = new PostListResponse(post);

            result.add(postInfo);
        }

        return result;
    }


    @PostMapping("/api/auth/posts/create")
    public String createPost(HttpServletRequest request, @RequestBody @Valid PostCreateRequest post){
        postService.createPostByRequest(post);
        return "redirect:/";
    }


}
