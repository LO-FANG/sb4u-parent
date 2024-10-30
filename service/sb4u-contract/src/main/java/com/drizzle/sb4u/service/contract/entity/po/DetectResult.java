package com.drizzle.sb4u.service.contract.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import com.drizzle.sb4u.service.base.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 * 检测结果表
 * </p>
 *
 * @author drizzle
 * @since 2024-10-30
 */
@Data
@TableName("t_detect_result")
@ApiModel(value="DetectResult对象", description="检测结果表")
public class DetectResult {

    private static final long serialVersionUID = 1L;

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
    private String resultId;


    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
