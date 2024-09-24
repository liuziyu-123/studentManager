package com.studentManager.user.controller;

import com.studentManager.common.Entry.UserEntry;
import com.studentManager.common.Utils.ApiResult;
import com.studentManager.common.Utils.JwtHelper;
import com.studentManager.user.utils.RedisUtil;
import com.studentManager.user.service.IUserService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 登陆
     * @param data
     * @return
     */
    @PostMapping("login")
    public ApiResult login(@RequestBody UserEntry data) {
        UserEntry user = userService.userLogin(data.getMobile(), data.getPassWord());


        if (user != null) {  //登录成功
                String token = JwtHelper.createToken(user.getId(), user.getPassWord(), user.getUserNo());

            redisUtil.set(token, user, 30L, TimeUnit.MINUTES);
            return ApiResult.success(token);
        }
        return ApiResult.fail(-200, "登录失败");
    }

    /**
     * 推出
     * @param request
     * @return
     */
    @PostMapping("loginOut")
    public ApiResult loginOut(HttpServletRequest request) {
        String token = request.getHeader("token");
        //删除redis 中的token
        redisUtil.remove(token);
        return ApiResult.success("成功退出");
    }

    /**
     * 验证码
     * @param word
     * @return
     */
    @GetMapping("verification")
    public ApiResult verification(String word) {
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghigklmnopqrstuvwxyz0123456789";
        Random ran = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 4; i++) {
            int index = ran.nextInt(str.length());
            char ch = str.charAt(index);
            sb.append(ch);

        }
        return ApiResult.success(sb.toString());
    }


    @PostMapping("test")
    public ApiResult testRabbitMQ(){
        rabbitTemplate.convertAndSend("");
        return null;
    }



}