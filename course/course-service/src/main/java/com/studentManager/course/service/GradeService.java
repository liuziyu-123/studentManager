package com.studentManager.course.service;


import com.studentManager.course.entry.GradeEntry;

import java.util.List;

/**
 * 1.@Description:
 * 2.@author:liuziyu
 * 3.@Time:2022/12/17
 **/
public interface GradeService {

    /**
     * 新增年级和年级主任
     *
     * @param gradeEntry       年级
     * @return
     */
    int insertGrade(GradeEntry gradeEntry);

    int updateGradeInfo(GradeEntry gradeEntry);

    List<GradeEntry> selectGrade(String gradeName, String gradeDirectorId);
}
