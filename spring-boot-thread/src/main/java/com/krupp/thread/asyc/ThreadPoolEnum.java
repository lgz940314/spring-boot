package com.krupp.thread.asyc;

/**
 * @author fengchen
 * @date 2018/05/25
 */
public enum ThreadPoolEnum {

    /**
     * 通用线程池
     */
    ONLY_CREATE_CASE_DYNAMIC_DATA_SOURCE(128, 128, 120L, 1000, "dynamic-pool-thread-");

//    GET_BATCH_ISSUE_STATUS_DATA_SOURCE(8, 16, 120L, 1000,"dynamic-pool-thread-"),

//    GET_BATCH_NR_SUBMIT_FLOW_DATA_SOURCE(8, 16, 120L, 1000),

//    SLA_COMPUTE_DATA_SOURCE(8, 16, 120L, 1000,"dynamic-pool-thread-"),;

    /**
     * 线程池种类枚举
     */
//    ICUB_CUSTOMER_VIEW(8, 16, 120L, 1000);

    /**
     * 核心线程数
     */
    private Integer coreSize;
    /**
     * 最大线程数
     */
    private Integer maxSize;
    /**
     * 存活时间
     */
    private Long keepAliveSeconds;
    /**
     * 队列缓存大小
     */
    private Integer queueSize;

    /**
     * 线程池前缀名
     */
    private String poolPrefixName;

    ThreadPoolEnum(Integer coreSize, Integer maxSize, Long keepAliveSeconds, Integer queueSize, String namePrefix) {
        this.coreSize = coreSize;
        this.maxSize = maxSize;
        this.keepAliveSeconds = keepAliveSeconds;
        this.queueSize = queueSize;
        this.poolPrefixName = namePrefix;
    }

    public Integer getCoreSize() {
        return coreSize;
    }

    public Integer getMaxSize() {
        return maxSize;
    }

    public Long getKeepAliveSeconds() {
        return keepAliveSeconds;
    }

    public Integer getQueueSize() {
        return queueSize;
    }

    public String getPoolPrefixName() {
        return poolPrefixName;
    }
}
