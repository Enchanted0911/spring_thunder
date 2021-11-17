package icu.junyao.extracurricular;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author johnson
 * @date 2021-10-22
 */
@SpringBootApplication
@MapperScan("icu.junyao.extracurricular.mapper")
@ComponentScan("icu.junyao")
public class ExtracurricularApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExtracurricularApplication.class);
    }
}
