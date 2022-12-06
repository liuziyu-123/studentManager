package com.studentManager.common.Utils;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class ErrorConstant {
    /**
     * 错误.
     */
    public static final Map<Integer, String> ERRORS = new HashMap();

    public static final int ERROR = -200;

    /**
     * 未知错误.
     */
    public static final int UNKOWN_ERROR = -500;
    /**
     * 成功.
     */
    public static final int SUCCESS = 200;

    //用户不存在
    public static final int NO_GET_LOGIN = -100;

    //参数不完整
    public static final int NO_COMPLETE_PARAM = -1;

    //该名称重复
    public static final int NAME_REPEAT = -2;

    //该编码重复
    public static final int CODE_REPEAT = -3;

    //Token无效
    public static final int NOTOKEN = -401;

    //空数据
    public static final int EMPTY = -402;

    //没有权限
    public static final int No_PERMISSION = -403;

    /**
     * 业务异常
     */
    public static final int BUSINESSERROR = -400;

    /**
     * 404 资源不存在
     */
    public static final int NOTFOUND = -404;

    /**
     * 405 对于请求所标识的资源，不允许使用请求行中所指定的方法。请确保为所请求的资源设置了正确的 MIME 类型。
     */
    public static final int METHODNOTALLOWED = -405;

    /**
     * 415 Unsupported Media Type
     */
    public static final int UNSUPPORTEDMEDIATYPE = -415;

    /**
     * 系统异常 502 服务器的内部错误
     */
    public static final int EXCEPTION = -502;


    /**
     * 限流429
     */
    public static final int TRAFFICLIMITING = -429;

    /**
     * 服务调用异常
     */
    public static final int APIGATEWAYERROR = -9999;

    /**
     * 510 rpc调用异常
     */
    public static final int RPCERROR = -510;

    /**
     * 空指针
     */
    public static final int NULLERROR = -406;


    static {
        ERRORS.put(UNKOWN_ERROR, "未知错误");
        ERRORS.put(NOTOKEN, "Token无效");
        ERRORS.put(SUCCESS, "操作成功");
        ERRORS.put(NO_GET_LOGIN, "用户不存在");
        ERRORS.put(NO_COMPLETE_PARAM, "参数不完整");
        ERRORS.put(NAME_REPEAT, "该名称重复");
        ERRORS.put(CODE_REPEAT, "该编码重复");
        ERRORS.put(EMPTY, "空");
        ERRORS.put(No_PERMISSION, "您没有权限操作");


        ERRORS.put(BUSINESSERROR, "业务异常");
        ERRORS.put(NOTFOUND, "资源不存在");
        ERRORS.put(METHODNOTALLOWED, "请重新尝试");
        ERRORS.put(UNSUPPORTEDMEDIATYPE, "不支持的文件类型");
        ERRORS.put(EXCEPTION, "服务器异常，请稍后再试");
        ERRORS.put(TRAFFICLIMITING, "网络拥挤，请稍后再试");
        ERRORS.put(APIGATEWAYERROR, "网络繁忙，请稍后再试");
        ERRORS.put(RPCERROR, "网络出问题，请稍后再试");
        ERRORS.put(NULLERROR, "空指针异常,请重新尝试");

    }

    /**
     * 获取错误信息.
     *
     * @param code 错误码
     * @return 错误信息
     */
    public static String get(int code) {
        String info = ERRORS.get(code);
        return StringUtils.isNotEmpty(info) ? info : ERRORS.get(UNKOWN_ERROR);
    }
}