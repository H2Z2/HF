package com.tedu.service.impl;

import com.tedu.mapper.UserMapper;
import com.tedu.pojo.User;
import com.tedu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Created by LYJ on 2017/3/19.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /*
    根据用户名和密码查询用户信息
     */
	@Override
	public User findByUsernameAndPassword(String username, String password) {
		return userMapper.findByUsernameAndPassword(username,password);
	}

	/*
	保存用户信息
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveUserByUsernameAndPassword(String username, String password) {
		//生成随机用户id
		String id= UUID.randomUUID().toString();
		//保存信息到user_p用户表
		userMapper.saveUserByUsernameAndPassword(id,username,password);
		//保存信息到user_info_p表
		userMapper.saveUserInfoByUsernameAndPassword(id);
	}
}
