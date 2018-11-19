package com.luming.config.session;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author lei.jia@insentek.com
 * @date 2018/11/15
 * 修正历史：
 * 	2018/11/15：文件创建
 */

@EnableRedisHttpSession
public class LettuceConfig {
    
    
    @Value("${lettuce.pool.maxTotal}")
    int maxTotal;
    @Value("${lettuce.pool.maxIdle}")
    int maxIdle;
    @Value("${lettuce.pool.minIdle}")
    int minIdle;
    @Value("${spring.redis.host}")
    String host;
    @Value("${spring.redis.port}")
    int port;
    
    
    /**
     * 通过Factory生成lettuce
     * @param redisSentinelConfiguration
     * @param lettuceClientConfiguration
     * @return
     */
    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory(RedisStandaloneConfiguration redisSentinelConfiguration, LettuceClientConfiguration lettuceClientConfiguration) {
        return new LettuceConnectionFactory();
        //return new LettuceConnectionFactory(redisSentinelConfiguration, lettuceClientConfiguration);
    }
    
    
    /**
     * 配置并获取redisConfig
     * @return
     */
    @Bean
    public RedisStandaloneConfiguration redisSentinelConfiguration() {
        //RedisSentinelConfiguration 主从redis的话用这个
        //RedisStandaloneConfiguration 单redis服务器的用这个
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setDatabase(0);
        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setPort(port);
        redisStandaloneConfiguration.setPassword(RedisPassword.of(""));
        return redisStandaloneConfiguration;
    }
    
    
    /**
     *
     * @return
     */
    @Bean
    public LettuceClientConfiguration lettuceClientConfiguration() {
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxIdle(maxTotal);
        poolConfig.setMinIdle(maxIdle);
        poolConfig.setMaxTotal(minIdle);
        return LettucePoolingClientConfiguration.builder().poolConfig(poolConfig).build();
    }
    
    
    /**
     * 使用JdkSerializationRedisSerializer序列化 存取字符串类型
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(redisConnectionFactory);
        return stringRedisTemplate;
    }
    
    
    /**
     * 使用StringRedisSerializer序列化 存取对象
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }
    
    
}
