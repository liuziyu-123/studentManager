package com.studentManager.user.entry;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 *地区
 */
@Data
@TableName("region")
public class RegionEntry {

    /**
     * 行政区划代码
     */
    private String id;

    /**
     * 名称
     */
    private String cname;

    /**
     * 上级id
     */
    private String parentId;

    /**
     * 等级
     */
    private String levelId;

}
