package com.mitesofor.smartsite.warehouse.biz.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mitesofor.smartsite.common.core.util.R;
import com.mitesofor.smartsite.common.log.annotation.SysLog;
import com.mitesofor.smartsite.warehouse.api.dto.MaterialInFormQueryDto;
import com.mitesofor.smartsite.warehouse.api.dto.MaterialInFormReviewerDto;
import com.mitesofor.smartsite.warehouse.api.dto.MaterialOutFormQueryDto;
import com.mitesofor.smartsite.warehouse.api.dto.MaterialOutFormReviewerDto;
import com.mitesofor.smartsite.warehouse.api.entity.MaterialOutForm;
import com.mitesofor.smartsite.warehouse.biz.service.MaterialOutFormService;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


/**
 * 物料出库单
 *
 * @author jchen
 * @date 2023-07-11 14:37:55
 */
@RestController
@AllArgsConstructor
@RequestMapping("/materialOutForm" )
@Api(value = "materialOutForm", tags = "物料出库单管理")
public class MaterialOutFormController {

    private final MaterialOutFormService materialOutFormService;

    /**
     * 分页查询
     * @param pageSize 每页条数
     * @param pageSize 当前页数
     * @param materialOutFormQueryDto 查询参数
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page/{pageNo}/{pageSize}" )
    public R getMaterialOutFormPage(@PathVariable("pageNo" ) Integer pageNo,@PathVariable("pageSize" ) Integer pageSize, MaterialOutFormQueryDto materialOutFormQueryDto) {
        return R.ok(materialOutFormService.getMaterialOutFormPage(pageNo, pageSize,materialOutFormQueryDto));
    }


    /**
     * 通过id查询物料出库单
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) Integer id) {
        return R.ok(materialOutFormService.getById(id));
    }

    /**
     * 新增物料出库单
     * @param materialOutForm 物料出库单
     * @return R
     */
    @ApiOperation(value = "新增物料出库单", notes = "新增物料出库单")
    @SysLog("新增物料出库单" )
    @PostMapping("/save")
    public R save(@RequestBody MaterialOutForm materialOutForm) {
        return R.ok(materialOutFormService.saveMaterialOutForm(materialOutForm));
    }

    /**
     * 修改物料出库单
     * @param materialOutForm 物料出库单
     * @return R
     */
    @ApiOperation(value = "修改物料出库单", notes = "修改物料出库单")
    @SysLog("修改物料出库单" )
    @PutMapping("/updateById")
    public R updateById(@RequestBody MaterialOutForm materialOutForm) {
        return R.ok(materialOutFormService.updateById(materialOutForm));
    }

    /**
     * 物料出库单审核
     * @param materialOutFormReviewerDto 物料出库单审核
     * @return R
     */
    @ApiOperation(value = "物料出库单审核", notes = "物料出库单审核")
    @SysLog("物料出库单审核" )
    @PutMapping("/reviewerMaterialOutForm")
    public R reviewerMaterialOutForm(@RequestBody MaterialOutFormReviewerDto materialOutFormReviewerDto) {
        return materialOutFormService.reviewerMaterialOutForm(materialOutFormReviewerDto);
    }

    /**
     * 通过id删除物料出库单
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除物料出库单", notes = "通过id删除物料出库单")
    @SysLog("通过id删除物料出库单" )
    @DeleteMapping("/{id}" )
    public R removeById(@PathVariable Integer id) {
        return R.ok(materialOutFormService.removeById(id));
    }

    /**
     * 我的审批
     * @param reviewState
     * @return R
     */
    @ApiOperation(value = "我的审批", notes = "我的审批")
    @GetMapping("/queryMyReviewer/{pageNo}/{pageSize}" )
    public R queryMyReviewer(@PathVariable("pageNo" ) Integer pageNo, @PathVariable("pageSize" ) Integer pageSize, String reviewState, Integer reviewerId, Integer materialId, LocalDateTime startTime,LocalDateTime endTime) {
        return materialOutFormService.queryMyReviewer(pageNo,pageSize,reviewState,reviewerId,materialId,startTime,endTime);
    }

    /**
     * 我的发起的
     * @param createBy 创建人id
     * @return R
     */
    @ApiOperation(value = "我的发起的", notes = "我的发起的")
    @GetMapping("/queryMyCreate/{pageNo}/{pageSize}" )
    public R queryMyCreate(@PathVariable("pageNo" ) Integer pageNo,@PathVariable("pageSize" ) Integer pageSize, String reviewState, Integer createBy, Integer materialId, LocalDateTime startTime,LocalDateTime endTime) {
        return materialOutFormService.queryMyCreate(pageNo,pageSize,reviewState,createBy,materialId,startTime,endTime);
    }
}
