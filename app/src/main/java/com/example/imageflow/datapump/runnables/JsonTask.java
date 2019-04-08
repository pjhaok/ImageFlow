package com.example.imageflow.datapump.runnables;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Message;
import android.util.Log;

import com.example.imageflow.datapump.executors.IFThreadPoolHandler;
import com.example.imageflow.interfaces.IFTaskupdate;
import com.example.imageflow.utility.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JsonTask extends Task {

    private String tag;
    private String urlString;
    String localFile;
    UiUpdateTask resultUpdateTask;
    private IFThreadPoolHandler poolHandler;
    private WeakReference<IFTaskupdate> updatable;


    public JsonTask(String tag, String urlIn, IFThreadPoolHandler handler, IFTaskupdate updatable){
        this.tag = tag;
        urlString = urlIn;
        this.poolHandler = handler;
        this.updatable = new WeakReference<>(updatable);


    }

    public String getTag() {
        return tag;
    }

    @Override
    public void run() {

        boolean isSuccess;

        String json = getJson();

        if (json != null) {
            isSuccess = true;
        } else {
            isSuccess = false;
        }

sendMessage(isSuccess, json);


    }


    private void sendMessage(boolean isSuccess, String json) {


        Message message = Constants.sendJSOn(2, isSuccess, json );

            if (updatable != null && updatable.get() != null) {

                Log.d("UIThread", updatable.toString());

                updatable.get().update(message);

                Log.d("JSON", "fired3");
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

        this.poolHandler = poolHandler;
    }

}