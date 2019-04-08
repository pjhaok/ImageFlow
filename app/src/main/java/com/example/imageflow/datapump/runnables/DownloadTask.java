package com.example.imageflow.datapump.runnables;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.imageflow.datapump.executors.IFThreadPoolHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.MalformedURLException;
import java.net.URL;

public class DownloadTask extends Task{


    private String tag;
    private String urlString;

    UiUpdateTask resultUpdateTask;
    private IFThreadPoolHandler ifThreadPoolHandler;

    public DownloadTask( String tag, String urlIn,
                        UiUpdateTask drUpdateTask){
        this.tag = tag;
       urlString = urlIn;

        resultUpdateTask = drUpdateTask;


    }

    public String getTag() {
        return tag;
    }

    @Override
    public void run() {



        Bitmap bitmap = null;
        try {
            bitmap = downloadFile();

            resultUpdateTask.setBackgroundMsg(this.tag, true, bitmap);
        } catch (IOException e) {

            resultUpdateTask.setBackgroundMsg(this.tag, false, bitmap);
            e.printStackTrace();
        }

       finally {
            IFThreadPoolHandler.getInstance().runOnMainThread(resultUpdateTask);
        }


    }

    private Bitmap downloadFile() throws IOException {


        HttpURLConnection connection = null;
        InputStream input = null;
        try {


            URL url = new URL(urlString);
            connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
             input = connection.getInputStream();
            return BitmapFactory.decodeStream(input);


        }  finally {
            if (connection != null) {
                connection.disconnect();
            }

            if (input != null) {
                input.close();
            }


        }



    }




    public void setThreadPoolManager(IFThreadPoolHandler downloadManager) {

        this.ifThreadPoolHandler = downloadManager;
    }

}