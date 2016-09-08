package com.tom.hunter.model;

/**
 * Created by txu1 on 9/6/2016.
 */
public class Photo {

    private String url;

    private String description;

    public Photo(String url, String description) {
        this.url = url;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
