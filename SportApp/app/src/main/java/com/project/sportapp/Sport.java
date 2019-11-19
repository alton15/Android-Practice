package com.project.sportapp;

public class Sport {

    private String title;
    private String info;
    private final int imageResourse;

    Sport(String title, String info, int imageResourse) {
        this.title = title;
        this.info = info;
        this.imageResourse = imageResourse;
    }

    String getTitle() { return title; }
    String getInfo() { return info; }
    int getimageResourse() { return imageResourse; }

    public void setInfo(String info) {
        this.info = info;
    }

}
