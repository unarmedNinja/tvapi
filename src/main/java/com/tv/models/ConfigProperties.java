package com.tv.models;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.net.URL;
import java.net.URLClassLoader;

@Configuration
@PropertySource("classpath:config.properties")
@ConfigurationProperties(prefix = "tv")
public class ConfigProperties {

    private String key;
    private String username;
    private String userkey;
    private String domain;

    public ConfigProperties(){
        ClassLoader cl = ClassLoader.getSystemClassLoader();

        URL[] urls = ((URLClassLoader)cl).getURLs();

        for(URL url: urls){
            System.out.println(url.getFile());
        }
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserkey() {
        return userkey;
    }

    public void setUserkey(String userkey) {
        this.userkey = userkey;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
