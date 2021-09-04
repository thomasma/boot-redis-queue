package com.learning.bootredispubsub.pubsub;

import com.learning.bootredispubsub.model.Blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;

@Component
public class MessagePublisher {
    @Autowired
    private RedisTemplate<String, Blog> redisTemplate;

    @Autowired
    private ChannelTopic topic;

    public MessagePublisher() {
    }

    public void handleMessage(final Blog message) {
        redisTemplate.convertAndSend(topic.getTopic(), message);
    }

}
