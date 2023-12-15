package com.example.educlass.model;

public class imgModel {

    private String imageUrl=null;
    public imgModel(){

    }

    public imgModel(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /*private String imageUrl;
    public imgModel(){

    }

    public imgModel(String imageUrl)
    {
        this.imageUrl=imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }*/
}
