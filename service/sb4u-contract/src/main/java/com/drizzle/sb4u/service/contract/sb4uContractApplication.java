package com.drizzle.sb4u.service.contract;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: drizzle
 * @Date: 2024/10/08/21:18
 * @Description:
 */
@SpringBootApplication
@ComponentScan({"com.drizzle.sb4u"})
@EnableFeignClients
public class sb4uContractApplication {

    public static void main(String[] args) {
        System.setProperty("nacos.logging.default.config.enabled","false");
        SpringApplication.run(sb4uContractApplication.class, args);
    }
}
