package com.drizzle.sb4u.service.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.drizzle.sb4u.common.base.result.PageParams;
import com.drizzle.sb4u.service.contract.entity.dto.QueryContractBaseDto;
import com.baomidou.mybatisplus.extension.service.IService;
import com.drizzle.sb4u.service.contract.entity.po.ContractBase;

/**
 * <p>
 * 合约基本信息表，用于存储合约的基本信息 服务类
 * </p>
 *
 * @author drizzle
 * @since 2024-10-08
 */
public interface ContractBaseService extends IService<ContractBase> {

    IPage<ContractBase> selectPage(PageParams pageParams, QueryContractBaseDto contractQueryVo);
}
