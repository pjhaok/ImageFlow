package com.example.imageflow.datapump.runnables;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.imageflow.interfaces.Callback;

public class UiUpdateTask implements Runnable{
    private String tag;
    private ImageView imageView;
    private boolean isSuccess;

    private Bitmap bitmap;

    private Callback.IFCallBack callBack;


    public UiUpdateTask(Callback.IFCallBack callBack) {

        this.callBack = callBack;

    }

//    public UiUpdateTask(ImageView imageView){
//        this.imageView = imageView;
//
//    }



    public void setBackgroundMsg(String tag, boolean isSuccess, Bitmap bitmap){

        this.isSuccess = isSuccess;
        this.bitmap = bitmap;
        this.tag = tag;



    }


    @Override
    public void run() {


        if (callBack != null) {

            if (isSuccess) {

              callBack.onSuccess(bitmap);

            } else {

                callBack.onError("Error in loading");
            }

        }




    }
}