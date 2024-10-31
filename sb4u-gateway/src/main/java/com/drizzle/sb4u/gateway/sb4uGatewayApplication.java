package com.drizzle.sb4u.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: drizzle
 * @Date: 2024/10/24/14:27
 * @Description:
 */
@SpringBootApplication
@ComponentScan({"com.drizzle.sb4u"})
public class sb4uGatewayApplication {
    public static void main(String[] args) {

        System.setProperty("nacos.logging.default.config.enabled","false");
        SpringApplication.run(sb4uGatewayApplication.class, args);
    }
}
