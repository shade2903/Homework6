package com.company;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class FileWatcher implements Runnable {
    private String path;
    private long pollingInterval;
    private Map<String,Long> firstMap ;
    private Map<String,Long> secondMap ;


    public FileWatcher(String dir,long pollingInterval){
        this.path=dir;
        this.pollingInterval=pollingInterval;
        firstMap = new HashMap<>();
        secondMap = new HashMap<>();

    }


    private Map<String,Long> listMap(){
        File dir = new File(this.path);
        Map<String,Long> map = new HashMap<>();
        if (!dir.exists()) {

            dir.mkdirs();
        }
        File[] list = dir.listFiles();
        for (File f : list) {
            map.put(f.getName(),f.lastModified());
        }
        return map;

    }

    private void timer() throws InterruptedException {
        firstMap.putAll(listMap());
        Thread.sleep(this.pollingInterval);
        secondMap.putAll(listMap());
    }


    public void watch() throws InterruptedException {


        timer();

        if(!secondMap.equals(firstMap)){
            System.out.println("Произошли изменения:");
            for(String x : secondMap.keySet()){
                System.out.println(x);
            }

        }else{
            System.out.println("Изменений нет");

        }


    }



    @Override
    public void run() {
        try {
            watch();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
