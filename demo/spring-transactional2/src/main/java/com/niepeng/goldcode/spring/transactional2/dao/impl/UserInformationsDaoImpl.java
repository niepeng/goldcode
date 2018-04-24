package com.niepeng.goldcode.spring.transactional2.dao.impl;

import com.niepeng.goldcode.spring.transactional2.dao.BaseDao;
import com.niepeng.goldcode.spring.transactional2.dao.UserInformationsDao;
import com.niepeng.goldcode.spring.transactional2.entity.UserInformationsEntity;
import org.springframework.stereotype.Service;

@Service ("userInformationsDao")
public class UserInformationsDaoImpl extends BaseDao implements UserInformationsDao {
    
    public void updateUserInformations(UserInformationsEntity userInfo) {
	      this.test2Update(userInfo);
    }

}

