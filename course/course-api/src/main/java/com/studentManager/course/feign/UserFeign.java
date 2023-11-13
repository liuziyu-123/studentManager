package com.studentManager.course.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name="user")
@RequestMapping("/user")
public interface UserFeign {


}
