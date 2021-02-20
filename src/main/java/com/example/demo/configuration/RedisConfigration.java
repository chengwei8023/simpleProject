package com.example.demo.configuration;

import java.time.Duration;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfigration {
	
	@Value("${spring.redis.host}")
	private String host;
	
	@Value("${spring.redis.port}")
	private Integer port;
	
	@Value("${spring.redis.password:}")
	private String password;
	
	@Value("${spring.redis.timeout}")
	private long timeout;
	
	@Value("${spring.redis.jedis.pool.max-active:8}")
	private Integer maxActive;
	
	@Value("${spring.redis.jedis.pool.max-idle:5}")
	private Integer maxIdle;
	
	@Value("${spring.redis.jedis.pool.max-wait:5}")
	private Integer maxWait;
	
	@Value("${spring.redis.jedis.pool.min-idle:0}")
	private Integer minIdle;
	
	// redis连接工厂配置
	@Bean
    public RedisConnectionFactory redisConnectionFactory() {
		// 数据库连接配置
		RedisStandaloneConfiguration redisConfig = getRedisConfiguration();
		
		// jedis连接池配置
		JedisPoolConfig poolConfig = getJedisPoolConfig();
		
	    JedisClientConfiguration clientConfig = JedisClientConfiguration.builder()
	            .usePooling().poolConfig(poolConfig)
	            .and().readTimeout(Duration.ofMillis(timeout))
	            .build();
		
		return new JedisConnectionFactory(redisConfig, clientConfig);
	}
	
	// redisTemplate配置(序列化配置)
	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		
		// jackson序列化
		GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
		
		// 设置序列化
		redisTemplate.setKeySerializer(stringRedisSerializer);
		redisTemplate.setValueSerializer(genericJackson2JsonRedisSerializer);
		
		// 设置hash序列化
		redisTemplate.setHashKeySerializer(stringRedisSerializer);
		redisTemplate.setHashValueSerializer(genericJackson2JsonRedisSerializer);
		
		// 设置默认序列化
		redisTemplate.setDefaultSerializer(stringRedisSerializer);
		redisTemplate.setEnableDefaultSerializer(true);
		redisTemplate.afterPropertiesSet();
		
		return redisTemplate;
	}
	
	// jedis连接池配置
	private JedisPoolConfig getJedisPoolConfig() {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
	   
		// 最大空闲连接数
	    poolConfig.setMaxIdle(maxIdle);
	    
	    // 最小空闲连接数
	    poolConfig.setMinIdle(minIdle);
	    
	    // 设置最大等待时间
	    poolConfig.setMaxWaitMillis(maxWait);
	   
	    // 最大活跃连接数
	    poolConfig.setMaxTotal(maxActive);
	    
	    // 从连接池获取连接时检测连接的有效性
	    poolConfig.setTestOnBorrow(true);
	    
	    // 从连接池归还连接时检测连接的有效性
	    poolConfig.setTestOnReturn(true);
	    
	    // 在连接池连接空闲时检测连接的有效性
	    poolConfig.setTestWhileIdle(true);
	    return poolConfig;
	}

	// redis连接配置
	private RedisStandaloneConfiguration getRedisConfiguration() {
		// 哨兵redis
		// RedisSentinelConfiguration redisConfig = new RedisSentinelConfiguration();
	    
		// 集群redis
	    // RedisClusterConfiguration redisConfig = new RedisClusterConfiguration();
		
		// 单点redis
		RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration();
		if (!StringUtils.isEmpty(password)) {
			redisConfig.setPassword(RedisPassword.of(password));
		}
		
		redisConfig.setHostName(host);
		redisConfig.setPort(port);
		return redisConfig;
	}
	

}
