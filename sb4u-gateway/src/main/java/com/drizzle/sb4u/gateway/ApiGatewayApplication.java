package com.drizzle.sb4u.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: drizzle
 * @Date: 2024/10/24/14:27
 * @Description:
 */
@SpringBootApplication
public class ApiGatewayApplication {
    public static void main(String[] args) {

        System.setProperty("nacos.logging.default.config.enabled","false");
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}
