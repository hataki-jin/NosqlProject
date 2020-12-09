package com.bjtu.redis;

import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Component
public class FileMonitor {
    @PostConstruct
    public void initFileMonitor(String fileName) {
        // 监控目录
        String rootDir =FileMonitor.class.getClassLoader().getResource("").getPath();
        Integer time = 1;
        long interval = TimeUnit.SECONDS.toMillis(time);
        FileAlterationObserver observer = new FileAlterationObserver(rootDir);
        observer.addListener(new FileListener()); //设置文件变化监听器
        //创建文件变化监听器
        FileAlterationMonitor monitor = new FileAlterationMonitor(interval);
        monitor.addObserver(observer);
        // 开始监控
        try {
            monitor.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}