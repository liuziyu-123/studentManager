package com.studentManager.course.controller;


import com.github.pagehelper.PageHelper;
import com.studentManager.common.Entry.UserEntry;
import com.studentManager.common.Utils.ApiResult;
import com.studentManager.common.Utils.ErrorConstant;
import com.studentManager.common.Utils.LocalThread;
import com.studentManager.common.Utils.PagingResult;
import com.studentManager.course.entry.ClassDao;
import com.studentManager.course.entry.ClassEntry;
import com.studentManager.course.service.ClassService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/*

 */
@RestController
@RequestMapping("course/class")
public class ClassController {

    @Autowired
    private ClassService classService;

    /**
     * 新增班级和班主任
     *
     * @param classEntry
     * @return
     */
    @PostMapping("insertClass")
    public ApiResult insertClass(@RequestBody ClassEntry classEntry) {

        UserEntry user = LocalThread.get();
        if (null == user) {
            return ApiResult.fail(ErrorConstant.NO_GET_LOGIN);
        }
        if (null == classEntry) {
            return ApiResult.fail(ErrorConstant.EMPTY, "参数不能为空");
        }

        int count = classService.insertClass(classEntry);
        return ApiResult.success(count);
    }


    /**
     * 修改班级的班主任
     *
     * @param classEntry
     * @return
     */
    @PostMapping("updateHeadTeacher")
    public ApiResult updateHeadTeacher(@RequestBody ClassEntry classEntry) {

        UserEntry user = LocalThread.get();
        if (null == user) {
            return ApiResult.fail(ErrorConstant.NO_GET_LOGIN);
        }
        if (null == classEntry) {
            return ApiResult.fail(ErrorConstant.EMPTY, "参数不能为空");
        }

        int count = classService.updateHeadTeacher(classEntry);
        return ApiResult.success(count);
    }


    /**
     * 查询班级信息
     *
     * @param className       班级名称
     * @param headTeacherName 班主任的ID
     * @return
     */
    @GetMapping("selectAllClass")
    public ApiResult selectAllClass(String gradeId,String className, String headTeacherName, int page, int pageSize) {


        if (pageSize > 0) {
            PageHelper.startPage(page, pageSize);
        }

        List<ClassEntry> classList = classService.selectAllClass(gradeId,className, headTeacherName);
        return ApiResult.success(new PagingResult<>(classList));
    }


    /**
     * 禁用班级
     *
     * @param classId 班级Id
     * @return
     */
    @PostMapping("updateStatus")
    public ApiResult updateStatus(@RequestParam String classId, @RequestParam int status) {

        if (StringUtils.isBlank(classId)) {
            return ApiResult.fail(ErrorConstant.EMPTY, "参数不能为空");
        }

        int count = classService.updateStatus(classId, status);
        return ApiResult.success(count);
    }

//    @GetMapping("first")
//    public ApiResult first(@RequestParam(required = false) String url,
//                           @RequestParam(required = false) String word,
//                           @RequestParam(required = false) String path) {
//         //url="https://live.kuaishou.com/live_api/profile/feedbyid?photoId=3x68zjm6p2mqb7a&principalId=3xbrz4srscjbcpy";
//        SougouImgProcessor processor = new SougouImgProcessor(url,word,path);
//
//        //  int start = 0, size = 10, limit = 50; // 定义爬取开始索引、每次爬取数量、总共爬取数量
//
////        for(int i=start;i<start+limit;i+=size)
//        //  processor.process("48", "48");
//        processor.process();
//        processor.pipelineData();
//        return ApiResult.success();
//    }


//    @PostMapping("down")
//    public ApiResult down(@RequestBody String date) {
//        JSONObject object= JSON.parseObject(date);
//        List<String> dateList= (List<String>)object.get("dateList");
//        SougouImgPipeline sougouImgProcessor=new SougouImgPipeline("H:/美妙人生/收藏/色/快手");
//        sougouImgProcessor.process(dateList,"爱你吖");
//        return ApiResult.success();
//    }



}
