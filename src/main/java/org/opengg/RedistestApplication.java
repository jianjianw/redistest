package org.opengg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("org.opengg.mapper")
//扫描实体类包
//@EntityScan("org.opengg.bean")

@EnableCaching
public class RedistestApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedistestApplication.class, args);
    }

}

