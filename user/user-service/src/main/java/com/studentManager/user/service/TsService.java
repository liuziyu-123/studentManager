package com.studentManager.user.service;


import com.studentManager.common.Entry.UserEntry;
import com.studentManager.user.entry.RegionEntry;

import java.util.List;

/**
 * 系统管理-师生管理
 */
public interface TsService {

    /**
     * 获取用户列表
     *
     * @param data
     * @return
     */
    List<UserEntry> getTsInfo(UserEntry data);

    /**
     * 新增用户信息
     *
     * @param user
     * @return
     */
    UserEntry insertTs(UserEntry user);

    UserEntry getTsDetail(String userId);

    int updateTs(UserEntry user);

    int deleteUser(String userId);

    /**
     * 获取所有的省份
     *
     * @return
     */
    List<RegionEntry> getProvince();

    /**
     * 获取某个省的所有市
     *
     * @return
     */
    List<RegionEntry> getCity(String provinceName);


    /**
     * 获取某个市的所有区
     *
     * @return
     */
    List<RegionEntry> getArea(String cityName);



    int outEx(String massage);
}
