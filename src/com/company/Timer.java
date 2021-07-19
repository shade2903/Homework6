package com.company;

public class Timer  {
    private Runnable handler;
    private Long time;
    private Thread t;

   public void put(Runnable r,long time){
       this.handler = r;
       this.time= time;
       this.t = new Thread(this.handler);

       Thread breakThread = new Thread(()-> {
           try {
               Thread.sleep(time);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }

       });
       breakThread.start();
       try {
           breakThread.join();
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
       this.t.start();



   }

   public void start(){
       try {

           this.t.join();
       } catch (InterruptedException e) {
           e.printStackTrace();
       }

   }


}
