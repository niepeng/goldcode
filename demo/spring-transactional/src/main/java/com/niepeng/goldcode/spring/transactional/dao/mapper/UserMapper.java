package com.niepeng.goldcode.spring.transactional.dao.mapper;

import com.niepeng.goldcode.spring.transactional.dao.UserDO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface UserMapper {


  UserDO selectUser(Long id);

  List<UserDO> getUserList();

  void create(@Param("name") String name, @Param("age") Integer age);

  void deleteByName(String name);

}
