package com.company;

public class MainTest {


    public static void main(String[] args) throws InterruptedException {
//        FileWatcher fileWatcher = new FileWatcher("test",10000);
//        fileWatcher.watch();
        Timer timer = new Timer();
        timer.put(()-> System.out.println("Hello"),1500);
        timer.put(()-> System.out.println("World!!"),300);
        timer.start();

        System.out.println("Done");

    }

}
