package icu.junyao.common.enums;

import icu.junyao.common.exception.assertion.BaseExceptionAssert;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>通用返回结果</p>
 *
 * @author johnson
 * @date 2021-10-02
 */
@Getter
@AllArgsConstructor
public enum BaseResponseEnum implements BaseExceptionAssert {

    /**
     * 一切OK
     */
    SUCCESS("00000", "操作成功"),

    /**
     * 用户端错误
     */
    JUNYAO_ERROR("A0001", "用户端业务异常"),

    /**
     * 用户请求参数错误
     */
    PARAM_ERROR("A0002", "用户请求参数错误"),

    /**
     * 系统执行出错
     */
    SYSTEM_ERROR("B0001", "系统异常"),

    /**
     * 实例不存在
     */
    ENTITY_NOT_FOUND("B0002", "实例不存在"),

    /**
     * 实例已存在
     */
    ENTITY_EXIST("B0002", "实例已存在"),
    ;

    private final String code;

    private final String message;

}
