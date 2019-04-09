package com.example.imageflow.datapump.runnables;

import com.example.imageflow.datapump.executors.IFThreadPoolHandler;

 public abstract class Task implements Runnable {



   public   abstract void setThreadPoolManager(IFThreadPoolHandler poolHandler);

   public abstract String getTag();





}
