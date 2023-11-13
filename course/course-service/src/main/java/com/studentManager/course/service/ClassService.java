package com.studentManager.course.service;


import com.studentManager.course.entry.ClassDao;
import com.studentManager.course.entry.ClassEntry;

import java.util.List;

public interface ClassService {

    /**
     * 新增班级和班主任
     *
     * @param  classEntry
     * @return
     */
    int insertClass(ClassEntry classEntry);

    int updateHeadTeacher(ClassEntry classEntry);

    List<ClassEntry> selectAllClass(String gradeId,String className, String headTeacherName);

    int updateStatus(String classId, int status);

    List<ClassDao> getClassByGradeId(String gradeId);


}
