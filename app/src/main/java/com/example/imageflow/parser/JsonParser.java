package com.example.imageflow.parser;

import android.os.Handler;
import android.util.Log;

import com.example.imageflow.datapump.executors.IFThreadPoolHandler;
import com.example.imageflow.datapump.runnables.Task;
import com.example.imageflow.model.DataModel;
import com.example.imageflow.model.DataModelUrls;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public  class JsonParser  {


  public static List<DataModel> parseJSON( String jsonString) {

      List<DataModel> models = new ArrayList<>();


      try {
          JSONArray jsonArray = new JSONArray(jsonString);

// only adding id, width, height and regular image url states
          for (int n = 0; n < jsonArray.length(); n++) {

              JSONObject object = jsonArray.getJSONObject(n);

              DataModelUrls modelUrls = new DataModelUrls(object.getJSONObject("urls").getString("regular"));


              models.add(new DataModel(object.getString("id"), object.getInt("width"), object.getInt("height"), modelUrls));

          }



          Log.d("Model", "model size is " + models.size());

          return models;


      } catch (JSONException e) {
          e.printStackTrace();

          Log.d("Model", "Json exception" + e.toString());

          return null;
      }


  }


}
