package com.drizzle.sb4u.service.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.drizzle.sb4u.common.base.result.PageParams;
import com.drizzle.sb4u.service.contract.entity.dto.QuerDetectResultDto;
import com.drizzle.sb4u.service.contract.entity.dto.QueryContractBaseDto;
import com.drizzle.sb4u.service.contract.entity.po.ContractBase;
import com.drizzle.sb4u.service.contract.entity.po.DetectResult;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 检测结果表 服务类
 * </p>
 *
 * @author drizzle
 * @since 2024-10-30
 */
public interface DetectResultService extends IService<DetectResult> {

    IPage<DetectResult> selectPage(PageParams pageParams, QuerDetectResultDto querDetectResultDto);

    String getResFileIdByResId(String id);

}
