package com.drizzle.sb4u.service.contract.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.drizzle.sb4u.service.base.handler.ValidationGroups;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: drizzle
 * @Date: 2024/10/10/9:11
 * @Description:
 */
@Data
@ApiModel("新增合约基本信息")
public class AddContractBaseDto{

    @NotEmpty(message = "合约名称不能为空", groups = {ValidationGroups.Inster.class})
    @ApiModelProperty(value = "合约名称")
    public String name;

    @ApiModelProperty(value = "语言类型")
    public String language;


    @ApiModelProperty(value = "运行平台")
    public String platform;

    @ApiModelProperty(value = "合约等级")
    public Integer grade;

    @ApiModelProperty(value = "合约描述")
    public String description;

    @ApiModelProperty("审计状态")
    private Integer status;

    @ApiModelProperty(value = "合约文件Id")
    public String fileId;

    @ApiModelProperty(value = "是否删除")
    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;

}
