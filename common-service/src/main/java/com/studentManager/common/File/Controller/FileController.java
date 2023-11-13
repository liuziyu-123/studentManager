package com.studentManager.common.File.Controller;

import cn.hutool.core.io.FileUtil;
import com.studentManager.common.File.FileService.FileService;
import com.studentManager.common.Utils.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * 对文件的处理
 */
@RestController
@RequestMapping("file")
public class FileController {

    @Value("${files.upload.path}")
    private String fileUploadPath;

    @Autowired
    private FileService fileService;

    /**
     * 文件上传接口
     *
     * @param file
     * @return
     * @throws IOException 对应与前端图片上传路径：http://localhost:8081/file/upload/img
     */
    @PostMapping("/upload/img")
    public ApiResult upload(@RequestParam MultipartFile file) throws IOException {

        return ApiResult.success( fileService.upload(file));

    }

    /**
     * 通过流的形式下载
     *
     * @param fileUUID
     */
    @GetMapping("/{fileUUID}")
    public void download(@PathVariable String fileUUID, HttpServletResponse response) throws IOException {
        //根据文件的标识码获取文件
        File uploadFile = new File(fileUploadPath + fileUUID);
        //设置输出流格式
        ServletOutputStream os = response.getOutputStream();
        response.addHeader("Context-Disposition", "attachment;filename=" +
                URLEncoder.encode(fileUUID, "UTF-8"));
        response.setContentType("application/octet-stream");
        //读取字节流
        os.write(FileUtil.readBytes(uploadFile));
        os.flush();
        os.close();
    }

    @GetMapping("getList")
    public ApiResult getList(@RequestParam int type){
       return ApiResult.success(fileService.getList(type)) ;

    }



}
