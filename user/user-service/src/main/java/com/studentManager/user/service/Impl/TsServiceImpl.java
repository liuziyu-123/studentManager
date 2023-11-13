package com.studentManager.user.service.Impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import com.github.pagehelper.PageHelper;
import com.studentManager.common.Entry.UserEntry;
import com.studentManager.common.Utils.LocalThread;
import com.studentManager.common.Utils.UUIDUtils;
import com.studentManager.user.entry.RegionEntry;
import com.studentManager.user.mapper.TsMapper;
import com.studentManager.user.service.TsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class TsServiceImpl implements TsService {

    @Autowired
    private TsMapper tsMapper;

    @Override
    public List<RegionEntry> getProvince() {
        List<RegionEntry> regionList = tsMapper.getProvince();
        return regionList;
    }

    @Override
    public List<RegionEntry> getCity(String provinceName) {
        List<RegionEntry> regionList = tsMapper.getCity(provinceName);
        return regionList;
    }

    @Override
    public List<RegionEntry> getArea(String cityName) {
        List<RegionEntry> regionList = tsMapper.getArea(cityName);
        return regionList;
    }


    /**
     * 获取用户列表
     *
     * @param data
     * @return
     */
    @Override
    public List<UserEntry> getTsInfo(UserEntry data) {
        List<UserEntry> userList=null;
        if (data.getPageSize() > 0) {
            PageHelper.startPage(1,10);
            userList = tsMapper.selectList(Wrappers.<UserEntry>lambdaQuery()
                    .eq(UserEntry::getIdentity, data.getIdentity())
                    .like(UserEntry::getUserName,data.getUserName())
                    .like(UserEntry::getMobile,data.getMobile())
                    .eq(UserEntry::getProvinceName, data.getProvinceName())
                    .eq(UserEntry::getCityName, data.getCityName())
                    .eq(UserEntry::getAreaName, data.getAreaName())
                    .eq(UserEntry::getSex, data.getSex())
                    .eq(UserEntry::getIsHead, data.getIsHead()));
        }

        return userList;
    }

    /**
     * 新增用户列表列表
     *
     * @param user
     * @return
     */
    @Override
    public UserEntry insertTs(UserEntry user) {
        Long num = tsMapper.selectCount(Wrappers.<UserEntry>lambdaQuery()
                .eq(UserEntry::getUserName, user.getUserName()));

        if (num > 0) {
            return null;
        }
        user.setId(UUIDUtils.getGUID32());
        user.setAge(Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date())) - Integer.parseInt(user.getBirthday().substring(0, 4)));
        user.setUserNo(user.getId().substring(user.getId().length()-6));
        user.setCreateBy(LocalThread.get().getUserNo());
        user.setModifyBy(LocalThread.get().getUserNo());
        tsMapper.insert(user);
        return user;
    }

    @Override
    public UserEntry getTsDetail(String userId) {
        return tsMapper.selectById(userId);
    }

    @Override
    public int updateTs(UserEntry user) {
        Long num = tsMapper.selectCount(Wrappers.<UserEntry>lambdaQuery()
                .eq(UserEntry::getUserName, user.getUserName()));

        if (num > 0) {
            return -1;
        }
        user.setModifyBy(user.getUserNo());
        user.setModifyTime(new Date());
        return tsMapper.updateById(user);
    }

    @Override
    public int deleteUser(String userId) {
        return tsMapper.deleteById(userId);
    }


    @Override
    public int outEx(String massage) {

        try {
            int i = 3 / 0;
        } catch (Exception e) {
            System.out.println("子函数{}" + e);
            //
            throw e;
        }
        return 0;
    }
}
