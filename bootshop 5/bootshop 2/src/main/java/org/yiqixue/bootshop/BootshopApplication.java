package org.yiqixue.bootshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("org.yiqixue.bootshop.mapper")
public class BootshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootshopApplication.class, args);
    }

}
