package com.mitesofor.smartsite.warehouse.biz.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.net.HttpHeaders;
import com.mitesofor.smartsite.common.core.util.R;
import com.mitesofor.smartsite.common.log.annotation.SysLog;
import com.mitesofor.smartsite.warehouse.api.dto.MaterialInFormQueryDto;
import com.mitesofor.smartsite.warehouse.api.dto.MaterialInFormReviewerDto;
import com.mitesofor.smartsite.warehouse.api.entity.MaterialInForm;
import com.mitesofor.smartsite.warehouse.biz.service.MaterialInFormService;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 物料入库单
 *
 * @author jchen
 * @date 2023-07-11 16:44:14
 */
@RestController
@AllArgsConstructor
@RequestMapping("/materialInForm" )
@Api(value = "物料入库单管理", tags = "物料入库单管理")
public class MaterialInFormController {

    private final MaterialInFormService materialInFormService;

    /**
     * 分页查询
     * @param pageSize 每页条数
     * @param pageSize 当前页数
     * @param materialInFormQueryDto 查询参数
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page/{pageNo}/{pageSize}" )
    public R getMaterialInFormPage2(@PathVariable("pageNo" ) Integer pageNo,@PathVariable("pageSize" ) Integer pageSize, MaterialInFormQueryDto materialInFormQueryDto) {
        return R.ok(materialInFormService.getMaterialInFormPage2(pageNo, pageSize,materialInFormQueryDto));
    }

    /**
     * 通过id查询物料入库单
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) Integer id) {
        return R.ok(materialInFormService.getById(id));
    }

    /**
     * 新增物料入库单
     * @param materialInForm 物料入库单
     * @return R
     */

    @SysLog("新增物料入库单" )
    @PostMapping("/save")
    @ApiOperation(value = "新增物料入库单", notes = "新增物料入库单")
    public R save(@RequestBody MaterialInForm materialInForm) {
        return R.ok(materialInFormService.saveMaterialInForm(materialInForm));
    }

    /**
     * 修改物料入库单
     * @param materialInForm 物料入库单
     * @return R
     */
    @ApiOperation(value = "修改物料入库单", notes = "修改物料入库单")
    @SysLog("修改物料入库单" )
    @PutMapping("/update")
    public R updateById(@RequestBody MaterialInForm materialInForm) {
        return R.ok(materialInFormService.updateById(materialInForm));
    }

    /**
     * 物料入库单审核
     * @param materialInFormReviewerDto 物料入库单审核参数
     * @return R
     */
    @ApiOperation(value = "物料入库单审核", notes = "物料入库单审核")
    @SysLog("物料入库单审核" )
    @PutMapping("/reviewerMaterialInForm")
    public R reviewerMaterialInForm(@RequestBody MaterialInFormReviewerDto materialInFormReviewerDto) {
        return materialInFormService.reviewerMaterialInForm(materialInFormReviewerDto);
    }

    /**
     * 通过id删除物料入库单
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除物料入库单", notes = "通过id删除物料入库单")
    @SysLog("通过id删除物料入库单" )
    @DeleteMapping("/{id}" )
    public R removeById(@PathVariable Integer id) {
        return R.ok(materialInFormService.removeById(id));
    }

}
