package com.drizzle.sb4u.service.minio.feignclient;


import com.drizzle.sb4u.common.base.result.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "service-contract", fallbackFactory = ContractServiceClientFallbackFactory.class)
public interface ContractServiceClient {

    @PostMapping("/contract/getfileid/{id}")
    public String getFileIdById(@PathVariable("id") String id);

    @PostMapping("/contract/detect-result/querydetectfileid/{id}")
    public String queryDetectFileId(@PathVariable("id") String id);
}
