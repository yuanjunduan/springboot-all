/**
 * Copyright (c) 2014 http://www.jieqianhua.com
 */
package min.jun.exception;


public class BusinessException extends RuntimeException {

    private String code = "";
    private String msg = "";

    public BusinessException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(Exception e) {
        super(e);
        if (e instanceof BusinessException) {
            this.code = ((BusinessException) e).getCode();
            this.msg = e.getMessage();
        }
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
