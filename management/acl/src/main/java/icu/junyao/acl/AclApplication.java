package icu.junyao.acl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * @author johnson
 * @date 2021-10-03
 */

@SpringBootApplication
@ComponentScan(value = "icu.junyao", excludeFilters = {@ComponentScan.Filter(type =
        FilterType.ASSIGNABLE_TYPE, classes = {icu.junyao.common.config.RedisConfig.class, icu.junyao.common.handler.MyMetaObjectHandler.class})})
@MapperScan("icu.junyao.acl.mapper")
public class AclApplication {
    public static void main(String[] args) {
        SpringApplication.run(AclApplication.class, args);
    }
}
