package com.hunny.uneasySolver.controller;

import com.hunny.uneasySolver.session.LoginSessionManager;
import com.hunny.uneasySolver.session.MemberInfo_simple;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
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

        MemberInfo_simple info = sessionManager.getSession(request);
        if(info != null) System.out.println(info.getEmail());


        return "home";
    }

}
