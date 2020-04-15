package com.krupp.threadpool;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ThreadPoolApplicationTests {

    @Test
    public void contextLoads() {
        Thread thread = new Thread();
        thread.start();
    }

    @Test
    public void newCachedThreadPool() {
        //用来创建一个可以无限扩大的线程池，适用于服务器负载较轻，执行很多短期异步任务。
        ExecutorService pool = Executors.newCachedThreadPool();
        long start = System.currentTimeMillis();
        pool.execute(new Runnable() {
            public void run() {
                int sum = 0;
                for (int i = 0; i < 10; i++) {
                    sum = (int) Math.sqrt(i * i - 1 + i);
                    System.out.println(sum);
                }
            }
        });
        System.out.println("cache: " + (System.currentTimeMillis() - start));
    }

    @Test
    public void newThreadPoolExecutor() throws IOException {
        int corePoolSize = 2;               //核心线程池大小
        int maximumPoolSize = 4;            //最大线程池大小
        long keepAliveTime = 10;            //线程最大空闲时间
        TimeUnit unit = TimeUnit.SECONDS;   //时间单位
        //线程等待队列
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
        //线程创建工厂
        ThreadFactory threadFactory = new NameTreadFactory();
        //拒绝策略
        RejectedExecutionHandler handler = new MyIgnorePolicy();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit,
                workQueue, threadFactory, handler);
        executor.prestartAllCoreThreads(); // 预启动所有核心线程

        for (int i = 1; i <= 10; i++) {
            MyTask task = new MyTask(String.valueOf(i));
            executor.execute(task);
        }

        System.out.println("开始阻塞主线程");
        //System.in.read(); //阻塞主线程
        System.out.println("结束阻塞主线程");
    }

    static class NameTreadFactory implements ThreadFactory {

        private final AtomicInteger mThreadNum = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, "my-thread-" + mThreadNum.getAndIncrement());
            System.out.println(t.getName() + " has been created");
            return t;
        }
    }

    public static class MyIgnorePolicy implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            doLog(r, e);
        }

        private void doLog(Runnable r, ThreadPoolExecutor e) {
            // 可做日志记录等
            System.err.println( r.toString() + " rejected");
//          System.out.println("completedTaskCount: " + e.getCompletedTaskCount());
        }
    }

    static class MyTask implements Runnable {
        private final String name;

        public MyTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                System.out.println(this.toString() + " is running!");
                Thread.sleep(3000); //让任务执行慢点
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "MyTask [name=" + name + "]";
        }
    }
}
