package com.pjq.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pjq.service.IUserService;
import com.pjq.vo.UserVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/user")
@Api("userController相关的api")
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private IUserService userService;

	@ResponseBody
	@RequestMapping(value = "getUserInfoList", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "查询用户信息", notes = "查询数据库用户信息")
	@ApiResponses({
        @ApiResponse(code=200,message="成功",response=Object.class),
	})
	public Object getUserInfoList(UserVo vo) {
		log.info("------------------start getUserInfoList() -------------------------vo:"
				+ JSONObject.fromObject(vo).toString());
		List<UserVo> userList = userService.getUserInfoList("");
		log.info("------------------end getUserInfoList() -------------------------");
		return userList;
	}
}
