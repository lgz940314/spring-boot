package com.krupp.springbootredis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRedisApplicationTests {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void contextLoads() {
        redisTemplate.opsForValue().set("test", 1);
        int test = (int) redisTemplate.opsForValue().get("test");
        stringRedisTemplate.opsForValue().set("lgz", "威武霸气", 10, TimeUnit.SECONDS);
        Object lgz = stringRedisTemplate.opsForValue().get("lgz");
        System.out.println(lgz);
        System.out.println(test);
        try {
            Thread.sleep(10000);
            //过期后拿到是null
            System.out.println("十秒后" + stringRedisTemplate.opsForValue().get("lgz"));
        } catch (InterruptedException e) {

        }
    }


    /**
     * 加锁
     *
     * @param key
     * @param value 当前时间+超时时间
     * @return
     */
    public boolean lock(String key, String value) {
        // 如果键不存在则新增,存在则不改变已经有的值。
        if (redisTemplate.opsForValue().setIfAbsent(key, value)) {
            return true;
        }
        //currentValue=A   这两个线程的value都是B  其中一个线程拿到锁
        String currentValue = stringRedisTemplate.opsForValue().get(key);
        //如果锁过期
        if (!StringUtils.isEmpty(currentValue)
                && Long.parseLong(currentValue) < System.currentTimeMillis()) {
            //获取上一个锁的时间     getAndSet（）以原子方式设置为给定值，并返回以前的值。
            String oldValue = stringRedisTemplate.opsForValue().getAndSet(key, value);
            return !StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue);
        }

        return false;
    }

    /**
     * 解锁
     *
     * @param key
     * @param value
     */
    public void unlock(String key, String value) {
        try {
            String currentValue = stringRedisTemplate.opsForValue().get(key);
            if (!StringUtils.isEmpty(currentValue) && currentValue.equals(value)) {
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        } catch (Exception e) {
            //log.error("【redis分布式锁】解锁异常, {}", e);
        }
    }


}
