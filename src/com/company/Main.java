package com.company;

public class Main {
    public static void main(String[] args) {
        int i = 0;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread t = new Thread(

        );
        try {
            t.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.notify();
        Thread.yield();
    }
}
