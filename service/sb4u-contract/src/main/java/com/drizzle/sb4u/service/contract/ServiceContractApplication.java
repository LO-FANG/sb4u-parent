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
public class ServiceContractApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceContractApplication.class, args);
    }
}
