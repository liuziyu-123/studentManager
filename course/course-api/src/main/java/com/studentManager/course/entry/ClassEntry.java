package com.studentManager.course.entry;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("clas")
public class ClassEntry {

    /**
     * 主键Id
     */
    private String id;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 班主任Id
     */
    private String headTeacherId;

    /**
     * 班主任姓名
     */
    private String headTeacherName;


    /**
     * 是否禁用 （0 不禁用   1 禁用）
     */
    private int isForbidden;


    /**
     * 年级Id
     */
    private String gradeId;
}
