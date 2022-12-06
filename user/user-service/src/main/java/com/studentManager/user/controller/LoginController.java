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
@RequestMapping("/login")
public class LoginController {
    @GetMapping("test3")
    public ApiResult test(){
        return ApiResult.success("张玉敏");
    }
}
