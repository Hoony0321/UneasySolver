package com.hunny.uneasySolver.controller;

import com.hunny.uneasySolver.session.LoginSessionManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    private final LoginSessionManager sessionManager;

    public HomeController(LoginSessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @GetMapping("/")
    public String home(HttpServletRequest request){
//        MemberDTO info = sessionManager.getSession(request);
//        if(info != null) System.out.println(info.getEmail());

        return "home";
    }

}
