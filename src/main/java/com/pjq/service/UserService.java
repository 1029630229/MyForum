package com.pjq.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pjq.dao.IUserDao;
import com.pjq.vo.UserVo;

@Service("userService")
public class UserService implements IUserService {
	
	@Autowired
	IUserDao userDao;
	
	@Override
	public List<UserVo> getUserInfoList(String name) {
		return userDao.getUserInfoList(name);
	}

}
