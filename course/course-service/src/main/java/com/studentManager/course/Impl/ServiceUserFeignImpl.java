package com.studentManager.course.Impl;

import com.studentManager.course.service.ServiceUserFeign;
import org.springframework.stereotype.Component;

@Component
public class ServiceUserFeignImpl implements ServiceUserFeign {


    @Override
    public String test() {
        return "调用test接口出错了";
    }
}
