package com.example.redisapi.services;
import com.example.redisapi.models.RedisEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
    private static final String USER_COUNTER_KEY = "userCounter";

    @Autowired
    private StringRedisTemplate redisTemplate;

    // Increment the counter and return the UserCounter model
    public RedisEntity increment() {
        Long count = redisTemplate.opsForValue().increment(USER_COUNTER_KEY);
        return new RedisEntity(count);
    }

    // Decrement the counter and return the UserCounter model
    public RedisEntity decrement() {
        String value = redisTemplate.opsForValue().get(USER_COUNTER_KEY);
        if(Integer.parseInt(value) <= 0){
            return null;
        }
        Long count = redisTemplate.opsForValue().decrement(USER_COUNTER_KEY);
        return new RedisEntity(count);
    }

    public RedisEntity get() {
        String value = redisTemplate.opsForValue().get(USER_COUNTER_KEY);
        Long count = value != null ? Long.parseLong(value) : 0;
        return new RedisEntity(count);
    }

    public RedisEntity reset() {
        redisTemplate.opsForValue().set(USER_COUNTER_KEY, "0");
        return new RedisEntity(Long.parseLong("0"));
    }
}