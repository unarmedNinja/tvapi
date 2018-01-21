package com.tv.biz;

import java.util.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.tv.db.ShowJdbcRepository;
import com.tv.models.Episode;
import com.tv.models.EpisodeWrapper;
import com.tv.models.Token;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class tvdb {
    private final String API_KEY = "D0A2EF46A5938D34";
    private final String USER_NAME = "unarmedninja";
    private final String USER_KEY = "610C63A36CCFD6B0";
    private final String API_DOMAIN = "https://api.thetvdb.com";

    @Autowired
    ShowJdbcRepository repository;

    public Token getToken(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "application/json");


        JSONObject req_payload = new JSONObject();
        req_payload.put("apikey", API_KEY);
        req_payload.put("userkey", USER_KEY);
        req_payload.put("username", USER_NAME);

        HttpEntity<String> request = new HttpEntity<String>(req_payload.toString(), httpHeaders);
        String url = API_DOMAIN + "/login";

        RestTemplate restTemplate = new RestTemplate();
        Token response = restTemplate.postForObject(url, request, Token.class);


       // JSONObject jsonObj = new JSONObject(response);
        //String balance = jsonObj.get("data").toString();
        return response;
    }

    public String getSummary(String accessToken, String id){
        String url = API_DOMAIN + "/series/" + id + "/episodes/summary";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "application/json");
        httpHeaders.set("Authorization", "Bearer "+accessToken);

        HttpEntity<String> request = new HttpEntity<String>(httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new HttpComponentsAsyncClientHttpRequestFactory());
        //Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);

        ResponseEntity<String> response = restTemplate.exchange(
                url, HttpMethod.GET, request, String.class, "");

        return response.getBody();
    }

    public List<Episode> getEpisodes(String accessToken, int id){
        String url = API_DOMAIN + "/series/" + id + "/episodes";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "application/json");
        httpHeaders.set("Authorization", "Bearer "+accessToken);

        HttpEntity<String> request = new HttpEntity<String>(httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new HttpComponentsAsyncClientHttpRequestFactory());
        //Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);


        ResponseEntity<EpisodeWrapper> response = restTemplate.exchange(
                url, HttpMethod.GET, request, EpisodeWrapper.class, "");

        EpisodeWrapper wrapper = response.getBody();
        List<Episode> episodes = wrapper.getData();
        episodes.forEach((e) -> e.setShowid(id));

        return episodes;
    }

    public boolean saveEpisodes(List<Episode> episodes){
        boolean isComplete = false;
        try {
            episodes.forEach((e) -> repository.insertEpisode(e));
            isComplete = true;
        }
        catch (Exception e){
            System.out.println(e);
        }

        return true;
    }
}
