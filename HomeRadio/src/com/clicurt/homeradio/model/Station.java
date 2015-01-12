package com.clicurt.homeradio.model;

/**
 * Created by Blow on 1/12/2015.
 */
public class Station {
    private String title, thumbnailUrl;
    private double frequency;

    public Station(){

    }

    public Station(String name, String thumbnailUrl, double frequency){
        this.title = name;
        this.thumbnailUrl = thumbnailUrl;
        this.frequency = frequency;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }


    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }
}
