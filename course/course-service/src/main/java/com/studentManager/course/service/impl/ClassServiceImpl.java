package com.studentManager.course.service.impl;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.studentManager.common.Utils.UUIDUtils;
import com.studentManager.course.entry.ClassDao;
import com.studentManager.course.entry.ClassEntry;
import com.studentManager.course.mapper.ClassMapper;
import com.studentManager.course.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClassServiceImpl implements ClassService {


    @Autowired
    private ClassMapper classMapper;

    /**
     * 新增班级和班主任
     *
     * @param classEntry
     * @return
     */
    @Override
    public int insertClass(ClassEntry classEntry) {

        Long count = classMapper.selectCount(Wrappers.<ClassEntry>lambdaQuery()
                .eq(ClassEntry::getGradeId, classEntry.getGradeId()));


            ClassEntry clasEntry = new ClassEntry();
            clasEntry.setId(UUIDUtils.getGUID32());
            clasEntry.setGradeId(classEntry.getGradeId());
            clasEntry.setHeadTeacherId(classEntry.getHeadTeacherId());
            clasEntry.setClassName(classEntry.getClassName()+count.intValue()+1+"班");

        return classMapper.insert(clasEntry);
    }

    /**
     * 修改班级的班主任
     *
     * @param classEntry
     * @return
     */
    @Override
    public int updateHeadTeacher(ClassEntry classEntry) {
        return classMapper.updateById(classEntry);
    }

    @Override
    public List<ClassEntry> selectAllClass(String gradeId,String className, String headTeacherName) {
        return classMapper.selectAllClass(gradeId,className, headTeacherName);
    }

    @Override
    public int updateStatus(String classId, int status) {
        return classMapper.update(null, Wrappers.<ClassEntry>lambdaUpdate()
                .set(ClassEntry::getIsForbidden, status)
                .eq(ClassEntry::getId, classId));
    }

    @Override
    public List<ClassDao> getClassByGradeId(String gradeId) {
        List<ClassDao> classDaoList= classMapper.getClassByGradeId(gradeId);

        return classDaoList;
    }


}
