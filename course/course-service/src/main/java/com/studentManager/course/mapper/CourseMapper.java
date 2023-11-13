package com.studentManager.course.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.studentManager.course.entry.CourseEntry;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseMapper extends BaseMapper<CourseEntry> {

    List<CourseEntry> selectCourseByName(String courseName);
}
