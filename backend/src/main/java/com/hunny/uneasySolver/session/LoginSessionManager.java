package com.hunny.uneasySolver.session;

import com.hunny.uneasySolver.domain.dto.MemberDTO;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class LoginSessionManager {

    private static final String LOGIN_SESSION_COOKIE = "loginCookie";

    private Map<String, MemberDTO> sessionStore = new ConcurrentHashMap<>();

    public void createSession(MemberDTO value, HttpServletResponse response){

        String sessionId = UUID.randomUUID().toString();

        System.out.println("session 값 : " + sessionId);
        sessionStore.put(sessionId, value);

        response.addCookie(new Cookie(LOGIN_SESSION_COOKIE, sessionId));
    }

    public MemberDTO getSession(HttpServletRequest request){
        Cookie cookie = findCookie(request, LOGIN_SESSION_COOKIE);
        if(cookie == null) return null;

        return sessionStore.get(cookie.getValue());
    }

    private Cookie findCookie(HttpServletRequest request, String cookieName) {
        return Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals(cookieName))
                .findAny()
                .orElse(null);
    }

    public void expire(HttpServletRequest request){
        Cookie cookie = findCookie(request, LOGIN_SESSION_COOKIE);
        if(cookie != null){
            sessionStore.remove(cookie.getValue());
        }
    }

}
