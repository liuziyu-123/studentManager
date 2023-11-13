package com.studentManager.course.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.studentManager.common.Utils.MyException;
import com.studentManager.common.Utils.UUIDUtils;
import com.studentManager.course.entry.ClassEntry;
import com.studentManager.course.entry.GradeEntry;
import com.studentManager.course.mapper.ClassMapper;
import com.studentManager.course.mapper.GradeMapper;
import com.studentManager.course.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 1.@Description:
 * 2.@author:liuziyu
 * 3.@Time:2022/12/17
 **/
@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeMapper gradeMapper;



    @Override
    public int insertGrade(GradeEntry gradeEntry) {

        long count = gradeMapper.selectCount(Wrappers.<GradeEntry>lambdaQuery()
                .eq(GradeEntry::getGradeName, gradeEntry.getGradeName()));
        if (count > 0) {
            return 0;
        }
        gradeEntry.setId(UUIDUtils.getGUID32());
        return gradeMapper.insert(gradeEntry);
    }

    @Override
    public int updateGradeInfo(GradeEntry gradeEntry) {
       Long count= gradeMapper.selectCount(Wrappers.<GradeEntry>lambdaQuery().eq(GradeEntry::getGradeName,gradeEntry.getGradeName()));
        if(count>0){
            throw new MyException("该年级名称重复");
        }
        return gradeMapper.updateById(gradeEntry);
    }

    @Override
    public List<GradeEntry> selectGrade(String gradeName, String gradeDirectorId) {

        return gradeMapper.selectGrade(gradeName, gradeDirectorId);
    }
}
