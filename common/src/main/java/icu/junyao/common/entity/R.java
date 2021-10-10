package icu.junyao.common.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import icu.junyao.common.enums.ResultCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 返回
 *
 * @author johnson
 * @date 2021-10-02
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "状态码", required = true)
    private String code;
    @ApiModelProperty(value = "承载数据")
    private T data;
    @ApiModelProperty(value = "返回消息", required = true)
    private String msg;

    private R(ResultCode resultCode) {
        this(resultCode, null, resultCode.getMessage());
    }

    private R(ResultCode resultCode, String msg) {
        this(resultCode, null, msg);
    }

    private R(ResultCode resultCode, T data) {
        this(resultCode, data, resultCode.getMessage());
    }

    private R(ResultCode resultCode, T data, String msg) {
        this(resultCode.getCode(), data, msg);
    }

    private R(String code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }


    /**
     * 返回R
     *
     * @param data 数据
     * @param <T>  T 泛型标记
     * @return R
     */
    public static <T> R<T> data(T data) {
        return data(data, ResultCode.SUCCESS.getMessage());
    }

    /**
     * 返回R
     *
     * @param data 数据
     * @param msg  消息
     * @param <T>  T 泛型标记
     * @return R
     */
    public static <T> R<T> data(T data, String msg) {
        return data(ResultCode.SUCCESS.getCode(), data, msg);
    }

    /**
     * 返回R
     *
     * @param code 状态码
     * @param data 数据
     * @param msg  消息
     * @param <T>  T 泛型标记
     * @return R
     */
    public static <T> R<T> data(String code, T data, String msg) {
        return new R<>(code, data, msg);
    }

    /**
     * 返回R
     *
     * @param <T> T 泛型标记
     * @return R
     */
    public static <T> R<T> success() {
        return new R<>(ResultCode.SUCCESS, ResultCode.SUCCESS.getMessage());
    }


    /**
     * 返回R
     *
     * @param msg 消息
     * @param <T> T 泛型标记
     * @return R
     */
    public static <T> R<T> success(String msg) {
        return new R<>(ResultCode.SUCCESS, msg);
    }

    /**
     * 返回R
     *
     * @param resultCode 业务代码
     * @param <T>        T 泛型标记
     * @return R
     */
    public static <T> R<T> success(ResultCode resultCode) {
        return new R<>(resultCode);
    }

    /**
     * 返回R
     *
     * @param resultCode 业务代码
     * @param msg        消息
     * @param <T>        T 泛型标记
     * @return R
     */
    public static <T> R<T> success(ResultCode resultCode, String msg) {
        return new R<>(resultCode, msg);
    }

    /**
     * 返回R
     *
     * @param msg 消息
     * @param <T> T 泛型标记
     * @return R
     */
    public static <T> R<T> fail(String msg) {
        return new R<>(ResultCode.BIZ_ERROR, msg);
    }


    /**
     * 返回R
     *
     * @param code 状态码
     * @param msg  消息
     * @param <T>  T 泛型标记
     * @return R
     */
    public static <T> R<T> fail(String code, String msg) {
        return new R<>(code, null, msg);
    }

    /**
     * 返回R
     *
     * @param resultCode 业务代码
     * @param <T>        T 泛型标记
     * @return R
     */
    public static <T> R<T> fail(ResultCode resultCode) {
        return new R<>(resultCode);
    }

    /**
     * 返回R
     *
     * @param resultCode 业务代码
     * @param msg        消息
     * @param <T>        T 泛型标记
     * @return R
     */
    public static <T> R<T> fail(ResultCode resultCode, String msg) {
        return new R<>(resultCode, msg);
    }


}
