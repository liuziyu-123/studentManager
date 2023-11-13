package com.studentManager.course.controller;

import com.github.pagehelper.PageHelper;
import com.studentManager.common.Entry.UserEntry;
import com.studentManager.common.Utils.ApiResult;
import com.studentManager.common.Utils.ErrorConstant;
import com.studentManager.common.Utils.LocalThread;
import com.studentManager.common.Utils.PagingResult;
import com.studentManager.course.entry.GradeEntry;
import com.studentManager.course.service.GradeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 1.@Description:
 * 2.@author:liuziyu
 * 3.@Time:2022/12/3
 **/
@RestController
@RequestMapping("course/grade")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    /**
     * @return
     * @descriptions:查询年级
     * @author:liuziyu
     * @Time:2022-11-12
     */
    @GetMapping("selectGrade")
    public ApiResult selectGrade(String gradeName, String gradeDirectorName, int page, int pageSize) {
        if (pageSize > 0) {
            PageHelper.startPage(page, pageSize);
        }
        List<GradeEntry> gradeList = gradeService.selectGrade(gradeName, gradeDirectorName);
        return ApiResult.success(new PagingResult<>(gradeList));
    }

    /**
     * @return
     * @descriptions:新增年级
     * @author:liuziyu
     * @Time:2022-11-12
     */
    @PostMapping("insertGrade")
    public ApiResult insertGrade(@RequestBody GradeEntry gradeEntry) {

        UserEntry user = LocalThread.get();
        if (user == null) {
            return ApiResult.fail(ErrorConstant.NO_GET_LOGIN);
        }

        if (StringUtils.isAnyBlank(gradeEntry.getGradeDirctorId(), gradeEntry.getGradeName(),gradeEntry.getGradeDirctorName())) {
            return ApiResult.fail(ErrorConstant.EMPTY);
        }
        int count=gradeService.insertGrade(gradeEntry);
        if(count==-1){
            return ApiResult.fail(ErrorConstant.NO_GET_LOGIN);
        }
        if(count==0){
            return ApiResult.fail(ErrorConstant.BUSINESSERROR);
        }
        return ApiResult.success(count);
    }

    /**
     * @return
     * @descriptions:修改年级信息（年级名称，年级主任,是否禁用）
     * @author:liuziyu
     * @Time:2022-11-12
     */
    @PostMapping("updateGradeInfo")
    public ApiResult updateGradeInfo(@RequestBody GradeEntry gradeEntry) {
        UserEntry user = LocalThread.get();
        if (user == null) {
            return ApiResult.fail(ErrorConstant.NO_GET_LOGIN);
        }

        if (gradeEntry == null) {
            return ApiResult.fail(ErrorConstant.EMPTY);
        }
        return ApiResult.success(gradeService.updateGradeInfo(gradeEntry));
    }







}
