package com.studentManager.course.service.impl;


import com.studentManager.common.Utils.UUIDUtils;
import com.studentManager.course.entry.CourseEntry;
import com.studentManager.course.mapper.CourseMapper;
import com.studentManager.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<CourseEntry> getCourse(String courseName) {

        return courseMapper.selectCourseByName(courseName);
    }

    @Override
    public int insertCourse(CourseEntry courseEntry) {
        courseEntry.setId(UUIDUtils.getGUID32());
        return courseMapper.insert(courseEntry);
    }
}
