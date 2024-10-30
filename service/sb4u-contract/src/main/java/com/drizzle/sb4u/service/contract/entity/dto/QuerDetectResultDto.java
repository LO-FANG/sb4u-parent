package com.drizzle.sb4u.service.contract.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: drizzle
 * @Date: 2024/10/30/16:03
 * @Description:
 */
@Data
@ApiModel("detectResult查询对象")
public class QuerDetectResultDto {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("检测结果记录ID")
    private String resultId;
    @ApiModelProperty("检测工具")
    private String tool;
    @ApiModelProperty("漏洞类型")
    private String type;
}
