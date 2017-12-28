package com.tv.controller;

import com.tv.models.Token;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tv.biz.tvdb;

import javax.servlet.http.HttpSession;

@RequestMapping("/tv")
@RestController
public class hello {
    @RequestMapping("/hello")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/token")
    public String token(HttpSession session) {
        tvdb tvapi = new tvdb();
        Token token = tvapi.getToken();
        String accessToken = token.getToken();
        session.setAttribute("token", accessToken);
        return accessToken;
    }

    @RequestMapping("/summary")
    public String summary(HttpSession session){
        String token = (String)session.getAttribute("token");
        tvdb tvapi = new tvdb();
        String data = tvapi.getSummary(token,"257655");
        return data;
    }
}
