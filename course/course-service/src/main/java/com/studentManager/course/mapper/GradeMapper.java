package com.studentManager.course.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.studentManager.course.entry.GradeEntry;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 1.@Description:
 * 2.@author:liuziyu
 * 3.@Time:2022/11/12
 **/
@Mapper
public interface GradeMapper extends BaseMapper<GradeEntry> {
    List<GradeEntry> selectGrade(String gradeName, String gradeDirectorId);

}
