package com.studentManager.course.service;

import com.studentManager.course.Impl.ServiceUserFeignImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@FeignClient(value = "service-user",fallback = ServiceUserFeignImpl.class)
public interface ServiceUserFeign {

    @RequestMapping("/user/test")
    String test();
}
