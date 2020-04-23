package com.krupp.springbootredis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.ArrayList;

public class KillTask implements Runnable {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void run() {
        // 获取版本号
        ArrayList<String> list = new ArrayList<>();
        list.add("kill_num");
        list.add("kill_list");
        //开启乐观锁
        stringRedisTemplate.watch(list);
        //获取产品数量
        int num = Integer.parseInt(stringRedisTemplate.opsForValue().get("kill_num"));
        if (num > 0) {
            // 执行秒杀，开启事务
            stringRedisTemplate.multi();
            //库存减一
            stringRedisTemplate.opsForValue().increment("kill_num", -1);
            //储存客户信息
            stringRedisTemplate.opsForValue().set("kill_list", num + "");
            //批处理
            stringRedisTemplate.exec();
        } else {
            // 关闭连接池
            //SpringBootRedisApplicationTests.pool.shutdown();
        }
    }
}