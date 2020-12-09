package com.bjtu.redis;

import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Component
public class FileMonitor {
    @PostConstruct
    public void initFileMonitor(String fileName) {
        // monitor's root directory
        String rootDir = Objects.requireNonNull(FileMonitor.class.getClassLoader().getResource("")).getPath();
        int time = 1;
        long interval = TimeUnit.SECONDS.toMillis(time);

        //set the file listener
        FileAlterationObserver observer = new FileAlterationObserver(rootDir);
        observer.addListener(new FileListener());

        //set the file altered monitor
        FileAlterationMonitor monitor = new FileAlterationMonitor(interval);
        monitor.addObserver(observer);

        // start monitoring
        try {
            monitor.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}