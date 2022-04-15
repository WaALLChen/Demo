package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
class DemoApplicationTests {
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Test
    void contextLoads() {
        log.info(redisTemplate.opsForValue().get("bigshabi"));
    }

}
