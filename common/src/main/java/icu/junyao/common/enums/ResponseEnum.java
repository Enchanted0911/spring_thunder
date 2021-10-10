package icu.junyao.common.enums;

/**
 * <pre>
 *  异常返回码枚举接口
 * </pre>
 *
 * @author johnson
 * @date 2021-10-02
 */
public interface ResponseEnum {

    /**
     * 获取返回码
     * @return 返回码
     */
    String getCode();

    /**
     * 获取返回信息
     * @return 返回信息
     */
    String getMessage();
}
