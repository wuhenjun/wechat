package com.cloud.user;

import com.cloud.user.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserApplicationTests {

	@Autowired
	private RedisUtil redisUtil;

	@Test
	public void contextLoads() {
		redisUtil.remove("token");
		System.out.println(redisUtil.get("token"));

	}

}
