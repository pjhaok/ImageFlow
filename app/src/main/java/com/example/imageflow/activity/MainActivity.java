package com.example.imageflow.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.imageflow.datapump.executors.IFThreadPoolHandler;
import com.example.imageflow.R;
import com.example.imageflow.adapter.IFRecyclerViewAdapter;
import com.example.imageflow.datapump.tasktypes.FileType;
import com.example.imageflow.interfaces.Callback;
import com.example.imageflow.interfaces.IFTaskupdate;
import com.example.imageflow.model.DataModel;
import com.example.imageflow.model.TaskModel;
import com.example.imageflow.parser.JsonParser;
import com.example.imageflow.utility.Constants;
import com.example.imageflow.view.IFProgressBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IFTaskupdate {
    private static final String TAG = "MainActivity";
    private static List<DataModel> models = new ArrayList<>();
    private static List<DataModel> modelsRV = new ArrayList<>();
    private IFRecyclerViewAdapter recyclerViewAdapter;
    private IFProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SwipeRefreshLayout pullToRefresh;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressBar = findViewById(R.id.progress_bar_main);
        progressBar.setVisibility(View.GONE);


        getJSON("http://pastebin.com/raw/wgkJgazE");


        RecyclerView recyclerView = findViewById(R.id.files_rv);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
//                fileRv.getContext(),gridLayoutManager.getOrientation());
//        fileRv.addItemDecoration(dividerItemDecoration);

         recyclerViewAdapter = new IFRecyclerViewAdapter(
                 modelsRV, this);
        recyclerView.setAdapter(recyclerViewAdapter);


        setupScrollListener(gridLayoutManager, recyclerView);

        //storage write permission needed to save downloaded file on device
        writeStoragePermission();


        pullToRefresh = findViewById(R.id.pullToRefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

               progressBar.setVisibility(View.VISIBLE);

                getJSON("http://pastebin.com/raw/wgkJgazE");
                pullToRefresh.setRefreshing(false);
            }
        });

    }

    private void setupScrollListener(final GridLayoutManager layoutmanager, RecyclerView recyclerView) {

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);


            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


                // todo check conditions before creating runnable
                recyclerView.post(new Runnable() {
                    public void run() {
                        addmoreRowsToView(layoutmanager.findLastVisibleItemPosition());
                    }
                });




            }
        });

    }

    private void loadView() {

        progressBar.setVisibility(View.GONE);


        if (models != null) {

            modelsRV.clear();

            if (models.size() <= 10) {
                modelsRV.addAll(models);
            } else  {

                for (int i = 0; i < 10; i++) {
                    modelsRV.add(models.get(i));
                }

            }


            this.recyclerViewAdapter.notifyDataSetChanged();
        } else{

            Toast.makeText(this, "DATA Void", Toast.LENGTH_SHORT).show();
        }



    }


    private void addmoreRowsToView(int currentPosition) {


        // check if we have more items to add. and is view near the bottom

//        Log.d("Rows", "adding data " + modelsRV.size() + "   " + models.size());
     //   Log.d("Rows", "current position " + currentPosition);


        if (modelsRV != null && models.size() > modelsRV.size() && (currentPosition > modelsRV.size() - 3 )) {

            int itemInView = modelsRV.size();

            int dataCount = models.size();

// if we have more than 10 more items then add only next 10 items.
            if (models.size() > (modelsRV.size() + 10)) {

                int f = modelsRV.size();
                int l = modelsRV.size() + 10 ;

               while ( f < l) {


                    modelsRV.add(models.get(f));

                    f ++;

                }

                //    Log.d("Rows", "index " + f);
               // Log.d("Rows", "model size " + models.size());
              //  Log.d("Rows", "modelrv size " + modelsRV.size());
            }

            // else add all remaining items
            else {

                for (int i = (modelsRV.size() - 1); i < models.size() ; i++) {

                    modelsRV.add(models.get(i));
                }
            }

            Log.d("Rows", "data added " + modelsRV.size() + "   " + models.size());
            recyclerViewAdapter.notifyItemInserted(modelsRV.size()-1);





        }



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();

        if (id == R.id.menu_stop_all_threads) {

            stopAllThreads();

            return true;

        }

        return super.onOptionsItemSelected(item);

    }


    private void getJSON(String urlString) {

        progressBar.setVisibility(View.VISIBLE);
      //  IFThreadPoolHandler.getInstance().getFile("JSON", urlString, FileType.JSON, new Callback.IFCallBack(){});



        IFThreadPoolHandler.getInstance().getFile("MainJSON", urlString, FileType.JSON , new Callback.IFCallBack(){

            @Override
            public void onSuccess(@NonNull TaskModel taskModel) {

                if (taskModel.getType() == FileType.JSON) {
                    String jsonText = (String) taskModel.getObject();

                    models.clear();
                    models.addAll(JsonParser.parseJSON(jsonText));

                    // todo  for testing many lines
                   // models.addAll(models);
                   // models.addAll(models);
                    Log.d("Adapter", "" +models.size());

                    loadView();



                }


            }

            @Override
            public void onError(@NonNull String message) {
                super.onError(message);
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Failed to sync", Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void stopAllThreads() {

        IFThreadPoolHandler.getInstance().stopAndClearAllThreads();

    }


    private void writeStoragePermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE},
                    1);

        }
    }



    @Override
    public void update(Message message)  {

//         if (message.what == 2) {


//             Bundle bundle = message.getData();

//             if (bundle.getBoolean(Constants.RESULT)) {

//                 String jsonText = bundle.getString(Constants.DATA_STRING);

//                 models.clear();
//                 models.addAll(JsonParser.parseJSON(jsonText));

//                 // todo  for testing many lines
//                 models.addAll(models);
//                 models.addAll(models);

//                 Log.d("Adapter0", "" +models.size());


//                     Runnable runnable = new Runnable() {
//                         @Override
//                         public void run() {



//                             loadView();


//                         }
//                     };

//                     Handler handler = new Handler(this.getMainLooper());

//                     handler.post(runnable);



//             }} else {

//                 Log.d("JSON", "fired6.2");

//             }


        }

    }

