# ImageFlow
Similar to pinterest pinboard flow, 
Fully written using various amazing features of android and java like ExecutorService, Executors, Future, 
Handler, Looper, Threads, Runnable, Callable, json, http, BlockingDeque, Hashtable, WeakReferences, 
RecyclerView, various CustomViews...etc

## now added in memory caching of files  

// for geting file simply call

IFThreadPoolHandler.getInstance().getFile(String tag, String url, FileType type , new Callback.IFCallBack(){

            @Override
            public void onSuccess(@NonNull TaskModel taskModel) {

            //    if (taskModel.getType() == FileType.IMAGES) {
              //      Bitmap bitmap = (Bitmap) taskModel.getObject());
              //  }  
                
                }

            @Override
            public void onError(@NonNull String message) {
                super.onError(message);
                Log.d("Error", message);
            }
        });
        
// for canceling all task  just call

 IFThreadPoolHandler.getInstance().stopAndClearAllThreads();

// for canceling any particular task just call
IFThreadPoolHandler.getInstance().removeTask(String tag);
        
        

todo : generify task, proper testing and profiling

![Alt text](Screenshot_1554748776.png?raw=true "Screenshot")
![Alt text](Screenshot_1554748781.png?raw=true "Screenshot")
![Alt text](Screenshot_1554748784.png?raw=true "Screenshot")
![Alt text](Screenshot_1554748787.png?raw=true "Screenshot")

