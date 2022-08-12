package com.hunny.uneasySolver.controller;

import com.hunny.uneasySolver.exception.LoginException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;


@Controller
@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(LoginException.class)
    @GetMapping("/error")
    public String loginFail(HttpServletRequest request, Model model){
        Object code = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object msg = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
        model.addAttribute("code", code.toString());
        model.addAttribute("msg", msg);

        return "error/error";
    }

}
