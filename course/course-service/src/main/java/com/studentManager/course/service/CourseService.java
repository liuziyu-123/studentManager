package com.studentManager.course.service;

import com.studentManager.course.entry.CourseEntry;
import java.util.List;

public interface CourseService {

    List<CourseEntry> getCourse(String courseName);

    int insertCourse(CourseEntry courseEntry);

}
