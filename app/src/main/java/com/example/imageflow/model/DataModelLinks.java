package com.example.imageflow.model;


class DataModelLinks {
    // Initially thought about using Gson

//    @SerializedName("self")
//    private String selfUrl;
//
//    @SerializedName("html")
//    private String htmlUrl;
//
//    @SerializedName("photos")
//    private String photosUrl;
//
//    @SerializedName("likes")
//    private String likesUrl;

    private String selfUrl;

    private String htmlUrl;

    private String photosUrl;

    private String likesUrl;




    public String getSelfUrl() {
        return selfUrl;
    }

    public void setSelfUrl(String selfUrl) {
        this.selfUrl = selfUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getPhotosUrl() {
        return photosUrl;
    }

    public void setPhotosUrl(String photosUrl) {
        this.photosUrl = photosUrl;
    }

    public String getLikesUrl() {
        return likesUrl;
    }

    public void setLikesUrl(String likesUrl) {
        this.likesUrl = likesUrl;
    }

    public DataModelLinks(String selfUrl, String htmlUrl, String photosUrl, String likesUrl) {
        this.selfUrl = selfUrl;
        this.htmlUrl = htmlUrl;
        this.photosUrl = photosUrl;
        this.likesUrl = likesUrl;
    }
}
