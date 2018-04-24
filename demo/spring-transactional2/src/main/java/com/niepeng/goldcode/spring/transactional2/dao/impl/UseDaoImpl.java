package com.niepeng.goldcode.spring.transactional2.dao.impl;

import com.niepeng.goldcode.spring.transactional2.dao.BaseDao;
import com.niepeng.goldcode.spring.transactional2.dao.UserDao;
import com.niepeng.goldcode.spring.transactional2.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service ("userDao")
public class UseDaoImpl extends BaseDao implements UserDao {

    public void updateUser(UserEntity user) {
	      this.test1Update(user);
    }

}

