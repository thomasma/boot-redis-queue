package com.learning.bootredispubsub.api;

import com.learning.bootredispubsub.model.Blog;
import com.learning.bootredispubsub.pubsub.MessagePublisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PubSubControllerApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(PubSubControllerApi.class);

    private MessagePublisher messagePublisher;

    public PubSubControllerApi(MessagePublisher messagePublisher) {
        this.messagePublisher = messagePublisher;
    }

    @PostMapping("/blog")
    public void publishAMessage(@RequestBody Blog blog) {
        LOGGER.info("About to publish {}", blog);
        messagePublisher.handleMessage(blog);
    }
}
