package com.drizzle.sb4u.service.contract.feignclient;

import com.drizzle.sb4u.common.base.result.R;
import com.drizzle.sb4u.service.contract.config.MultipartSupportConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: drizzle
 * @Date: 2024/08/29/11:25
 * @Description:
 */
@FeignClient(name = "service-minio", configuration = MultipartSupportConfig.class, fallbackFactory = MinioServiceClientFallbackFactory.class)
public interface MinioServiceClient {

    @PostMapping("minio/delete")
    Boolean delete(@RequestParam(value = "fileId") String id);
}
