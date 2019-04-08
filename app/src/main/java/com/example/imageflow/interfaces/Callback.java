package com.example.imageflow.interfaces;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

public interface Callback {

    void onSuccess(@NonNull Bitmap bitmap);

    void onError(@NonNull String message);


  public   class IFCallBack implements Callback {
        @Override
        public void onSuccess(@NonNull Bitmap bitmap){

        }

        @Override
        public void onError(@NonNull String message) {

        }
    }




}
