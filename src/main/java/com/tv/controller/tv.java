package com.tv.controller;

import com.tv.db.ShowJdbcRepository;
import com.tv.models.Episode;
import com.tv.models.Show;
import com.tv.models.Token;
import jdk.nashorn.internal.ir.CatchNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tv.biz.tvdb;

import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/tv")
@RestController
public class tv {
    @Autowired
    ShowJdbcRepository repository;

    @RequestMapping("/hello")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/token")
    public Token token(HttpSession session) {
        tvdb tvapi = new tvdb();
        Token token = tvapi.getToken();
        String accessToken = token.getToken();
        System.out.println("ACCESS TOKEN: " + accessToken);
        session.setAttribute("token", accessToken);
        return token;
    }

    @RequestMapping("/summary")
    public String summary(HttpSession session){
        String token = (String)session.getAttribute("token");
        tvdb tvapi = new tvdb();
        String data = tvapi.getSummary(token,"257655");
        return data;
    }

    @RequestMapping("/getEpisodes/{showId}")
    public List<Episode> episodesdata(HttpSession session, @PathVariable("showId") int showId){
        String token = (String)session.getAttribute("token");
        if(token == null){
            System.out.println("getting new token");
            Token t = token(session);
            token = t.getToken();
        }

        tvdb tvapi = new tvdb();
        List<Episode> data = tvapi.getEpisodes(token,showId);

 //       data.forEach((e) -> repository.insertEpisode(e));
        for (Episode e : data) {
            repository.insertEpisode(e);
        }

        return data;
    }

    @RequestMapping("/episodes/{showId}")
    public List<Episode> getEpisodes(HttpSession session,  @PathVariable("showId") int showId){
        List<Episode> episodes = repository.getEpisodesByShowId(showId);

        return episodes;
    }

    @RequestMapping("/show")
    public Show show(HttpSession session){
        Show aShow = repository.findById(257655);
        return aShow;
    }

    @RequestMapping("/show/{id}")
    public Show showById(HttpSession session, @PathVariable("showId") int showId){
        Show aShow = repository.findById(showId);
        return aShow;
    }

    @RequestMapping("/shows")
    public List<Show> getShows(HttpSession session){
        List<Show> shows = repository.getAllShows();
        return shows;
    }

    @RequestMapping("/shows/recent")
    public List<Episode> getRecentShows(HttpSession session){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)-6);

        // convert to date
        Date myDate = cal.getTime();

        List<Episode> episodes = repository.getRecentEpisodes(myDate);
        return episodes;
    }

    @RequestMapping(value = "/show/add", method = RequestMethod.POST)
    public boolean addShow(HttpSession session, @RequestBody Show aShow){
        try {
            Show bShow = repository.findById(aShow.getId());
        }
        catch(org.springframework.dao.EmptyResultDataAccessException dne){
            System.out.println(dne);
            repository.insertShow(aShow);
            return true;
        }
        catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    @RequestMapping(value = "/shows/delete/{showId}", method = RequestMethod.DELETE)
    public int getShows(HttpSession session, @PathVariable("showId") int showId){
        int result = repository.deleteEpisodesByShowId(showId);
        return result;
    }
}