package com.example;

import com.example.service.impl.VerifyServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
class SeqTemplateApplicationTests {



    @Resource
    StringRedisTemplate template;
    @Resource
    VerifyServiceImpl service;

    @Test
    void contextLoads() {
        service.sendVerifyCode("2691661760@qq.com");
    }

    @Test
    void ReidsWriteTest(){
        template.opsForValue().set("abc","123");
    }

}
