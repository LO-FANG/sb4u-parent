package com.drizzle.sb4u.service.minio.entity.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: drizzle
 * @Date: 2024/10/25/11:29
 * @Description:
 */
public class QueryMediaParamsDto {
    @ApiModelProperty("媒资文件名称")
    private String filename;
    @ApiModelProperty("媒资类型")
    private String fileType;
}
