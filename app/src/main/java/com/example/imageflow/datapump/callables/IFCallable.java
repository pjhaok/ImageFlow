package com.example.imageflow.datapump.callables;

import android.os.Message;

import com.example.imageflow.datapump.tasktypes.FileType;
import com.example.imageflow.datapump.executors.IFThreadPoolHandler;
import com.example.imageflow.utility.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;

public class IFCallable  implements Callable {


    private IFThreadPoolHandler threadPoolManager;

    private URL url;
    private FileType type;



    public IFCallable(URL url, FileType type) {

        this.url = url;
        this.type = type;

    }


    private String downloadJson() throws IOException {





        HttpURLConnection  connection = (HttpURLConnection) url.openConnection();
            connection.connect();


            InputStream stream = connection.getInputStream();

        BufferedReader    reader = new BufferedReader(new InputStreamReader(stream));

            StringBuilder buffer = new StringBuilder();
            String line = "";

            while ((line = reader.readLine()) != null) {
                buffer.append(line).append("\n");
              //  Log.d("Response: ", "> " + line);

            }

            return buffer.toString();



    }



    @Override
    public Object call() {

        try {


            if (Thread.interrupted()) throw new InterruptedException();

            if (type == FileType.JSON) {



            Message message = Constants.createMessage(Constants.MESSAGE_ID, downloadJson());


            if (threadPoolManager != null) {

                threadPoolManager.sendMessage(message);
            }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    public void setIFThreadPoolManager(IFThreadPoolHandler threadPoolManager) {

        this.threadPoolManager = threadPoolManager;
    }

}
