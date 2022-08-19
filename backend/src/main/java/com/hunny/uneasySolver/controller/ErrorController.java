package com.hunny.uneasySolver.controller;

import com.hunny.uneasySolver.exception.LoginException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<Object> loginFail(HttpServletRequest request) {
        String msg = request.getAttribute(RequestDispatcher.ERROR_MESSAGE).toString();

        return new ResponseEntity<>(msg, HttpStatus.EXPECTATION_FAILED);
    }

}
