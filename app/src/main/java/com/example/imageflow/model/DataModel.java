package com.example.imageflow.model;


import java.util.List;

public class DataModel {

    // Initially thought about using Gson

//    @SerializedName("ID")
//    private String id;
//
//    @SerializedName("created_at")
//    private String created_at;
//
//    @SerializedName("width")
//    private int width;
//
//    @SerializedName("height")
//    private int height;
//
//    @SerializedName("color")
//    private String color;
//
//    @SerializedName("ID")
//    private int likes;
//
//    @SerializedName("liked_by_user")
//    private boolean liked_by_user;
//
//    @SerializedName("user")
//    private DataModelUser user ;
//
//
//    @SerializedName("current_user_collections")
//    private List<?> currentUserCollections;
//
//    @SerializedName("urls")
//    private DataModelUrls urls;
//
//    @SerializedName("categories")
//    private List<?> categories;
//
//    @SerializedName("links")
//    private DataModelLinksParent links;


    private String id;

    private String created_at;

    private int width;

    private int height;

    private String color;

    private int likes;

    private boolean liked_by_user;

    private DataModelUser user ;

    private List<?> currentUserCollections;

    private DataModelUrls urls;

    private List<?> categories;

    private DataModelLinksParent links;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public boolean isLiked_by_user() {
        return liked_by_user;
    }

    public void setLiked_by_user(boolean liked_by_user) {
        this.liked_by_user = liked_by_user;
    }

    public DataModelUser getUser() {
        return user;
    }

    public void setUser(DataModelUser user) {
        this.user = user;
    }

    public List<?> getCurrentUserCollections() {
        return currentUserCollections;
    }

    public void setCurrentUserCollections(List<?> currentUserCollections) {
        this.currentUserCollections = currentUserCollections;
    }

    public DataModelUrls getUrls() {
        return urls;
    }

    public void setUrls(DataModelUrls urls) {
        this.urls = urls;
    }

    public List<?> getCategories() {
        return categories;
    }

    public void setCategories(List<?> categories) {
        this.categories = categories;
    }

    public DataModelLinksParent getLinks() {
        return links;
    }

    public void setLinks(DataModelLinksParent links) {
        this.links = links;
    }


    public DataModel(String id, int width, int height, DataModelUrls urls) {
        this.id = id;
        this.width = width;
        this.height = height;
        this.urls = urls;
    }







}
