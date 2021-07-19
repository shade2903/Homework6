package com.company;

import java.util.ArrayList;
import java.util.Collections;

public class Timer {
    private Runnable handler;
    private Long time;
    private Thread t;
    ArrayList<Long> secondList = new ArrayList<>();

    public void put(Runnable r,long time){
        secondList.add(time);

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

    public void start() {
        try {
            Thread.sleep(Collections.max(secondList)+100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
