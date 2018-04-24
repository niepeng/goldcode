package com.niepeng.goldcode.spring.transactional2;

import com.niepeng.goldcode.spring.transactional2.service.UserService;
import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = { "classpath:/test*.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestUserService {
    
        @Resource
	private UserService userService;

	@Test
	public void testUpdateUserinfo() {
	    userService.updateUserinfo();
	    
	}
    

}

