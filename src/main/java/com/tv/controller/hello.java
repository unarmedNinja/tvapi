package com.tv.controller;

import com.tv.db.ShowJdbcRepository;
import com.tv.models.Show;
import com.tv.models.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tv.biz.tvdb;

import javax.servlet.http.HttpSession;

@RequestMapping("/tv")
@RestController
public class hello {
    @Autowired
    ShowJdbcRepository repository;

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

    @RequestMapping("/episodes")
    public String episodes(HttpSession session){
        String token = (String)session.getAttribute("token");
        tvdb tvapi = new tvdb();
        String data = tvapi.getEpisodes(token,"257655");
        return data;
    }

    @RequestMapping("/show")
    public Show show(HttpSession session){
        Show aShow = repository.findById(257655);
        return aShow;
    }
}
//jdbc:h2:~/test