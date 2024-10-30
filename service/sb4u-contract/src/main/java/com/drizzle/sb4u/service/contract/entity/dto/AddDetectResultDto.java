package com.drizzle.sb4u.service.contract.entity.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: drizzle
 * @Date: 2024/10/30/17:35
 * @Description:
 */
@Data
@ApiModel("新增检测结果基本信息")
public class AddDetectResultDto {

    @ApiModelProperty(value = "ID")
    @TableId(value = "id")
    private String id;

    @ApiModelProperty(value = "合约名称")
    private String contractName;

    @ApiModelProperty(value = "程序计数器")
    private String pc;

    @ApiModelProperty(value = "操作码")
    private String opcode;

    @ApiModelProperty(value = "缺陷类型")
    private String type;

    @ApiModelProperty(value = "工具名称")
    private String tool;

    @ApiModelProperty(value = "代码覆盖率")
    private String codeCoverage;

    @ApiModelProperty(value = "执行时间")
    private String executionTime;

    @ApiModelProperty(value = "检测结果文件id")
    private String ResultId;
}
