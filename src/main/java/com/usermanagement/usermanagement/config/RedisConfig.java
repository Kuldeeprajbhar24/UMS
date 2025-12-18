package com.usermanagement.usermanagement.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import java.time.Duration;
@Configuration
@EnableCaching
public class RedisConfig {

    @Bean
    public RedisCacheConfiguration cacheConfiguration(ObjectMapper objectMapper) {

        GenericJackson2JsonRedisSerializer serializer =
                new GenericJackson2JsonRedisSerializer(objectMapper);

        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofHours(1))
                .serializeKeysWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(
                                RedisSerializer.string()))
                .serializeValuesWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(
                                serializer));
    }

    @Bean
    public LettuceConnectionFactory redisConnectionFactory(
            @Value("${spring.redis.host}") String host,
            @Value("${spring.redis.port}") int port,
            @Value("${spring.redis.password:}") String password) {

        RedisStandaloneConfiguration config =
                new RedisStandaloneConfiguration(host, port);
        if (!password.isBlank()) {
            config.setPassword(password);
        }
        return new LettuceConnectionFactory(config);
    }
}

