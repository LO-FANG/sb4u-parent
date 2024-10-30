package com.drizzle.sb4u.service.contract.feignclient;

import com.drizzle.sb4u.common.base.result.R;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: drizzle
 * @Date: 2024/08/30/10:21
 * @Description:
 */
@Component
@Slf4j
public class MinioServiceClientFallbackFactory implements FallbackFactory<MinioServiceClient> {
    @Override
    public MinioServiceClient create(Throwable cause) {

        return new MinioServiceClient() {

            @Override
            public Boolean delete(String id) {
                log.debug("上传服务发生熔断...");
                return null;
            }
        };
    }
}
