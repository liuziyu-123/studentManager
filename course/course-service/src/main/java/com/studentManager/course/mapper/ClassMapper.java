package com.studentManager.course.mapper;

;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.studentManager.course.entry.ClassDao;
import com.studentManager.course.entry.ClassEntry;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClassMapper extends BaseMapper<ClassEntry> {

    List<ClassEntry> selectAllClass(String gradeId,String className, String headTeacherName);

    List<ClassDao> getClassByGradeId(String gradeId);
}
