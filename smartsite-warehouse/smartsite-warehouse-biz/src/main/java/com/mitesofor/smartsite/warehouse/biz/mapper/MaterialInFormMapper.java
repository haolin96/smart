package com.mitesofor.smartsite.warehouse.biz.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mitesofor.smartsite.common.data.datascope.SmartsiteBaseMapper;
import com.mitesofor.smartsite.warehouse.api.dto.MaterialInFormQueryDto;
import com.mitesofor.smartsite.warehouse.api.entity.MaterialInForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * 物料出库单
 *
 * @author jchen
 * @date 2023-07-11 16:44:14
 */
@Mapper
public interface MaterialInFormMapper extends SmartsiteBaseMapper<MaterialInForm> {

    Page<MaterialInForm> selectMaterialInFormByPage(Page<MaterialInForm> page, @Param("dto") MaterialInFormQueryDto materialInFormQueryDto);

    BigDecimal countMaterialInNum(@Param("materialId") Integer materialId);
}
