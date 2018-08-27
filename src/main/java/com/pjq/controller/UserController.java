package com.pjq.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pjq.service.IUserService;
import com.pjq.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	
	@Autowired
	private IUserService userService;
	
	@ResponseBody
	@RequestMapping("/getUserInfoList")
	public Object getUserInfoList() {
		List<UserVo> userList = userService.getUserInfoList("");
		System.out.println(userList.size());
		return userList;
	}
}
