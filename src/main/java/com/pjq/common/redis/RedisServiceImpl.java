package com.pjq.common.redis;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service("redisService")
public class RedisServiceImpl implements IRedisService {
	
	@Autowired
	private RedisTemplate redisTemplate;

	@Override
	public boolean set(String key, String value) {
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			operations.set(key, value);
			result = true;
			Object name = operations.get(key);
			System.out.println(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean set2(String key, String value) {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public String get(String key) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public boolean expire(String key, long expire) {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public <T> boolean setList(String key, List<T> list) {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public <T> List<T> getList(String key, Class<T> clz) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public long lpush(String key, Object obj) {
		// TODO 自动生成的方法存根
		return 0;
	}

	@Override
	public long rpush(String key, Object obj) {
		// TODO 自动生成的方法存根
		return 0;
	}

	@Override
	public String lpop(String key) {
		// TODO 自动生成的方法存根
		return null;
	}
	

}
