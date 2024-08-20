package com.studentManager.course.service;


import com.studentManager.common.Entry.ResourcesFileEntry;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 1.@Description:
 * 2.@author:liuziyu
 * 3.@Time:2023/1/10
 **/
public interface FileService {

    String upload(MultipartFile file) throws IOException;

     List<ResourcesFileEntry> getList(int type);
}
