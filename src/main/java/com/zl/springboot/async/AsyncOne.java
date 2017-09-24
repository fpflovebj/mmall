package com.zl.springboot.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import java.util.Random;

@Component
public class AsyncOne {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private Random random = new Random();

    @Async
    public void async1() throws InterruptedException {
        logger.info("adync1START");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        logger.info("adync1END,时间:"+(end-start)+"ms");
    }
    @Async
    public void async2() throws InterruptedException {
        logger.info("adync2START");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        logger.info("adync2END,时间:"+(end-start)+"ms");
    }
    @Async
    public void async3() throws InterruptedException {
        logger.info("adync3START");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        logger.info("adync3END,时间:"+(end-start)+"ms");
    }
}
