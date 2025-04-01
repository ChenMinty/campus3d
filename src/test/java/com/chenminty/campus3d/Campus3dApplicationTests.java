package com.chenminty.campus3d;

import com.chenminty.campus3d.mapper.UserMapper;
import com.chenminty.campus3d.entity.User;
import com.chenminty.campus3d.utils.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class Campus3dApplicationTests {

//	@Test
//	void contextLoads() {
//	}

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private JwtUtils jwtUtils;

	@Test
	void testFindUserById() {
		User user = userMapper.findByUserId(1L);
		assertNotNull(user);
		System.out.println(user);
	}

	@Test
	void testJwt() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("USER");
		System.out.println(jwtUtils.generateToken(1L, list));
	}
}
