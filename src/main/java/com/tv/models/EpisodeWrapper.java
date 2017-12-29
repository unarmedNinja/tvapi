package com.tv.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EpisodeWrapper {
    public List<Episode> getData() {
        return data;
    }

    public void setData(List<Episode> data) {
        this.data = data;
    }

    private List<Episode> data;
}
