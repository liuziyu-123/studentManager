package com.studentManager.common.Utils;


public class ApiResult<T> {
    /**
     * 状态码
     */
    private int code;

    /**
     * 信息
     */
    private String msg;

    /**
     * 返回数据
     */
    private T result;

    /**
     * 构造方法
     */
    public ApiResult() {
    }

    public ApiResult(int code) {
        super();
        this.code = code;
        this.msg = ErrorConstant.get(code);
    }

    public ApiResult(String msg, int code) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public ApiResult(int code, String msg, T result) {
        super();
        this.code = code;
        this.msg = msg;
        this.result = result;
    }

    public ApiResult(int code, T result) {
        super();
        this.code = code;
        this.msg = ErrorConstant.get(code);
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the result
     */
    public T getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(T result) {
        this.result = result;
    }

    /**
     * 返回一个标准成功的JSON结果
     *
     * @return 标准JSON结果
     */
    public static <T> ApiResult<T> success() {
        return new ApiResult<>(ErrorConstant.SUCCESS);
    }


    /**
     * 返回一个标准成功的JSON结果
     *
     * @author ljchengx
     * @date 2021/5/8 14:32
     */
    public static <T> ApiResult<T> success(T result) {

        return new ApiResult<>(ErrorConstant.SUCCESS, result);
    }

    /**
     * 返回一个不含result的标准失败的JSON结果
     *
     * @param code 错误码
     * @return 标准JSON结果
     */
    public static <T> ApiResult<T> fail(int code) {
        return new ApiResult<>(code);
    }


    /**
     * 返回一个不含result的标准失败的JSON结果
     *
     * @param code 错误码
     * @return 标准JSON结果
     */
    public static <T> ApiResult<T> fail(int code, T result) {
        return new ApiResult<>(code, result);
    }

    /**
     * 返回一个不含result的标准失败的JSON结果
     *
     * @param msg 错误提示
     * @return 标准JSON结果
     */
    public static <T> ApiResult<T> error(String msg) {
        return new ApiResult<>(msg, ErrorConstant.ERROR);
    }


}
