package icu.junyao.classroom;

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
@EnableFeignClients
@MapperScan("icu.junyao.classroom.mapper")
@ComponentScan("icu.junyao")
public class ClassroomApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClassroomApplication.class);
    }
}
