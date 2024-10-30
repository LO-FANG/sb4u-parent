package com.drizzle.sb4u.service.contract.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.drizzle.sb4u.common.base.result.PageParams;
import com.drizzle.sb4u.common.base.result.R;
import com.drizzle.sb4u.service.contract.entity.dto.QuerDetectResultDto;
import com.drizzle.sb4u.service.contract.entity.dto.QueryContractBaseDto;
import com.drizzle.sb4u.service.contract.entity.po.ContractBase;
import com.drizzle.sb4u.service.contract.entity.po.DetectResult;
import com.drizzle.sb4u.service.contract.service.ContractBaseService;
import com.drizzle.sb4u.service.contract.service.DetectResultService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 检测结果表 前端控制器
 * </p>
 *
 * @author drizzle
 * @since 2024-10-30
 */
@RestController
@Slf4j
@RequestMapping("admin/contract/detect-result")
@CrossOrigin
public class DetectResultController {

    @Autowired
    private DetectResultService detectResultService;

    @Autowired
    private ContractBaseService contractBaseService;


    @ApiOperation(value = "获取合约分页列表", notes = "获取合约分页列表")
    @GetMapping("/listpage/{pageNo}/{pageSize}")
    public R listPage(@PathVariable Long pageNo, @PathVariable Long pageSize,
                      @ApiParam("contract查询条件对象") QuerDetectResultDto querDetectResultDto) {
        PageParams pageParams = new PageParams();
        if(pageNo != null && pageSize != null) {
            pageParams.setPageNo(pageNo);
            pageParams.setPageSize(pageSize);
        }
        IPage<DetectResult> pageResult = detectResultService.selectPage(pageParams, querDetectResultDto);
        List<DetectResult> detectResults =pageResult.getRecords();

        Long total = pageResult.getTotal();
        return R.ok().data("total", total).data("rows", detectResults);
    }

    @ApiOperation("保存检测结果信息")
    @PostMapping("/save/{id}")
    public R saveDetectResult(@RequestBody List<DetectResult> detectResultList, @PathVariable("id") String id) {
//        DetectResult detectResultFromDb = detectResultService.getById(id);
//        if(detectResultFromDb != null) {
//            return R.error().message("检测结果已存在");
//        }
        for(DetectResult detectResult : detectResultList) {
            detectResult.setId(id);
            boolean save = detectResultService.save(detectResult);
            if(!save) {
                return R.error().message("添加检测结果出错");
            }
        }
        ContractBase contractBase = new ContractBase();
        contractBase.setStatus(3);
        contractBase.setResultId(id);
        boolean updateContractBase = contractBaseService.update(contractBase, new LambdaQueryWrapper<ContractBase>().eq(ContractBase::getFileId, id));
        if(!updateContractBase) {
            return R.error().message("更新合约审计状态出错");
        }
        return R.ok().message("保存检测信息及更新合约审计状态成功");
    }


    @ApiOperation("查询检测结果信息")
    @PostMapping("/query/{id}")
    public R queryDetectResult(@PathVariable("id") String id) {
        DetectResult detectResult = detectResultService.getById(id);
        if(detectResult != null) {
            return R.ok().data("detectResult", detectResult);
        } else {
            log.error("没有查询到检测结果");
            return R.error().message("没有查询到检测结果");
        }
    }


    @ApiOperation("查询检测结果信息")
    @PostMapping("/querydetectfileid/{id}")
    public String queryDetectFileId(@PathVariable("id") String id) {
        DetectResult detectResult = detectResultService.getById(id);
        if(detectResult != null) {
            return detectResult.getResultId();
        } else {
            log.error("没有查询到检测结果");
            return "";
        }
    }


    @ApiOperation("删除检测结果信息")
    @PostMapping("/delete/{id}")
    public R deleteDetectResult(@PathVariable("id") String id) {
        boolean b = detectResultService.removeById(id);
        if(b) {
            return R.ok().message("检测结果信息从数据库删除成功");
        } else {
            return R.error().message("检测结果信息从数据库删除失败");
        }
    }

}

