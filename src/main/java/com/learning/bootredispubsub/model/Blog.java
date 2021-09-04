package com.learning.bootredispubsub.model;

import org.springframework.data.redis.core.RedisHash;

import lombok.Data;

@Data
@RedisHash("Blog")
public class Blog {
    private int id;
    private String title;
    private String content;
}