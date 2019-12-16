package org.study.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName: User
 * @Description:
 * @Author: Lcc
 * @Date: 2019/8/6
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan("org.study.springboot.mapper")
@EnableScheduling
@EnableCaching
@EnableSwagger2
public class SpringbootCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootCoreApplication.class, args);
    }

}
