package com.company;

public class Timer {
    private Runnable handler;
    private Long time;
    private Thread t;

    public void put(Runnable r,long time){

new Thread(()->{
    try {
        Thread.sleep(time);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    this.handler = r;
    this.time= time;
    this.t = new Thread((this.handler));
    t.start();
}).start();





    }

    public void start(){
        try {

            this.t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
