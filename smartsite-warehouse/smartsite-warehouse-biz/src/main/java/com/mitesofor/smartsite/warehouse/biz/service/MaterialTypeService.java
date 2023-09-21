package com.mitesofor.smartsite.warehouse.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mitesofor.smartsite.warehouse.api.dto.MaterialTypeQueryDto;
import com.mitesofor.smartsite.warehouse.api.entity.MaterialType;

import java.util.List;


/**
 * 物料类型
 *
 * @author jchen
 * @date 2023-07-12 09:19:47
 */
public interface MaterialTypeService extends IService<MaterialType> {

    IPage<MaterialType> selectTypeByPage(Integer pageNo, Integer pageSize, MaterialTypeQueryDto materialTypeQueryDto);

    List<MaterialType> selectTypeByList(MaterialTypeQueryDto materialTypeQueryDto);
}
