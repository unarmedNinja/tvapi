package com.tv.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.text.SimpleDateFormat;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Episode {
    public String getAbsoluteNumber() {
        return absoluteNumber;
    }

    public void setAbsoluteNumber(String absoluteNumber) {
        this.absoluteNumber = absoluteNumber;
    }

    public String getAiredEpisodeNumber() {
        return airedEpisodeNumber;
    }

    public void setAiredEpisodeNumber(String airedEpisodeNumber) {
        this.airedEpisodeNumber = airedEpisodeNumber;
    }

    public String getAiredSeason() {
        return airedSeason;
    }

    public void setAiredSeason(String airedSeason) {
        this.airedSeason = airedSeason;
    }

    public String getDvdEpisodeNumber() {
        return dvdEpisodeNumber;
    }

    public void setDvdEpisodeNumber(String dvdEpisodeNumber) {
        this.dvdEpisodeNumber = dvdEpisodeNumber;
    }

    public String getDvdSeason() {
        return dvdSeason;
    }

    public void setDvdSeason(String dvdSeason) {
        this.dvdSeason = dvdSeason;
    }

    public String getEpisodeName() {
        return episodeName;
    }

    public void setEpisodeName(String episodeName) {
        this.episodeName = episodeName;
    }

    public Date getFirstAired() {
        Date utilDate;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            utilDate = formatter.parse(firstAired);
        }
        catch (Exception e){
            utilDate = new Date();
        }
        return utilDate;
    }

    public void setFirstAired(String firstAired) {
        this.firstAired = firstAired;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    private String absoluteNumber; //int
    private String airedEpisodeNumber; //int
    private String airedSeason; //int
    private String dvdEpisodeNumber; //int
    private String dvdSeason; //int
    private String episodeName; //string
    private String firstAired; //string
    private int id;
    private String lastUpdated; //int
    private String overview;

    public int getShowid() {
        return showid;
    }

    public void setShowid(int showid) {
        this.showid = showid;
    }

    private int showid;
}
