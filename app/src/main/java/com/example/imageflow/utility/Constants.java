package com.example.imageflow.utility;

import android.os.Bundle;
import android.os.Message;

import java.util.concurrent.TimeUnit;

public class Constants {


    // private static int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
    public static int NUMBER_OF_CORES = 20;
    public static final int KEEP_ALIVE_TIME = 1;
    public static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;

    public static final String LOG_TAG = "BackgroundThread";
    public static final int MESSAGE_ID = 1;
    public static final String DATA_STRING = "DATA";

    public static final String ROW_TAG = "ROW";

    public static final String RESULT = "RESULT";
    public static final int RESULT_FAILED = 0;
    public static final  int RESULT_SUCCESS = 1;

    public static final String EMPTY_MESSAGE = "EMPTY_MESSAGE";

    public static Message createMessage(int id, String dataString) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.DATA_STRING, dataString);
        Message message = new Message();
        message.what = id;
        message.setData(bundle);

        return message;
    }

    public static Message sendJSOn(int id ,boolean isSuccess, String dataString) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(RESULT, isSuccess);
        bundle.putString(Constants.DATA_STRING, dataString);
        Message message = new Message();
        message.what = id;
        message.setData(bundle);

        return message;
    }



}
