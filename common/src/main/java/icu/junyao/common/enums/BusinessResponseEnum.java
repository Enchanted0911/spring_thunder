package icu.junyao.common.enums;

import icu.junyao.common.exception.assertion.BusinessExceptionAssert;
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
public enum BusinessResponseEnum implements BusinessExceptionAssert {


    /**
     * 请输入有效的用户名以及密码
     */
    PWD_ERROR("A0210", "请输入有效的用户名以及密码。"),
    /**
     * 新密码不能和旧密码相同
     */
    NEW_PWD_EQUALS_OLD("A0212", "新密码不能与旧密码相同!"),
    /**
     * 用户名已存在
     */
    USER_NAME_REPEAT("A0211", "用户名已存在"),
    /**
     * 文件不存在!
     */
    FILE_IS_EMPTY("A0213", "文件不存在!"),
    /**
     * 用户不存在!
     */
    USER_NOT_FOUND("A0214", "用户不存在!"),
    /**
     * 角色名或角色编码已存在!
     */
    ROLE_INFO_EXIST("A0215", "角色名或角色编码已存在!"),
    /**
     * 权限名已存在
     */
    PERMISSION_NAME_ALREADY_EXISTS("A0216", "权限名已存在!"),
    /**
     * 该角色正在被使用
     */
    ROLE_BRING_USED("A0217", "该角色正在被使用!"),
    /**
     * banner标题已存在
     */
    BANNER_TITLE_USED("A0218", "banner标题已存在!"),
    /**
     * banner不存在
     */
    BANNER_NOT_EXIST("A0219", "banner不存在!"),
    /**
     * 登录信息过期
     */
    LOGIN_EXPIRED("A0220", "登录信息过期, 请重新登录!");

    private final String code;

    private final String message;

}
