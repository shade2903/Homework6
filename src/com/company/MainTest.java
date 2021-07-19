package com.company;

public class MainTest {


    public static void main(String[] args) throws InterruptedException {
        FileWatcher fileWatcher = new FileWatcher("test",10000);
        fileWatcher.watch();




    }
}
