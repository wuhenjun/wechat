package com.cloud.authority.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

@EnableCaching
@Configuration
public class RedisConn {
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.database}")
    private Integer database;
    @Value("${spring.redis.port}")
    private Integer port;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.timeout}")
    private Integer timeout;
    @Value("${spring.redis.pool.max-active}")
    private Integer maxActive;
    @Value("${spring.redis.pool.max-wait}")
    private Integer maxWait;
    @Value("${spring.redis.pool.max-idle}")
    private Integer maxIdle;
    @Value("${spring.redis.pool.min-idle}")
    private Integer minIdle;

    @Bean
    public RedisConnectionFactory redisCF(){
        JedisConnectionFactory jcf=new JedisConnectionFactory();
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWait);
        jedisPoolConfig.setMaxTotal(maxActive);
        jedisPoolConfig.setMinIdle(minIdle);
        jcf.setHostName(host);
        jcf.setPort(port);
        jcf.setPassword(password);
        jcf.setDatabase(database);
        jcf.setTimeout(timeout);
        jcf.setPoolConfig(jedisPoolConfig);
        jcf.afterPropertiesSet();
        jcf.setUsePool(true);
        return  jcf;
    }
    @Bean
    public RedisTemplate redisTemplate(){
        final RedisTemplate< String, Object > template =  new RedisTemplate< String, Object >();
        template.setKeySerializer(new StringRedisSerializer());//指定key的序列化
        //template.setValueSerializer(new )
        template.setConnectionFactory( redisCF() );
        return template;
    }

    @Bean
    public CacheManager cacheManager(){

        return new RedisCacheManager(redisTemplate());
    }

}
