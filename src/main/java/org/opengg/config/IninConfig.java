package org.opengg.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import javax.validation.Valid;
import java.util.Map;

@Configuration
public class IninConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Primary
    @Bean
    public RedisTemplate redisTemplate() {

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMinIdle(10);
        jedisPoolConfig.setMaxIdle(200);
        jedisPoolConfig.setMaxTotal(600);
        jedisPoolConfig.setTestOnBorrow(true);

        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName(host);
        jedisConnectionFactory.setPort(6379);
        //jedisConnectionFactory.setPassword(dsMap.get("redis.pass").toString());
        jedisConnectionFactory.setPoolConfig(jedisPoolConfig);
        jedisConnectionFactory.setTimeout(60000);
        jedisConnectionFactory.setDatabase(0);
        jedisConnectionFactory.afterPropertiesSet();		//Cannot get Jedis connection

        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new MyJackson2JsonRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Primary
    @Bean
    public CacheManager cacheManager(){
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate());
        redisCacheManager.setDefaultExpiration(6000);
        return redisCacheManager;
    }
}
