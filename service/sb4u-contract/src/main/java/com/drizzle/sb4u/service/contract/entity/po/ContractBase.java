package com.drizzle.sb4u.service.contract.entity.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableName;
import com.drizzle.sb4u.service.base.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 合约基本信息表，用于存储合约的基本信息
 * </p>
 *
 * @author drizzle
 * @since 2024-10-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_contract_base")
@ApiModel(value="ContractBase对象", description="合约基本信息表，用于存储合约的基本信息")
public class ContractBase extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "检测结果ID")
    private String resultId;

    @ApiModelProperty(value = "语言类型")
    private String language;

    @ApiModelProperty(value = "合约平台")
    private String platform;

    @ApiModelProperty(value = "合约等级")
    private Integer grade;

    @ApiModelProperty(value = "介绍")
    private String description;

    @ApiModelProperty(value = "创建者")
    private String createPeople;

    @ApiModelProperty(value = "更新者")
    private String updatePeople;

    @ApiModelProperty(value = "是否删除")
    @TableField(value = "is_deleted", fill = FieldFill.INSERT)
    @TableLogic
    private Boolean deleted;

    @ApiModelProperty(value = "合约名称")
    private String name;

    @ApiModelProperty(value = "合约文件id")
    private String fileId;

    @ApiModelProperty(value = "合约审计状态（1表示未审计，2表示审计中，3表示已审计）")
    private Integer status;


}
