package com.studentManager.user.service;

import com.studentManager.common.Entry.UserEntry;

public interface IUserService {


    /**
     * 用户登录
     *
     * @param mobile   手机号码
     * @param password 密码
     * @return
     */
    UserEntry userLogin(String mobile, String password);


}
