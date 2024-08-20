package com.studentManager.common.Utils;

public class MyException extends RuntimeException {
    private String msg;
    private int code = 300;

    /**
     * 空参构造
     */
    public MyException() {
        super();
    }

    /**
     * @param msg 表示异常提示
     */
    public MyException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public MyException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public MyException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
