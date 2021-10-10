package icu.junyao.common.exception;

import icu.junyao.common.enums.ResponseEnum;
import lombok.Getter;

/**
 * <p>基础异常类，所有自定义异常类都需要继承本类</p>
 *
 * @author johnson
 * @date 2021-10-02
 */
@Getter
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 返回码
     */
    protected ResponseEnum responseEnum;
    /**
     * 异常消息参数
     */
    protected Object[] args;

    public BaseException(ResponseEnum responseEnum) {
        super(responseEnum.getMessage());
        this.responseEnum = responseEnum;
    }

    public BaseException(String code, String msg) {
        super(msg);
        this.responseEnum = new ResponseEnum() {
            @Override
            public String getCode() {
                return code;
            }

            @Override
            public String getMessage() {
                return msg;
            }
        };
    }

    public BaseException(ResponseEnum responseEnum, Object[] args, String message) {
        super(message);
        this.responseEnum = responseEnum;
        this.args = args;
    }

    public BaseException(ResponseEnum responseEnum, Object[] args, String message, Throwable cause) {
        super(message, cause);
        this.responseEnum = responseEnum;
        this.args = args;
    }
}
