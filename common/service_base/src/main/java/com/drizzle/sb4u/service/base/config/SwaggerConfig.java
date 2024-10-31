package com.drizzle.sb4u.service.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: drizzle
 * @Date: 2024/10/08/22:12
 * @Description:
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.drizzle.sb4u.service.contract.controller")	// 合约api
                .or(RequestHandlerSelectors.basePackage("com.drizzle.sb4u.service.minio.controller")))      // 媒资api
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("searchBug4u's RESTful API") 	// 页面标题
                .version("3.0")								// 版本号
                .description("drizzle.org")				    // 描述
                .build();
    }
}
