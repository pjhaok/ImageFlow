# ImageFlow
Similar to pinterest pinboard flow, 
Fully written using various amazing features of android and java like ExecutorService, Executors, Future, 
Handler, Looper, Threads, Runnable, Callable, json, http, BlockingDeque, Hashtable, WeakReferences, 
RecyclerView, various CustomViews...etc

## now added in memory caching of files  

For geting a file from url, call
```java
IFThreadPoolHandler.getInstance().getFile(String tag, String url, FileType type, new Callback.IFCallBack(){
            @Override
            public void onSuccess(@NonNull TaskModel taskModel) {
               super.onSuccess(taskModel)
               Log.d("onSuccess", message );
                }

            @Override
            public void onError(@NonNull String message) {
                super.onError(message);
                Log.d("onError", message);
            }
        });
 ```      
For canceling all task, call
```java
 IFThreadPoolHandler.getInstance().stopAndClearAllThreads();
```
For canceling any particular task, call
```java
IFThreadPoolHandler.getInstance().removeTask(String tag);
 ```       
        

todo: proper testing and profiling

<img align="left" src = "/imageflow.gif" width = "360"/>


