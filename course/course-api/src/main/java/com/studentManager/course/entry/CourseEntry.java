package com.studentManager.course.entry;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("course")
public class CourseEntry {

    /**
     * 主键Id
     */
    private String id;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 学科Id
     */
    private String subjectId;

    /**
     * 是否生效（0  失效   1 生效）
     */
    private int isEffective;

    /**
     * 年级
     * 1 ：一年级      2：二年级      3：三年级
     * 4：四年级  5：五年级       6：六年级
     * 7 ：七年级     8：八年级 9：九年级
     * 10：高一         11：高二        12 ：高三
     */
    private int gradeLevel;


}
