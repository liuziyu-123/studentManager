package com.studentManager.user.controller;

import com.studentManager.common.Utils.ApiResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 1.@Description:
 * 2.@author:liuziyu
 * 3.@Time:2022/11/27
 **/
@RestController
@RequestMapping("user")
public class UserController {

    @GetMapping("test")
    public ApiResult test(){
        int i=2/0;
        return ApiResult.success("张玉敏");
    }

    @GetMapping("test2")
    public ApiResult test2(){
        return ApiResult.success("张玉敏,加油");
    }
}
