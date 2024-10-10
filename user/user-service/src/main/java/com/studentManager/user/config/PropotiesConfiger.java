package com.studentManager.user.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "white")
@Data
public class PropotiesConfiger {
  private Map<String,String> list;
}
