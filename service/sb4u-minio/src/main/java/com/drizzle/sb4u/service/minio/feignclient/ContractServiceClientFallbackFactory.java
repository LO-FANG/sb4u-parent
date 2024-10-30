package com.drizzle.sb4u.service.minio.feignclient;

import com.drizzle.sb4u.common.base.result.R;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: drizzle
 * @Date: 2024/08/30/10:21
 * @Description:
 */
@Component
@Slf4j
public class ContractServiceClientFallbackFactory implements FallbackFactory<ContractServiceClient> {
    @Override
    public ContractServiceClient create(Throwable cause) {

        return new ContractServiceClient() {

            @Override
            public String getFileIdById(String id) {
                log.debug("上传服务发生熔断...");
                return null;
            }

            @Override
            public String queryDetectFileId(String id) {
                log.debug("上传服务发生熔断...");
                return null;
            }
        };
    }
}
