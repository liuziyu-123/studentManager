package com.studentManager.course.entry;

import lombok.Data;

/**
 * 1.@Description:
 * 2.@author:liuziyu
 * 3.@Time:2022/12/17
 **/
@Data
public class ClassDao {

    /**
     * 主键Id
     */
    private String id;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 班级序号
     */
    private int classNo;
}
