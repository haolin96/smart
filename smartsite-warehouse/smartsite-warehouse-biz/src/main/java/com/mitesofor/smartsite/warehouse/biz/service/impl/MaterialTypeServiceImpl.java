
package com.mitesofor.smartsite.warehouse.biz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mitesofor.smartsite.warehouse.api.dto.MaterialTypeQueryDto;
import com.mitesofor.smartsite.warehouse.api.entity.MaterialInForm;
import com.mitesofor.smartsite.warehouse.api.entity.MaterialType;
import com.mitesofor.smartsite.warehouse.biz.mapper.MaterialTypeMapper;
import com.mitesofor.smartsite.warehouse.biz.service.MaterialTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 物料类型
 *
 * @author jchen
 * @date 2023-07-12 09:19:47
 */
@Service
public class MaterialTypeServiceImpl extends ServiceImpl<MaterialTypeMapper, MaterialType> implements MaterialTypeService {

    @Autowired
    private MaterialTypeMapper materialTypeMapper;

    @Override
    public IPage<MaterialType> selectTypeByPage(Integer pageSize, Integer pageNo, MaterialTypeQueryDto materialTypeQueryDto) {
        Page<MaterialType> page = new Page(pageNo, pageSize);
        //根据条件只查询一级类目
        Page<MaterialType> materialInFormPage =  materialTypeMapper.selectTypeByPage(page,materialTypeQueryDto);
        materialInFormPage.getRecords().forEach(materialType -> {
            findTypeChildren(materialType);
        });
        return materialInFormPage;
    }

    @Override
    public List<MaterialType> selectTypeByList(MaterialTypeQueryDto materialTypeQueryDto) {
        List<MaterialType> materialTypes = materialTypeMapper.selectTypeByList(materialTypeQueryDto);
        materialTypes.forEach(materialType -> {
            findTypeChildren(materialType);
        });
        return materialTypes;
    }

    /**
     * 获取下级节点
     *
     * @param materialType  当前节点
     */
    private void findTypeChildren(MaterialType materialType) {
        List<MaterialType> children = materialTypeMapper.selectSubTypeByParentId(materialType.getId());
        materialType.setChildList(children);
        children.forEach(child -> this.findTypeChildren(child));
    }
}
