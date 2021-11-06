
package icu.junyao.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * @author johnson
 * @date 2021-10-02
 */

@Getter
@AllArgsConstructor
public enum ResultCode {

	/**
	 * 一切OK
	 */
	SUCCESS("20000", "操作成功"),

	 /**
	 * 用户端错误
	 */
	JUNYAO_ERROR("A0001", "用户端业务异常"),

	/**
	 * 用户身份校验失败
	 */
	OAUTH_ERROR("A0220","用户身份校验失败"),

	/**
	 * 用户请求参数错误
	 */
	PARAM_ERROR("A0400", "用户请求参数错误"),

	/**
	 * 系统执行出错
	 */
	SYSTEM_ERROR("B0001", "系统异常"),
	;

	/**
	 * code编码
	 */
	private final String code;

	/**
	 * 中文信息描述
	 */
	private final String message;
}
