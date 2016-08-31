package com.newyeah.test.spring.redis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisWebService {
	
	@Autowired
	private RedisTemplate<String,String> redisTemplate ;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean exists(final String key){
		 return redisTemplate.execute(new RedisCallback() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.exists(key.getBytes());
            }
        });
	}
	
	public boolean set(String key,String val){
		redisTemplate.opsForValue().set(key,val) ;
		return true ;
	}
	
	public String get(String key){
		return redisTemplate.opsForValue().get(key) ;
	}
	
	public String hget(String key,String field){
		return (String)redisTemplate.opsForHash().get(key, field) ;
	}
	
	public void hset(String key,String field,String value){
		redisTemplate.opsForHash().put(key, field, value)  ;
	}
	
	public void llset(String key,String value){
		redisTemplate.opsForList().leftPush(key, value) ;
	}
	
	public List<String> lget(String key){
		return lget(key,0,-1) ;
	}
	
	public List<String> lget(String key,int start){
		return lget(key,start,-1) ;
	}
	
	public List<String> lget(String key,int start,int end){
		return redisTemplate.opsForList().range(key, start, end) ;
	}
	
}
