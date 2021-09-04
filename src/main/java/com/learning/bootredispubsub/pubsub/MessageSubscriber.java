package com.learning.bootredispubsub.pubsub;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.bootredispubsub.model.Blog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class MessageSubscriber implements MessageListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageSubscriber.class);

    ObjectMapper objectMapper = new ObjectMapper();

    public void onMessage(final Message message, final byte[] pattern) {
        try {
            Blog blog = objectMapper.readValue(message.toString(), Blog.class);
            LOGGER.info("I got a message received - {}", blog);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
