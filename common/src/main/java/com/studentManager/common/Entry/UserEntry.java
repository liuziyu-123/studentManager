package com.studentManager.common.Entry;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("user")
public class UserEntry {

    @TableField(exist = false)
    private int page;

    @TableField(exist = false)
    private int pageSize;

    /**
     * 主键
     */
    @TableId
    private String id;

    /**
     *手机号
     */
    private String mobile;

    /**
     *姓名
     */
    private String userName;

    /**
     *密码
     */
    private String passWord;

    /**
     *省
     */
    private String provinceName;

    /**
     *市
     */
    private String cityName;

    /**
     *区
     */
    private String areaName;

    /**
     *年龄
     */
    private int age;

    /**
     *性别
     */
    private int sex;

    /**
     *身份
     */
    private int identity;

    /**
     *用户编号
     */
    private String userNo;

    /**
     *生日
     */
    private String birthday;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *创建人
     */
    private String createBy;

    /**
     *修改人
     */
    private String modifyBy;

    /**
     *修改时间
     */
    private Date modifyTime;

    /**
     *是否置顶
     */
    private int isHead;


}
