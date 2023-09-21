package com.mitesofor.smartsite.warehouse.biz.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mitesofor.smartsite.common.data.datascope.SmartsiteBaseMapper;
import com.mitesofor.smartsite.warehouse.api.dto.MaterialLogQueryDto;
import com.mitesofor.smartsite.warehouse.api.dto.MaterialQueryDto;
import com.mitesofor.smartsite.warehouse.api.entity.Material;
import com.mitesofor.smartsite.warehouse.api.entity.MaterialInForm;
import com.mitesofor.smartsite.warehouse.api.entity.MaterialOutForm;
import com.mitesofor.smartsite.warehouse.api.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.annotation.ParametersAreNonnullByDefault;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface MaterialMapper extends SmartsiteBaseMapper<Material> {

    Page<Material> queryBypage(Page<Material> page,@Param("dto") MaterialQueryDto materialQueryDto);

    MaterialTotalCountVo countMaterialTotal(@Param("proId") Integer proId);

    List<MaterialNumInfoVo> countMaterialTypeTodayTotal(@Param("proId") Integer proId);

    List<MaterialNumInfoVo> countMaterialTypeTotal(@Param("proId") Integer proId);

    List<MaterialInventoryCountVo> countMaterialTypeNum(@Param("proId") Integer proId);

//    Page<MaterialWarehouseLogVo> warehouseLog(Page<MaterialWarehouseLogVo> page);
//
//    List<MaterialWarehouseLogVo> warehouseLog2();

    List<MaterialWarehouseLogVo> warehouseLogForIn();

    MaterialInForm selectMaterialInFormMaxTimeList(@Param("materialId") Integer materialId,@Param("ioTime") LocalDateTime ioTime);

    Material selectMaterialInfoById(@Param("id") Integer materialId);

    List<MaterialWarehouseLogVo> warehouseLogForOut();

    MaterialOutForm selectMaterialOutFormMaxTimeList(@Param("materialId") Integer materialId,@Param("ioTime") LocalDateTime ioTime);

    List<MaterialInfoAnalyseVo> countMaterialInfoAnalyse(@Param("proId") Integer proId);

    List<MaterialWarehouseLogVo> warehouseLogForInList(@Param("dto") MaterialLogQueryDto materialLogQueryDto);

    List<MaterialWarehouseLogVo> warehouseLogForOutList(@Param("dto") MaterialLogQueryDto materialLogQueryDto);
}
