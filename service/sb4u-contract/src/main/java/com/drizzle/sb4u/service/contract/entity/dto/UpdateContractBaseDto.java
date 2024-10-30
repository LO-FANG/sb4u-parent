package com.drizzle.sb4u.service.contract.entity.dto;

import com.drizzle.sb4u.service.contract.entity.po.ContractBase;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: drizzle
 * @Date: 2024/10/09/21:16
 * @Description:
 */
@Data
@ApiModel(value = "UpdateContractBaseDto", description = "修改合约基本信息")
public class UpdateContractBaseDto extends ContractBase {
}
