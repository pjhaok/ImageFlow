package com.example.imageflow.model;


class DataModelLinksParent {

    // Initially thought about using Gson

//    @SerializedName("self")
//    private String selfUrl;
//
//    @SerializedName("html")
//    private  String htmlUrl;
//
//    @SerializedName("download")
//    private  String downloadUrl;

    private String selfUrl;

    private  String htmlUrl;

    private  String downloadUrl;


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

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }


    public DataModelLinksParent(String selfUrl, String htmlUrl, String downloadUrl) {
        this.selfUrl = selfUrl;
        this.htmlUrl = htmlUrl;
        this.downloadUrl = downloadUrl;
    }


}
