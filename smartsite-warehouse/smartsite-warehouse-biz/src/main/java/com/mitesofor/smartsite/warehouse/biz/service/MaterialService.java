package com.mitesofor.smartsite.warehouse.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mitesofor.smartsite.warehouse.api.dto.MaterialLogQueryDto;
import com.mitesofor.smartsite.warehouse.api.dto.MaterialQueryDto;
import com.mitesofor.smartsite.warehouse.api.entity.Material;
import com.mitesofor.smartsite.warehouse.api.entity.MaterialInForm;
import com.mitesofor.smartsite.warehouse.api.vo.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 *设备标定设置
 */
public interface MaterialService extends IService<Material> {

    IPage<Material> queryByPage(Integer pageNo, Integer pageSize, MaterialQueryDto materialQueryDto);

    MaterialTotalCountVo countMaterialTotal(Integer proId);

    List<MaterialTotalCountTypeVo> countMaterialTypeTotal(Integer proId);

    List<MaterialInventoryCountVo> countMaterialTypeNum(Integer proId);

    List<MaterialWarehouseLogVo> warehouseLog(MaterialLogQueryDto materialLogQueryDto);

    List<MaterialInfoAnalyseVo> countMaterialInfoAnalyse(Integer proId);
}
