package com.drizzle.sb4u.service.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drizzle.sb4u.common.base.result.PageParams;
import com.drizzle.sb4u.service.contract.entity.dto.QuerDetectResultDto;
import com.drizzle.sb4u.service.contract.entity.dto.QueryContractBaseDto;
import com.drizzle.sb4u.service.contract.entity.po.ContractBase;
import com.drizzle.sb4u.service.contract.entity.po.DetectResult;
import com.drizzle.sb4u.service.contract.mapper.ContractBaseMapper;
import com.drizzle.sb4u.service.contract.mapper.DetectResultMapper;
import com.drizzle.sb4u.service.contract.service.DetectResultService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 检测结果表 服务实现类
 * </p>
 *
 * @author drizzle
 * @since 2024-10-30
 */
@Service
public class DetectResultServiceImpl extends ServiceImpl<DetectResultMapper, DetectResult> implements DetectResultService {

    @Resource
    private DetectResultMapper detectResultMapper;

    @Override
    public IPage<DetectResult> selectPage(PageParams pageParams, QuerDetectResultDto detectResultQueryVo) {

        LambdaQueryWrapper<DetectResult> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 合约文件ID
        lambdaQueryWrapper.eq(StringUtils.isNotEmpty(detectResultQueryVo.getResultId()), DetectResult::getId, detectResultQueryVo.getResultId());
        // 检测工具
        lambdaQueryWrapper.like(StringUtils.isNotEmpty(detectResultQueryVo.getTool()), DetectResult::getTool, detectResultQueryVo.getTool());
        // 漏洞类型
        lambdaQueryWrapper.like(StringUtils.isNotEmpty(detectResultQueryVo.getType()), DetectResult::getType, detectResultQueryVo.getType());


        Page<DetectResult> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());
        IPage<DetectResult> detectResultPage = detectResultMapper.selectPage(page, lambdaQueryWrapper);
        long total = detectResultPage.getTotal();
        return detectResultPage;
    }
}
