package com.drizzle.sb4u.service.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drizzle.sb4u.common.base.result.PageParams;
import com.drizzle.sb4u.service.contract.entity.dto.QueryContractBaseDto;
import com.drizzle.sb4u.service.contract.entity.po.ContractBase;
import com.drizzle.sb4u.service.contract.mapper.ContractBaseMapper;
import com.drizzle.sb4u.service.contract.service.ContractBaseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * <p>
 * 合约基本信息表，用于存储合约的基本信息 服务实现类
 * </p>
 *
 * @author drizzle
 * @since 2024-10-08
 */
@Service
public class ContractBaseServiceImpl extends ServiceImpl<ContractBaseMapper, ContractBase> implements ContractBaseService {
    @Resource
    private ContractBaseMapper contractBaseMapper;

    @Override
    public IPage<ContractBase> selectPage(PageParams pageParams, QueryContractBaseDto contractQueryVo) {

        LambdaQueryWrapper<ContractBase> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        // 语言类型
        lambdaQueryWrapper.like(StringUtils.isNotEmpty(contractQueryVo.getLanguage()), ContractBase::getLanguage, contractQueryVo.getLanguage());
        // 运行平台
        lambdaQueryWrapper.like(StringUtils.isNotEmpty(contractQueryVo.getPlatform()), ContractBase::getPlatform, contractQueryVo.getPlatform());
        // 审计状态
        lambdaQueryWrapper.eq(contractQueryVo.getStatus() != null, ContractBase::getStatus, contractQueryVo.getStatus());
        // 合约等级
        lambdaQueryWrapper.eq(contractQueryVo.getGrade() != null, ContractBase::getGrade, contractQueryVo.getGrade());
        // 合约名称
        lambdaQueryWrapper.like(StringUtils.isNotEmpty(contractQueryVo.getName()), ContractBase::getName, contractQueryVo.getName());


        Page<ContractBase> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());
        IPage<ContractBase> contractBasePage = contractBaseMapper.selectPage(page, lambdaQueryWrapper);
        long total = contractBasePage.getTotal();
        return contractBasePage;
    }
}
