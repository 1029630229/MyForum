package com.pjq.common.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {
	
	@Autowired
	private IRedisService redisService;
	
	@RequestMapping("/redis/set")
	public String redisSet(@RequestParam("value")String value){
		boolean isOk = redisService.set("name", value);
		return "123";
	}
	
	
	@RequestMapping("/redis/set2")
	public String redisSet2(@RequestParam("value")String value){
		boolean isOk = redisService.set2("name", value);
		return "123";
	}
	
	@RequestMapping("/redis/get")
	public String redisGet(){
		String name = redisService.get("name");
		return "123";
	}

}
