package com.example.imageflow.datapump.runnables;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Message;
import android.util.Log;

import com.example.imageflow.datapump.executors.IFThreadPoolHandler;
import com.example.imageflow.datapump.tasktypes.FileType;
import com.example.imageflow.model.TaskModel;
import com.example.imageflow.utility.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class JsonTask extends Task {

    private String tag;
    private String urlString;
    private UiUpdateTask resultUpdateTask;
    private IFThreadPoolHandler ifThreadPoolHandler;
    private FileType type;



    public JsonTask( String tag, IFThreadPoolHandler handler, String urlIn, FileType type,
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

        boolean isSuccess;



//
//            String json = getJson();
//
//            if (json != null) {
//                isSuccess = true;
//
//                IFThreadPoolHandler.cacheTable.put(urlString, new TaskModel(urlString,null,json, new Date()));
//
//            } else {
//                isSuccess = false;
//            }
//
//
//
//            //String url, Bitmap bitmap, String json, Date lastTimeUsed
//            sendMessage(isSuccess, json);
//






        String json ;
        try {
            json = downloadFile();

            TaskModel taskModel =   new TaskModel(urlString,this.type, json, new Date());

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

    private String downloadFile() throws IOException {


        HttpURLConnection connection = null;
        InputStream input = null;
        BufferedReader reader = null;
        try {


            URL url = new URL(urlString);
            connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            input = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));

            StringBuilder buffer = new StringBuilder();
            String line = "";

            while ((line = reader.readLine()) != null) {
                buffer.append(line).append("\n");
                // Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)

            }

            return buffer.toString();


        }  finally {
            if (connection != null) {
                connection.disconnect();
            }

            if (input != null) {
                input.close();
            }


        }



    }



    private String getJson() {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();


            InputStream stream = connection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(stream));

            StringBuilder buffer = new StringBuilder();
            String line = "";

            while ((line = reader.readLine()) != null) {
                buffer.append(line).append("\n");
               // Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)

            }

            return buffer.toString();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public void setThreadPoolManager(IFThreadPoolHandler poolHandler) {

        this.ifThreadPoolHandler = poolHandler;
    }

}