package com.tv.controller;

import com.tv.db.ShowJdbcRepository;
import com.tv.models.Episode;
import com.tv.models.Show;
import com.tv.models.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(tv.class);
    private static final String TVTOKEN = "TVDBTOKEN";

    @Autowired
    ShowJdbcRepository repository;

    @RequestMapping("/hello")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/token")
    public Token token() {
        tvdb tvapi = new tvdb();
        Token token = tvapi.getToken();
        String accessToken = token.getToken();
        LOGGER.debug("retrieved access token: {}", accessToken);
        return token;
    }

    @RequestMapping("/summary")
    public String summary(@RequestHeader(TVTOKEN) String tvdbtoken){
        tvdb tvapi = new tvdb();
        String data = tvapi.getSummary(tvdbtoken,"257655");
        return data;
    }

    @RequestMapping("/getEpisodes/{showId}")
    public List<Episode> episodesdata(@RequestHeader(TVTOKEN) String tvdbtoken, @PathVariable("showId") int showId, @RequestParam("page") int page){
        LOGGER.debug("using tvdb token: {}", tvdbtoken);
        tvdb tvapi = new tvdb();
        List<Episode> data = tvapi.getEpisodes(tvdbtoken,showId, page);

        //       data.forEach((e) -> repository.insertEpisode(e));
        for (Episode e : data) {
            repository.insertEpisode(e);
        }

        return data;
    }

    @RequestMapping("/episodes/{showId}")
    public List<Episode> getEpisodes(@PathVariable("showId") int showId){
        List<Episode> episodes = repository.getEpisodesByShowId(showId);

        return episodes;
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('STANDARD_USER')")
    @RequestMapping("/show")
    public Show show(){
        Show aShow = repository.findById(257655);
        return aShow;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/show/{showId}")
    public Show showById( @PathVariable("showId") int showId){
        Show aShow = repository.findById(showId);
        return aShow;
    }

    @RequestMapping("/shows")
    public List<Show> getShows(){
        List<Show> shows = repository.getAllShows();
        return shows;
    }

    @PreAuthorize("hasRole('STANDARD_USER')")
    @RequestMapping("/shows/recent")
    public List<Episode> getRecentShows(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)-14);

        // convert to date
        Date myDate = cal.getTime();
        LOGGER.debug("fetching recent episodes for date: {}", myDate.toString());
        List<Episode> episodes = repository.getRecentEpisodes(myDate);
        return episodes;
    }

    @RequestMapping(value = "/show/add", method = RequestMethod.POST)
    public boolean addShow(@RequestBody Show aShow){
        try {
            Show bShow = repository.findById(aShow.getId());
        }
        catch(org.springframework.dao.EmptyResultDataAccessException dne){
            LOGGER.debug("show does not exist. continue adding: {}", aShow);
            repository.insertShow(aShow);
            return true;
        }
        catch (Exception e){
            LOGGER.debug("adding show failed: {}", e);
        }
        return false;
    }

    @RequestMapping(value = "/shows/delete/{showId}", method = RequestMethod.DELETE)
    public int getShows(@PathVariable("showId") int showId){
        int result = repository.deleteEpisodesByShowId(showId);
        return result;
    }
}