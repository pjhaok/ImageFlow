package com.example.imageflow.model;


class DataModelUser {

    // Initially thought about using Gson

//    @SerializedName("id")
//    private String id;
//
//    @SerializedName("userName")
//    private String userName;
//
//    @SerializedName("name")
//    private String name;
//
    private String id;

    private String userName;

    private String name;

    private DataModelProfileImage profileImage;

    private DataModelLinks links;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DataModelProfileImage getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(DataModelProfileImage profileImage) {
        this.profileImage = profileImage;
    }

    public DataModelLinks getLinks() {
        return links;
    }

    public void setLinks(DataModelLinks links) {
        this.links = links;
    }

    public DataModelUser(String id, String userName, String name, DataModelProfileImage profileImage, DataModelLinks links) {
        this.id = id;
        this.userName = userName;
        this.name = name;
        this.profileImage = profileImage;
        this.links = links;
    }
}
