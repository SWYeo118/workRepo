package com.darsgateway.controller;

import com.darsgateway.model.ViewInfoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RedisController {

    RedisTemplate<String, String> redisTemplate;

    @Autowired
    public RedisController(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostMapping("/redisTest")
    public ResponseEntity<?> addRedisKey(@RequestBody ViewInfoModel vo) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(vo.getCallId(), vo.getOpenedAt());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/redisTest/{key}")
    public ResponseEntity<?> getRedisKey(@PathVariable String key) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String value = valueOperations.get(key);
        return new ResponseEntity<>(value, HttpStatus.OK);
    }
}
