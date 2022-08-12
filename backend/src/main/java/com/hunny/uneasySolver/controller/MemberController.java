package com.hunny.uneasySolver.controller;

import com.hunny.uneasySolver.domain.Member;
import com.hunny.uneasySolver.form.MemberCreateForm;
import com.hunny.uneasySolver.form.MemberLoginForm;
import com.hunny.uneasySolver.service.MemberService;
import com.hunny.uneasySolver.session.LoginSessionManager;
import com.hunny.uneasySolver.session.MemberInfo_simple;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        return "/members/singUpPage";
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

    @GetMapping("members/login")
    public String login(Model model){
        model.addAttribute("form", new MemberLoginForm());
        return "/members/loginPage";
    }

    @PostMapping("members/login")
    public String handleLogin(HttpServletResponse response, MemberLoginForm form){
        Member result = memberService.login(form);
        MemberInfo_simple info = MemberInfo_simple.createMemberCookie(result);



        sessionManager.createSession(info,response);
        Cookie idCookie = new Cookie("memberId", "1");
        response.addCookie(idCookie);
        System.out.println("cookie 추가");
        return "redirect:/";
    }

}
