package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

@Configuration
public class RedisConfig {
    @Value("${redis.url}")
    private String url;
    @Value("${redis.password}")
    private String password;
    @Value("${redis.database:0}")
    private int database;
    //---------------------- 下面是jedis作为客户端所需要的参数（redisson、lettuce也可以作为redis服务端二者基于netty，可异步）
    //设置池可以分配的对象数的上限
    @Value("${jedis.max-active:8}")
    public int maxActive;
    //空闲实例数的上限
    @Value("${jedis.max-idle:8}")
    public int maxIdle;
    //最小空闲对象数
    @Value("${jedis.min-idle:4}")
    public int minIdle;
    //空闲的最短时间
    @Value("${jedis.minEvictableTimeMillis:10000}")
    public long minEvictableTimeMillis;
    //空闲进程运行之间睡眠的毫秒数
    @Value("${jedis.timeBetweenEvictionRunsMillis:20000}")
    public long timeBetweenEvictionRunsMillis;
    //执行超时时间
    @Value("${jedis.readTimeout:2000}")
    public int readTimeout;
    //连接超时时间
    @Value("${jedis.connectTimeout:2000}")
    public int connectTimeout;

    /**
     * 连接工厂
     *
     * @return JedisConnectionFactory
     */
    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration standaloneConfiguration = new RedisStandaloneConfiguration();
        String[] split = url.split(":");
        // 节点
        standaloneConfiguration.setHostName(split[0]);
        // 端口
        standaloneConfiguration.setPort(Integer.parseInt(split[1]));
        // 登陆密码
        standaloneConfiguration.setPassword(RedisPassword.of(password));
        // 数据库
        standaloneConfiguration.setDatabase(database);
        //基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxActive);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);
        config.setTestOnBorrow(true);
        config.setTestWhileIdle(true);
        config.setMaxWait(Duration.ofMillis(-1));
        config.setTimeBetweenEvictionRuns(Duration.ofMillis(timeBetweenEvictionRunsMillis));
        config.setMinEvictableIdleTime(Duration.ofMillis(minEvictableTimeMillis));
        //池配置
        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder builder = JedisClientConfiguration.builder().usePooling();
        builder.poolConfig(config);
        builder.and().readTimeout(Duration.ofMillis(readTimeout)).connectTimeout(Duration.ofMillis(connectTimeout));
        return new JedisConnectionFactory(standaloneConfiguration, builder.build());
    }

    /**
     * 操作类
     *
     * @param jedisConnectionFactory 连接工厂
     * @return RedisTemplate
     */
    @Bean
    RedisTemplate<String, String> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        //键值序列化方式
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringSerializer);
        template.setValueSerializer(stringSerializer);
        template.setHashKeySerializer(stringSerializer);
        template.setHashValueSerializer(stringSerializer);
        template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
        template.setEnableDefaultSerializer(true);
        template.setConnectionFactory(jedisConnectionFactory);
        return template;
    }


}
