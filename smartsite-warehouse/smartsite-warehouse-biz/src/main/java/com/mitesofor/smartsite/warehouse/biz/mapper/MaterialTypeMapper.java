package com.mitesofor.smartsite.warehouse.biz.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mitesofor.smartsite.common.data.datascope.SmartsiteBaseMapper;
import com.mitesofor.smartsite.warehouse.api.dto.MaterialTypeQueryDto;
import com.mitesofor.smartsite.warehouse.api.entity.MaterialType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 物料类型
 *
 * @author jchen
 * @date 2023-07-12 09:19:47
 */
@Mapper
public interface MaterialTypeMapper extends SmartsiteBaseMapper<MaterialType> {

    Page<MaterialType> selectTypeByPage(Page<MaterialType> page,@Param("dto") MaterialTypeQueryDto materialTypeQueryDto);

    List<MaterialType> selectSubTypeByParentId(@Param("parentId") Integer id);

    List<MaterialType> selectTypeByList(@Param("dto") MaterialTypeQueryDto materialTypeQueryDto);
}
