package com.example.imageflow.model;


class DataModelProfileImage {

    // Initially thought about using Gson

//        @SerializedName("small")
//    private String small;
//
//        @SerializedName("medium")
//    private String medium;
//
//        @SerializedName("large")
//    private String large;

    private String small;

    private String medium;

    private String large;



    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }


    public DataModelProfileImage(String small, String medium, String large) {
        this.small = small;
        this.medium = medium;
        this.large = large;
    }
}
