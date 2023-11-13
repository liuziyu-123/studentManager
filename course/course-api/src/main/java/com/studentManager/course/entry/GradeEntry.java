package com.studentManager.course.entry;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("grade")
public class GradeEntry {

    /**
     * 主键Id
     */
    private String id;

    /**
     * 年级名称
     */
    private String gradeName;

    /**
     * 年级主任
     */
    private String gradeDirctorName;

    /**
     * 年级主任Id
     */
    private String gradeDirctorId;


    /**
     * 是否禁用（1 禁用  0 不禁用）
     */
    private String isForbidden;


}
