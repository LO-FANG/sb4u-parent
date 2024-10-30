package com.drizzle.sb4u.service.contract.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: drizzle
 * @Date: 2024/10/09/21:07
 * @Description:
 */
@Data
@ApiModel("contract查询对象")
public class QueryContractBaseDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("合约名称")
    private String name;
    @ApiModelProperty("语言类型")
    private String language;
    @ApiModelProperty("运行平台")
    private String platform;
    @ApiModelProperty("等级")
    private Integer grade;
    @ApiModelProperty("审计状态")
    private Integer status;

}
