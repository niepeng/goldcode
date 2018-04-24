package com.niepeng.goldcode.spring.transactional2.service.impl;

import com.niepeng.goldcode.spring.transactional2.dao.UserDao;
import com.niepeng.goldcode.spring.transactional2.dao.UserInformationsDao;
import com.niepeng.goldcode.spring.transactional2.entity.UserEntity;
import com.niepeng.goldcode.spring.transactional2.entity.UserInformationsEntity;
import com.niepeng.goldcode.spring.transactional2.service.UserService;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl implements UserService {
    
    @Resource
		UserDao userDao;
    @Resource
		UserInformationsDao userInformationsDao;

		@Transactional(rollbackFor = RuntimeException.class)
    public void updateUserinfo() {

			UserEntity user = new UserEntity();
			user.setId(1);
			user.setUserName("niepeng");
			user.setPassWord("p2");

			UserInformationsEntity userInfo = new UserInformationsEntity();
			userInfo.setUserId(1);
			userInfo.setAddress("hangzhou");
			userInfo.setEmail("email2");

			userDao.updateUser(user);
			userInformationsDao.updateUserInformations(userInfo);

			if("p2".equals(user.getPassWord())) {
				throw new RuntimeException("test db exception");
			}
		}
    

}

