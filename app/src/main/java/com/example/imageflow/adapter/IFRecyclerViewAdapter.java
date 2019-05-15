package com.example.imageflow.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.imageflow.R;
import com.example.imageflow.datapump.executors.IFThreadPoolHandler;
import com.example.imageflow.datapump.tasktypes.FileType;
import com.example.imageflow.interfaces.Callback;
import com.example.imageflow.model.DataModel;
import com.example.imageflow.model.DataModelUrls;
import com.example.imageflow.model.TaskModel;

import java.util.List;

public class IFRecyclerViewAdapter extends RecyclerView.Adapter<IFRecyclerViewAdapter.ViewHolder> {

    private List<DataModel> models;

    private Context context;



    private String DOWNLOAD_DIR = Environment.getExternalStoragePublicDirectory
            (Environment.DIRECTORY_DOWNLOADS).getPath();

    public IFRecyclerViewAdapter(List<DataModel> models, Context ctx) {


        this.models = models;
        context = ctx;
    }
    @Override
    public int getItemCount() {
        
        if(models==null) return 0;
        
        return models.size();
    }

    @Override
    public IFRecyclerViewAdapter.ViewHolder
    onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row, parent, false);

        IFRecyclerViewAdapter.ViewHolder viewHolder =
                new IFRecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(IFRecyclerViewAdapter.ViewHolder holder, final int position) {

        DataModel model = models.get(position);

        final DataModelUrls urlsModel = model.getUrls();


        final TextView statusTextView = holder.statusTextView;
        final ImageView imageView = holder.imageView;
        final ProgressBar circularProgressBar = holder.circularProgressBar;

        final Button stopButton = holder.stopButton;
        final Button startButton = holder.startButton;



        circularProgressBar.setVisibility(View.VISIBLE);

        final String taskTag = "Tag" + position;

        stopButton.setVisibility(View.VISIBLE);
        startButton.setVisibility(View.GONE);


      //  Log.d("Adapter", model.getId());


      //  UiUpdateTask drUpdateTask = new UiUpdateTask(callBack);

       // DownloadTask downloadTask = new DownloadTask(tag, url, drUpdateTask);


        IFThreadPoolHandler.getInstance().getFile(taskTag, urlsModel.getRegular(), FileType.IMAGES , new Callback.IFCallBack(){

            @Override
            public void onSuccess(@NonNull TaskModel taskModel) {

                if (taskModel.getType() == FileType.IMAGES) {
                    imageView.setImageBitmap((Bitmap) taskModel.getObject());
                }

                imageView.setVisibility(View.VISIBLE);
                stopButton.setVisibility(View.GONE);
                startButton.setVisibility(View.GONE);
                statusTextView.setVisibility(View.GONE);
                circularProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(@NonNull String message) {
                super.onError(message);
                imageView.setVisibility(View.GONE);
                stopButton.setVisibility(View.GONE);
                startButton.setVisibility(View.VISIBLE);

                statusTextView.setText(message);
                statusTextView.setVisibility(View.VISIBLE);
                circularProgressBar.setVisibility(View.GONE);
            }
        });
        


        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                circularProgressBar.setVisibility(View.GONE);
                startButton.setVisibility(View.VISIBLE);
                stopButton.setVisibility(View.GONE);
                removeTask(taskTag);


            }
        });


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                circularProgressBar.setVisibility(View.VISIBLE);
                startButton.setVisibility(View.GONE);
                stopButton.setVisibility(View.VISIBLE);


                IFThreadPoolHandler.getInstance().getFile(taskTag, urlsModel.getRegular(), FileType.IMAGES , new Callback.IFCallBack(){

                    @Override
                    public void onSuccess(@NonNull TaskModel taskModel) {

                        if (taskModel.getType() == FileType.IMAGES) {
                            imageView.setImageBitmap((Bitmap) taskModel.getObject());
                        }

                        imageView.setVisibility(View.VISIBLE);
                        stopButton.setVisibility(View.GONE);
                        startButton.setVisibility(View.GONE);
                        statusTextView.setVisibility(View.GONE);
                        circularProgressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(@NonNull String message) {
                        super.onError(message);
                        imageView.setVisibility(View.GONE);
                        stopButton.setVisibility(View.GONE);
                        startButton.setVisibility(View.VISIBLE);

                        statusTextView.setText(message);
                        statusTextView.setVisibility(View.VISIBLE);
                        circularProgressBar.setVisibility(View.GONE);
                    }
                });

            }
        });




    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView statusTextView;
        public ProgressBar circularProgressBar;
        public Button stopButton;
        public Button startButton;

        public ViewHolder(View view) {
            super(view);

            stopButton = view.findViewById(R.id.btn_stop);
            startButton = view.findViewById(R.id.btn_start);
            circularProgressBar = view.findViewById(R.id.pb);

            imageView = view.findViewById(R.id.iv_download);

            statusTextView = view.findViewById(R.id.download_file_status);
        }
    }


    private void removeTask(String tag) {

        IFThreadPoolHandler.getInstance().removeTask(tag);

    }




}
