package com.qf.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppApplicationTests {

	@Autowired
	private RedisTemplate<String,String> redisTemplate;

	@Test
	public void contextLoads() {
		redisTemplate.opsForValue().set("name","lisi",10L,TimeUnit.SECONDS);
		String value = redisTemplate.opsForValue().get("name");
		System.err.println(value);
	}

//	@Test
//	public void userDir(){
//		System.err.println(System.getProperty("user.dir"));
//	}








}
