package com.pjq.service;

import java.util.List;

import com.pjq.vo.UserVo;

public interface IUserService {
	//查询信息
	public List<UserVo> getUserInfoList(String name);
}
