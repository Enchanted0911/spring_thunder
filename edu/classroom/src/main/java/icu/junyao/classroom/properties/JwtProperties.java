package icu.junyao.classroom.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

/**
 * @author johnson
 * @date 2021-10-02
 */
@Validated
@Configuration
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtProperties {

    /**
     * 认证头
     */
    private String header = "Authorization";

    /**
     * 认证头前缀
     */
    private String prefix = "Bearer ";

    /**
     * token过期时间 默认一天
     */
    private Long accessTokenExpire = 86_400_000L;

    /**
     * 刷新token过期时间 默认一个月
     */
    private Long refreshTokenExpire = 2_592_000_000L;

    /**
     * key
     */
    @NotEmpty
    private String key;

    /**
     * refreshKey
     */
    @NotEmpty
    private String refreshKey;
}
