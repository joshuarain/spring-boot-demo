package com.zhl.springboot.redis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author 张雷
 * @date 2019/12/25 17:16
 */
public class RedisUtils {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    void test(){
        stringRedisTemplate.opsForCluster();
        stringRedisTemplate.opsForGeo();
        stringRedisTemplate.opsForHyperLogLog();
        stringRedisTemplate.opsForStream();
        stringRedisTemplate.opsForValue().set("key","value");
        stringRedisTemplate.opsForList().leftPush("key","value");
        stringRedisTemplate.opsForHash().put("h","hk","hv");
    }
}
