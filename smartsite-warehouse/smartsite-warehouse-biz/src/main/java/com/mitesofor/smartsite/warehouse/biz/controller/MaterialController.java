package com.mitesofor.smartsite.warehouse.biz.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mitesofor.smartsite.common.core.constant.CacheConstants;
import com.mitesofor.smartsite.common.core.util.R;
import com.mitesofor.smartsite.common.log.annotation.SysLog;
import com.mitesofor.smartsite.warehouse.api.dto.MaterialLogQueryDto;
import com.mitesofor.smartsite.warehouse.api.dto.MaterialQueryDto;
import com.mitesofor.smartsite.warehouse.api.entity.Material;
import com.mitesofor.smartsite.warehouse.api.vo.*;
import com.mitesofor.smartsite.warehouse.biz.service.MaterialService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 物料
 *
 */
@RestController
@AllArgsConstructor
@RequestMapping("/material")
@Api(value = "物料管理", tags = "物料管理")
public class MaterialController {

	private final MaterialService materialService;

	/**
	 * 分页查询
	 * @param pageSize 每页条数
	 * @param pageSize 当前页数
	 * @parammaterial 查询参数
	 * @return
	 */
	@ApiOperation(value = "分页查询", notes = "分页查询")
	@GetMapping("/page/{pageNo}/{pageSize}")
	public R getSysTenantPage(@PathVariable("pageNo" ) Integer pageNo,@PathVariable("pageSize" ) Integer pageSize, MaterialQueryDto materialQueryDto) {
		return R.ok(materialService.queryByPage(pageNo,pageSize,materialQueryDto));
	}

	/**
	 * 通过id查询物料
	 * @param id id
	 * @return R
	 */
	@ApiOperation(value = "通过id查询物料", notes = "通过id查询物料")
	@GetMapping("/{id}")
	public R getById(@PathVariable("id") Integer id) {
		return R.ok(materialService.getById(id));
	}

	/**
	 * 新增物料
	 * @parammaterial 物料
	 * @return R
	 */
	@SysLog("新增物料")
	@ApiOperation(value = "新增物料", notes = "新增物料")
	@PostMapping("/save")
	@CacheEvict(value = CacheConstants.TENANT_DETAILS, allEntries = true)
	public R save(@RequestBody Material material) {
		material.setInventoryNum(null==material.getInventoryNum()?new BigDecimal(0):material.getInventoryNum());
		material.setCreateTime(LocalDateTime.now());
		return R.ok(materialService.save(material));
	}

	/**
	 * 修改物料
	 * @parammaterial 物料
	 * @return R
	 */
	@SysLog("修改物料")
	@ApiOperation(value = "修改物料", notes = "修改物料")
	@PutMapping("/updateById")
	public R updateById(@RequestBody Material material) {
		return R.ok(materialService.updateById(material));
	}

	/**
	 * 通过id删除物料
	 * @param id id
	 * @return R
	 */
	@SysLog("删除物料")
	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除物料", notes = "删除物料")
	//@PreAuthorize("@pms.hasPermission('admin_material_del')")
	@CacheEvict(value = CacheConstants.TENANT_DETAILS, allEntries = true)
	public R removeById(@PathVariable Integer id) {
		return R.ok(materialService.removeById(id));
	}

	/**
	 * 仓储日志
	 * @return
	 */
	@ApiOperation(value = "仓储日志", notes = "仓储日志")
	@GetMapping("/warehouseLog")
	public R warehouseLog(MaterialLogQueryDto materialLogQueryDto) {
		List<MaterialWarehouseLogVo> materialWarehouseLogVos = materialService.warehouseLog(materialLogQueryDto);
		return R.ok(materialWarehouseLogVos);
	}

	/**
	 * 查询全部物料
	 * @return
	 */
	@ApiOperation(value = "查询全部物料", notes = "查询全部物料")
	@GetMapping("/list")
	public R list() {
		List<Material> materials =materialService.list();
		return R.ok(materials);
	}

	/**
	 * 大屏物料总数
	 * @return
	 */
	@ApiOperation(value = "大屏物料总数", notes = "大屏物料总数")
	@GetMapping("/countMaterialTotal")
	public R<MaterialTotalCountVo> countMaterialTotal(Integer proId) {
		MaterialTotalCountVo materialTotalCountVo = materialService.countMaterialTotal(proId);
		return R.ok(materialTotalCountVo);
	}

	/**
	 * 大屏物料总数
	 * @return
	 */
	@ApiOperation(value = "大屏收料/实际", notes = "大屏收料/实际")
	@GetMapping("/countMaterialTypeTotal")
	public R<List<MaterialTotalCountTypeVo>> countMaterialTypeTotal(Integer proId) {
		List<MaterialTotalCountTypeVo> materialTotalCountVo = materialService.countMaterialTypeTotal(proId);
		return R.ok(materialTotalCountVo);
	}

	/**
	 * 物料分类分析
	 * @return
	 */
	@ApiOperation(value = "物料分类分析", notes = "物料分类分析")
	@GetMapping("/countMaterialTypeNum")
	public R<List<MaterialInventoryCountVo>> countMaterialTypeNum(Integer proId) {
		List<MaterialInventoryCountVo> materialInventoryCountVos = materialService.countMaterialTypeNum(proId);
		return R.ok(materialInventoryCountVos);
	}

	/**
	 * 物料统计分析
	 * @return
	 */
	@ApiOperation(value = "物料统计分析", notes = "物料统计分析")
	@GetMapping("/countMaterialInfoAnalyse")
	public R<List<MaterialInfoAnalyseVo>> countMaterialInventoryNum(Integer proId) {
		List<MaterialInfoAnalyseVo> materialInfoAnalyseVos = materialService.countMaterialInfoAnalyse(proId);
		return R.ok(materialInfoAnalyseVos);
	}
}
