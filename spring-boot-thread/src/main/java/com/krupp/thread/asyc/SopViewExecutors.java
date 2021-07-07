package com.krupp.thread.asyc;

import com.google.common.collect.Maps;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池管理类
 * 单例
 *
 * @author fengchen
 * @date 2018/05/25
 */
public class SopViewExecutors {

    /**
     * 线程池map
     */
    private HashMap<ThreadPoolEnum, ThreadPoolExecutor> executorMap = Maps.newHashMap();

    /**
     * 初始化各线程池
     */
    private SopViewExecutors() {
        for (ThreadPoolEnum tEnum : ThreadPoolEnum.values()) {
            ThreadPoolExecutor executor = new ThreadPoolExecutor(tEnum.getCoreSize(), tEnum.getMaxSize(),
                    tEnum.getKeepAliveSeconds(), TimeUnit.SECONDS, new ArrayBlockingQueue<>(tEnum.getQueueSize()),
                    new ThreadFactory() {
                        final AtomicInteger threadNumber = new AtomicInteger(1);

                        @Override
                        public Thread newThread(@NotNull Runnable r) {
                            return new Thread(r, tEnum.getPoolPrefixName() + threadNumber.incrementAndGet());
                        }
                    });
            executorMap.put(tEnum, executor);
        }
    }

    public static final SopViewExecutors getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 根据线程池类型获取线程池
     *
     * @param threadPoolEnum
     * @return
     */
    public ThreadPoolExecutor getExecutor(ThreadPoolEnum threadPoolEnum) {
        return executorMap.get(threadPoolEnum);
    }

    private static class SingletonHolder {
        private static final SopViewExecutors INSTANCE = new SopViewExecutors();
    }
}
