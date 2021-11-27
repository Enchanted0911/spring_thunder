package icu.junyao.back;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * @author johnson
 * @date 2021-10-22
 */
@MapperScan("icu.junyao.back.mapper")
@SpringBootApplication
@EnableFeignClients
@ComponentScan(value = "icu.junyao", excludeFilters = {@ComponentScan.Filter(type =
        FilterType.ASSIGNABLE_TYPE, classes = {icu.junyao.common.config.RedisConfig.class, icu.junyao.common.handler.MyMetaObjectHandler.class})})
public class BackApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackApplication.class);
    }
}
