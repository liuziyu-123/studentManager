package com.studentManager.course.entry;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("subject")
@Data
public class SubjectEntry {

    /**
     * 主键Id
     */
    private String id;

    /**
     * 课程名称
     */
    private String subjectName;


}
