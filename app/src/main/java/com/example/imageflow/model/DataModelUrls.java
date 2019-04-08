package com.example.imageflow.model;


public class DataModelUrls {

    // Initially thought about using Gson

//    @SerializedName("raw")
//    private String raw;
//
//    @SerializedName("full")
//    private String full;
//
//    @SerializedName("regular")
//    private String regular;
//
//    @SerializedName("small")
//    private String small;
//
//    @SerializedName("thumb")
//    private String thumb;

    private String raw;

    private String full;

    private String regular;

    private String small;

    private String thumb;



    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public String getFull() {
        return full;
    }

    public void setFull(String full) {
        this.full = full;
    }

    public String getRegular() {
        return regular;
    }

    public void setRegular(String regular) {
        this.regular = regular;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }


    public DataModelUrls(String raw, String full, String regular, String small, String thumb) {
        this.raw = raw;
        this.full = full;
        this.regular = regular;
        this.small = small;
        this.thumb = thumb;
    }


     public DataModelUrls( String regular) {
        this.raw = "";
        this.full = "";
        this.regular = regular;
        this.small = "";
        this.thumb = "";
    }




}
