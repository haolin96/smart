package com.mitesofor.smartsite.warehouse.biz.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mitesofor.smartsite.common.core.util.R;
import com.mitesofor.smartsite.common.log.annotation.SysLog;
import com.mitesofor.smartsite.warehouse.api.dto.MaterialTypeQueryDto;
import com.mitesofor.smartsite.warehouse.api.entity.MaterialType;
import com.mitesofor.smartsite.warehouse.biz.service.MaterialTypeService;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 物料类型
 *
 * @author jchen
 * @date 2023-07-12 09:19:47
 */
@RestController
@AllArgsConstructor
@RequestMapping("/materialType" )
@Api(value = "materialType", tags = "物料类型管理")
public class MaterialTypeController {

    private final MaterialTypeService materialTypeService;

    /**
     * 分页查询类型层级 只做一级类型搜索
     * @param pageSize 每页条数
     * @param pageSize 当前页数
     * @param materialTypeQueryDto 查询参数
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/selectTypeByPage/{pageNo}/{pageSize}" )
    public R selectTypeByPage(@PathVariable("pageNo" ) Integer pageNo,@PathVariable("pageSize" ) Integer pageSize, MaterialTypeQueryDto materialTypeQueryDto) {
        return R.ok(materialTypeService.selectTypeByPage(pageNo,pageSize,materialTypeQueryDto));
    }

    /**
     * 查询类型层级 (搜索只做一级类型)
     * @param materialTypeQueryDto 查询参数
     * @return
     */
    @ApiOperation(value = "查询列表", notes = "查询列表")
    @GetMapping("/selectTypeByList" )
    public R selectTypeByList(MaterialTypeQueryDto materialTypeQueryDto) {
        return R.ok(materialTypeService.selectTypeByList(materialTypeQueryDto));
    }

    /**
     * 通过id查询物料类型
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
//    @PreAuthorize("@pms.hasPermission('meterial_meterialtype_view')" )
    public R getById(@PathVariable("id" ) Integer id) {
        return R.ok(materialTypeService.getById(id));
    }

    /**
     * 新增物料类型
     * @param materialType 物料类型
     * @return R
     */
    @ApiOperation(value = "新增物料类型", notes = "新增物料类型")
    @SysLog("新增物料类型" )
    @PostMapping
    //@PreAuthorize("@pms.hasPermission('meterial_meterialtype_add')" )
    public R save(@RequestBody MaterialType materialType) {
        return R.ok(materialTypeService.save(materialType));
    }

    /**
     * 修改物料类型
     * @param materialType 物料类型
     * @return R
     */
    @ApiOperation(value = "修改物料类型", notes = "修改物料类型")
    @SysLog("修改物料类型" )
    @PutMapping
    //@PreAuthorize("@pms.hasPermission('meterial_meterialtype_edit')" )
    public R updateById(@RequestBody MaterialType materialType) {
        return R.ok(materialTypeService.updateById(materialType));
    }

    /**
     * 通过id删除物料类型
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除物料类型", notes = "通过id删除物料类型")
    @SysLog("通过id删除物料类型" )
    @DeleteMapping("/{id}" )
    //@PreAuthorize("@pms.hasPermission('meterial_meterialtype_del')" )
    public R removeById(@PathVariable Integer id) {
        return R.ok(materialTypeService.removeById(id));
    }

}
