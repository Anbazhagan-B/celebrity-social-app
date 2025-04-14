package com.sotroid.interaction_service.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.IntStream;

@Service
public class RedisPrefixService {
    final RedisTemplate<String, String> redisTemplate;

    public RedisPrefixService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void addUserNameToPrefixes(String username) {
        String lowerCaseUsername = username.toLowerCase(Locale.ROOT);

        IntStream.rangeClosed(1, lowerCaseUsername.length()).forEach(i ->{
            String prefix = lowerCaseUsername.substring(0, i);
            String key = "prefix:" + prefix;
            redisTemplate.opsForList().leftPush(key, username);
        });
    }

    public List<String> getSuggestions(String prefix, int limit) {
        String key = "prefix:" + prefix.toLowerCase(Locale.ROOT);
        return redisTemplate.opsForList().range(key, 0, limit);
    }

    public void addUsernamesToPrefixes(List<String> usernames) {
        for (String username : usernames) {
            addUserNameToPrefixes(username);
        }
    }

    public void clearAllPrefixKeys() {
        Set<String> keys = redisTemplate.keys("prefix:*");
        if (!keys.isEmpty()) {
            redisTemplate.delete(keys);
        }
    }
}
