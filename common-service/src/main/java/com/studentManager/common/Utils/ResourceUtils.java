package com.studentManager.common.Utils;

/**
 * 1.@Description:
 * 2.@author:liuziyu
 * 3.@Time:2023/1/10
 **/
public class ResourceUtils {

    public static int fileTypeToNum(String type){
        if("png".equals(type) || "jpg".equals(type)){
             return 1;
        }


        return 0;
    }
}
