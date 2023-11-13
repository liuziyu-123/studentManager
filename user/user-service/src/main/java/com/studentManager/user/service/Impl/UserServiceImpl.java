package com.studentManager.user.service.Impl;


import com.studentManager.common.Entry.UserEntry;
import com.studentManager.user.mapper.UserMapper;
import com.studentManager.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserEntry userLogin(String mobile, String password) {

        UserEntry user = userMapper.userLogin(mobile, password);

        return user;
    }


}
