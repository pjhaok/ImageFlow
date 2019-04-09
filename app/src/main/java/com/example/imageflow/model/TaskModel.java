package com.example.imageflow.model;

import android.graphics.Bitmap;

import com.example.imageflow.datapump.tasktypes.FileType;

import java.util.Date;

public class TaskModel {

    private String url;
    private Object object;
    private FileType type;
    private Date lastTimeUsed;



    public TaskModel(String url, FileType type, Object object, Date lastTimeUsed) {
        this.url = url;
        this.type = type;
        this.object = object;
        this.lastTimeUsed = lastTimeUsed;
    }



    public String getUrl() {
        return url;
    }

    public Object getObject() {
        return object;
    }

    public Date getLastTimeUsed() {
        return lastTimeUsed;
    }

    public FileType getType() {
        return type;
    }

    public void setLastTimeUsed(Date lastTimeUsed) {
        this.lastTimeUsed = lastTimeUsed;
    }


}
