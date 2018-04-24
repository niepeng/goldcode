package com.niepeng.goldcode.spring.transactional2.controller;

import com.niepeng.goldcode.spring.transactional2.service.UserService;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/user")
public class Usercontroller {
    @Resource
		UserService userService;
    
    @RequestMapping("/update")
	public @ResponseBody Object update(){
	     userService.updateUserinfo();
	     return "ok";
	}
    
    
          

}

