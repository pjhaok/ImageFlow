package com.example.imageflow.datapump.executors;

import android.graphics.Bitmap;
import android.os.Message;
import android.os.Process;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.imageflow.datapump.runnables.DownloadTask;
import com.example.imageflow.datapump.runnables.UiUpdateTask;
import com.example.imageflow.interfaces.Callback;
import com.example.imageflow.model.TaskModel;
import com.example.imageflow.datapump.runnables.JsonTask;
import com.example.imageflow.datapump.runnables.Task;
import com.example.imageflow.datapump.tasktypes.FileType;
import com.example.imageflow.interfaces.IFTaskupdate;
import com.example.imageflow.utility.Constants;

import java.lang.ref.WeakReference;
import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;


public class IFThreadPoolHandler {

    private static IFThreadPoolHandler threadPoolManager = null;
    private static MainThreadExecutor handler;

    private final ExecutorService executorService;
    private final BlockingDeque<Runnable> taskQueue;


    // string is url and cache is object
    public static Hashtable<String, TaskModel> cacheTable = new Hashtable<>();

    Hashtable<String, Future> threadFutureTable ;

    private Set<WeakReference<IFTaskupdate>> updatables = new HashSet<>();



    static {

        threadPoolManager = new IFThreadPoolHandler();
        handler = new MainThreadExecutor();

    }


    private IFThreadPoolHandler(){

        taskQueue = new LinkedBlockingDeque<Runnable>();

        threadFutureTable = new Hashtable<>();

        executorService = new ThreadPoolExecutor(Constants.NUMBER_OF_CORES, Constants.NUMBER_OF_CORES * 2, Constants.KEEP_ALIVE_TIME, Constants.KEEP_ALIVE_TIME_UNIT, taskQueue, new IFThreadPoolHandler.IFThreadFactory());


    }

    public static IFThreadPoolHandler getInstance() {
        return threadPoolManager;
    }

    public void setIFUpdatable(IFTaskupdate updatable) {


        updatables.add(new WeakReference<IFTaskupdate>(updatable));

        Log.d("Updatable", "added");

    }

    public void removeIFUpdatable(IFTaskupdate updatable) {



        for (WeakReference<IFTaskupdate> upd : updatables) {

            if (upd != null && upd.get() != null && upd.get() == updatable) {

                updatables.remove(upd);

                Log.d("Updatable", "removed");

            }

        }


    }

    public void removeallIFUpdatable() {

        updatables.clear();


    }



    public void runOnMainThread(Runnable runnable){

        handler.execute(runnable);



    }



    public void sendMessage(Message m) {

        Log.d("Count", "" +updatables.size());

        for (WeakReference<IFTaskupdate> updatable : updatables) {

            if (updatable != null && updatable.get() != null) {

                Log.d("UIThread", updatable.toString());

                updatable.get().update(m);
            }
        }


    }



//    public void addDownloadTask( Task task) {
//
//        task.setThreadPoolManager(this);
//        Future future = executorService.submit(task);
//        // add this future in a list for future usage or to cancel the task
//
//        //runningTaskList.add(future);
//        threadFutureTable.put(task.getTag(), future);
//
//       // return future;
//    }


    public void addDownloadTask(Task task, Callback callback) {

        task.setThreadPoolManager(this);
        Future future = executorService.submit(task);
        // add this future in a list for future usage or to cancel the task

        //runningTaskList.add(future);
        threadFutureTable.put(task.getTag(), future);

        // return future;
    }



    public Future addCallable( String tag, Callable callable) {

        Future future = executorService.submit(callable);
        // add this future in a list for future usage or to cancel the task

        //runningTaskList.add(future);
        threadFutureTable.put(tag, future);

        return future;
    }


    public void getFile(String tag , String url, FileType type, Callback.IFCallBack callback) {

        UiUpdateTask drUpdateTask = new UiUpdateTask(callback);

        if (cacheTable.containsKey(url) && type == FileType.IMAGES) {

            cacheTable.get(url).setLastTimeUsed(new Date());

            drUpdateTask.setBackgroundMsg(tag, true, cacheTable.get(url));
            runOnMainThread(drUpdateTask);


        } else {

            Task task;

            if (type == FileType.IMAGES) {
                task = new DownloadTask(tag,this, url, type, drUpdateTask);
                Future future = executorService.submit(task);
                threadFutureTable.put(task.getTag(), future);
            } else if (type == FileType.JSON) {

                task = new JsonTask(tag,this, url, type, drUpdateTask);
                Future future = executorService.submit(task);
                threadFutureTable.put(task.getTag(), future);

            }

        }
    }

    public void addToCacheTable(String url, TaskModel taskModel) {


        cacheTable.put(url, taskModel);

    }


//    public void getFile(String tag, String url, FileType type, IFTaskupdate updatable) {
//
//
//
//        if (type == FileType.JSON) {
//
//
//
//            Task task = new DownloadTask(tag,this, url, type, drUpdateTask);
//            Future future = executorService.submit(task);
//            threadFutureTable.put(task.getTag(), future);
//
//            Log.d("JSON", "fired2");
//        }
//
//
//
//    }




//    public void runDownloadFile( Runnable r) {
//
//
//
//
//        // submit the incoming runnable task to executorservice then it will return future object that can be used to cancel the task later on.
//
//        Future future = executorService.submit(r);
//        // add this future in a list for future usage or to cancel the task
//
//        //runningTaskList.add(future);
//        threadFutureTable.put(tag,future);
//
//        //return future;
//
//    }

public void removeTask(String tag ) {





    Log.d("Removetask", tag);
    synchronized (this) {




//        Iterator iterator = taskQueue.iterator();
//        while (iterator.hasNext()) {
//
//
//            Log.d("Removetask1", tag);
//
//                if (((DownloadTask) iterator.next()).getTag().equals(tag)) {
//
//
//                    iterator.remove();
//                    break;
//                }
//                }



     if(threadFutureTable.containsKey(tag)) {

        Future future = threadFutureTable.get(tag);


         Log.d("Removetask2", tag);
            if (!future.isDone()) {
                future.cancel(true);


                Log.d("Removetask2", tag + " cancelled");
            }

    }}


}
    public void stopAndClearAllThreads() {


        synchronized (this) {

            taskQueue.clear();

            for (Future f : threadFutureTable.values()) {
                if (!f.isDone()) {
                    f.cancel(true);
                }
            }
        }

        sendMessage(Constants.createMessage(Constants.MESSAGE_ID, "All threads are stopped now."));

    }


    private static class IFThreadFactory implements ThreadFactory {

        private static int tag = 1;

        @Override
        public Thread newThread(@NonNull Runnable runnable) {


            Thread thread = new Thread(runnable);
            thread.setName("IFThread" + tag);

            Log.d("ThreadNew", "new thread created");
            thread.setPriority(Process.THREAD_PRIORITY_BACKGROUND);

            thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(){

                @Override
                public void uncaughtException(Thread thread, Throwable throwable) {
                    Log.e(Constants.LOG_TAG, thread.getName() + "error" + throwable.getMessage());
                }
            });

            return thread;

        }
    }


}



