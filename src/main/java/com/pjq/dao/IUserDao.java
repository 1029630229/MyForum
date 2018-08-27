package com.pjq.dao;

import java.util.List;

import com.pjq.vo.UserVo;

public interface IUserDao {
	
	//查询信息
	public List<UserVo> getUserInfoList(String name);
}
