package com.studentManager.user.controller;

import com.studentManager.common.Entry.UserEntry;
import com.studentManager.common.Utils.*;
import com.studentManager.user.entry.RegionEntry;
import com.studentManager.user.service.TsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user/ts")
public class TsController {

    @Autowired
    private TsService tsService;
//
//    @Value("${white.list.login}")
//    private String login;

    /**
     * 获取用户列表
     *
     * @param userVo 查询数据
     * @return
     */
    @PostMapping("tsInfo")
    public ApiResult getTsInfo(@RequestBody UserEntry userVo) {
        UserEntry userInfo = LocalThread.get();
        if (userInfo == null) {
            return ApiResult.fail(ErrorConstant.NO_GET_LOGIN, "用户不存在");
        }

        List<UserEntry> userList = tsService.getTsInfo(userVo);

        return ApiResult.success(new PagingResult<>(userList));
    }


    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    @PostMapping("insertTs")
    public ApiResult insertTs(@RequestBody UserEntry user) {
        if (user == null) {
            return ApiResult.fail(ErrorConstant.NO_GET_LOGIN);
        }
        UserEntry item = tsService.insertTs(user);
        return ApiResult.success(item);
    }

    /**
     * 获取单个用户信息的详情
     * @param userId
     * @return
     */
    @GetMapping("tsDetail")
    public ApiResult getTsDetail(@RequestParam String userId) {
        UserEntry userInfo = LocalThread.get();
        if (userInfo == null) {
            return ApiResult.fail(ErrorConstant.NO_GET_LOGIN, "用户不存在");
        }

        return ApiResult.success(tsService.getTsDetail(userId));
    }


    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    @PostMapping("updateTs")
    public ApiResult updateTs(@RequestBody UserEntry user) {
        if (user == null) {
            return ApiResult.fail(ErrorConstant.NO_GET_LOGIN);
        }
        int count = tsService.updateTs(user);
        return ApiResult.success(count);
    }


    /**
     * 删除用户
     *
     * @param userId
     * @return
     */
    @GetMapping("deleteTs")
    public ApiResult deleteTs(@RequestParam String userId) {
        if (StringUtils.isBlank(userId)) {
            return ApiResult.fail(ErrorConstant.EMPTY);
        }
        int count = tsService.deleteUser(userId);
        return ApiResult.success(count);
    }

    /**
     * 获取全国所有的省
     *
     * @return
     */
    @GetMapping("getProvince")
    public ApiResult getProvince() {
        List<RegionEntry> regionList = tsService.getProvince();
        return ApiResult.success(regionList);
    }


    /**
     * 获取某个省的所有市
     *
     * @param provinceName 省份名称
     * @return
     */
        @GetMapping("getCity")
    public ApiResult getCity(String provinceName) {

        List<RegionEntry> regionList = tsService.getCity(provinceName);
        return ApiResult.success(regionList);
    }


    /**
     * 获取某个市的所有区
     *
     * @param cityName 市名
     * @return
     */
    @GetMapping("getArea")
    public ApiResult getArea(String cityName) {

        List<RegionEntry> regionList = tsService.getArea(cityName);
        return ApiResult.success(regionList);
    }


    @PostMapping("outEx")
    public ApiResult outEx(String massage) {

        try {
            tsService.outEx(massage);
        } catch (Exception e) {
            System.out.println("出发了");
            e.printStackTrace();
        }
        return ApiResult.success(massage);
    }


}
