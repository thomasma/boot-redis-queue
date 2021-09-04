package com.learning.bootredispubsub;

import com.learning.bootredispubsub.model.Blog;
import com.learning.bootredispubsub.pubsub.MessageSubscriber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

@SpringBootApplication
public class BootRedisPubsubApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootRedisPubsubApplication.class, args);
	}

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		return new JedisConnectionFactory();
	}

	@Bean
	public RedisTemplate<String, Blog> redisTemplate() {
		RedisTemplate<String, Blog> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory());
		RedisSerializer<Blog> valueSerializer = new Jackson2JsonRedisSerializer<>(Blog.class);
		template.setValueSerializer(valueSerializer);
		return template;
	}

	@Bean
	RedisMessageListenerContainer redisContainer() {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(jedisConnectionFactory());
		container.addMessageListener(messageListener(), topic());
		return container;
	}

	@Bean
	ChannelTopic topic() {
		return new ChannelTopic("testqueue");
	}

	@Bean
	MessageListenerAdapter messageListener() {
		return new MessageListenerAdapter(new MessageSubscriber());
	}

}
