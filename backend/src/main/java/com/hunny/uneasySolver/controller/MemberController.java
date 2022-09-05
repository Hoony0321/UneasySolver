package com.hunny.uneasySolver.controller;

import com.hunny.uneasySolver.domain.Member;
import com.hunny.uneasySolver.form.MemberCreateForm;
import com.hunny.uneasySolver.form.MemberLoginForm;
import com.hunny.uneasySolver.service.MemberService;
import com.hunny.uneasySolver.session.LoginSessionManager;
import com.hunny.uneasySolver.dto.MemberDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;
    private final LoginSessionManager sessionManager;

    public MemberController(MemberService memberService, LoginSessionManager sessionManager) {
        this.memberService = memberService;
        this.sessionManager = sessionManager;
    }

    @GetMapping("members/new")
    public String signUp(Model model){
        model.addAttribute("memberForm", new MemberCreateForm());
        return "members/signUpPage";
    }

    @PostMapping("members/new")
    public String joinMember(MemberCreateForm memberForm){
        Member member = Member.createMemberByForm(memberForm);
        memberService.join(member);
        return "redirect:/members";
    }

    @GetMapping("members")
    public String memberList(Model model){
        List<Member> members = memberService.findAll();
        model.addAttribute("members", members);
        return "/members/listPage";
    }

//    @GetMapping("members/login")
//    public String login(Model model){
//        model.addAttribute("form", new MemberLoginForm());
//        return "/members/loginPage";
//    }

//    @PostMapping("members/login")
//    public void handleLogin(HttpServletResponse response, MemberLoginForm form){
//        Member result = memberService.login(form);
//        MemberDTO info = MemberDTO.createMemberCookie(result);
//
//        sessionManager.createSession(info,response);
//        try {
//            response.sendRedirect("/");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

}
