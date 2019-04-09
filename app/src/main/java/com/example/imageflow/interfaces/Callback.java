package com.example.imageflow.interfaces;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.example.imageflow.model.TaskModel;

public interface Callback {

    void onSuccess(@NonNull TaskModel taskModel);

    void onError(@NonNull String message);


  public   class IFCallBack implements Callback {
        @Override
        public void onSuccess(@NonNull TaskModel taskModel){

        }

        @Override
        public void onError(@NonNull String message) {

        }
    }




}
