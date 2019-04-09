package com.example.imageflow.datapump.runnables;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.imageflow.interfaces.Callback;
import com.example.imageflow.model.TaskModel;

public class UiUpdateTask implements Runnable{
    private String tag;

    private boolean isSuccess;

    private TaskModel taskModel;

    private Callback.IFCallBack callBack;

    public UiUpdateTask(Callback.IFCallBack callBack) {

        this.callBack = callBack;

    }


    public void setBackgroundMsg(String tag, boolean isSuccess, TaskModel taskModel){

        this.isSuccess = isSuccess;
        this.taskModel = taskModel;
        this.tag = tag;


    }


    public void downloadFailed(String tag){

       this.isSuccess = false;

    }

    @Override
    public void run() {


        if (callBack != null) {

            if (isSuccess) {

              callBack.onSuccess(taskModel);

            } else {

                callBack.onError("Error in loading");
            }

        }




    }
}