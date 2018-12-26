package org.opengg;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.opengg.bean.User;
import org.opengg.controller.UserController;
import org.opengg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedistestApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private UserController userController;

    @Test
    public void contextLoads() {
        User o = (User)redisTemplate.opsForValue().get("2");
        System.out.println(0);
    }

}

