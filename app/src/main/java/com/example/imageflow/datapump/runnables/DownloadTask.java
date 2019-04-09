package com.example.imageflow.datapump.runnables;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.imageflow.datapump.executors.IFThreadPoolHandler;
import com.example.imageflow.datapump.tasktypes.FileType;
import com.example.imageflow.model.TaskModel;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import java.net.URL;
import java.util.Date;

public class DownloadTask extends Task{


    private String tag;
    private String urlString;
    private UiUpdateTask resultUpdateTask;
    private IFThreadPoolHandler ifThreadPoolHandler;
    private FileType type;



    public DownloadTask( String tag, IFThreadPoolHandler handler, String urlIn, FileType type,
                        UiUpdateTask drUpdateTask){
        this.tag = tag;
        urlString = urlIn;
        this.ifThreadPoolHandler = handler;
        this.type = type;
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

            TaskModel taskModel =   new TaskModel(urlString,this.type, bitmap, new Date());

            IFThreadPoolHandler.getInstance().addToCacheTable(urlString, taskModel);

            resultUpdateTask.setBackgroundMsg(this.tag, true, taskModel);
        } catch (IOException e) {

            resultUpdateTask.downloadFailed(tag);
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