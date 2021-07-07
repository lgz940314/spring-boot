package com.krupp.thread.controller;

import com.krupp.thread.asyc.SopViewExecutors;
import com.krupp.thread.asyc.ThreadPoolEnum;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author: liuguangzheng   rrefe@163.com
 * @Date: 2021/77/7
 */
@RestController
public class TestController {

    /**
     * 多线程测试
     * date: 2021/7/7
     *
     * @return
     */
    @RequestMapping(value = "/test")
    public String test() {
        ThreadPoolExecutor executor = SopViewExecutors.getInstance()
                .getExecutor(ThreadPoolEnum.ONLY_CREATE_CASE_DYNAMIC_DATA_SOURCE);

        //遍历任务list，带返回值
        //invokeAll(executor);

        //异步单任务带返回值
        //submit(executor);

        //异步遍历不带返回值
        execute(executor);

        Thread t = Thread.currentThread();
        String name = t.getName();
        System.out.println(name + "====>" + "主线程结束!!!");
        return "主线程结束!!!";
    }

    private void execute(ThreadPoolExecutor executor) {
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            executor.execute(() -> {
                hello(finalI);
            });
        }
    }

    private void submit(ThreadPoolExecutor executor) {
        Future<String> submit = executor.submit(() -> hello(99999));
        try {
            System.out.println(submit.get(2, TimeUnit.SECONDS));
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    private void invokeAll(ThreadPoolExecutor executor) {
        List<Callable<Void>> settingInfoCallLst = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            settingInfoCallLst.add(() -> {
                String hello = hello(finalI);
                System.out.println("结果哟:" + hello);
                return null;
            });
        }

        try {
            List<Future<Void>> futures = executor.invokeAll(settingInfoCallLst);
            for (Future<Void> voidFuture : futures) {
                voidFuture.get(2, TimeUnit.SECONDS);
            }
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
    }


    public String hello(int i) {
        System.out.println(Thread.currentThread().getName() + "====>" + i);
        return Thread.currentThread().getName() + "====>" + i;
    }

}
