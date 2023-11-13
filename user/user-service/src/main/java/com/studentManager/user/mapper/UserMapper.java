package com.studentManager.user.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.studentManager.common.Entry.UserEntry;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserEntry> {

    UserEntry userLogin(String mobile, String password);

    String getUserName(String userId);

}
