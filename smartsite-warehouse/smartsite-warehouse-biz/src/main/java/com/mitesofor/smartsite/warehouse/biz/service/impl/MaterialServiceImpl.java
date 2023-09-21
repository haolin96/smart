
package com.mitesofor.smartsite.warehouse.biz.service.impl;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mitesofor.smartsite.common.core.exception.CheckedException;
import com.mitesofor.smartsite.warehouse.api.dto.MaterialLogQueryDto;
import com.mitesofor.smartsite.warehouse.api.dto.MaterialQueryDto;
import com.mitesofor.smartsite.warehouse.api.entity.Material;
import com.mitesofor.smartsite.warehouse.api.entity.MaterialInForm;
import com.mitesofor.smartsite.warehouse.api.entity.MaterialOutForm;
import com.mitesofor.smartsite.warehouse.api.vo.*;
import com.mitesofor.smartsite.warehouse.biz.mapper.MaterialInFormMapper;
import com.mitesofor.smartsite.warehouse.biz.mapper.MaterialMapper;
import com.mitesofor.smartsite.warehouse.biz.mapper.MaterialOutFormMapper;
import com.mitesofor.smartsite.warehouse.biz.service.MaterialService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 设备标定设置
 *
 */
@Service
@AllArgsConstructor
@Slf4j
public class MaterialServiceImpl extends ServiceImpl<MaterialMapper, Material> implements MaterialService {

    @Autowired
    private MaterialMapper materialMapper;

    @Autowired
    private MaterialInFormMapper materialInFormMapper;

    @Autowired
    private MaterialOutFormMapper materialOutFormMapper;

    @Override
    public IPage<Material> queryByPage(Integer pageNo, Integer pageSize, MaterialQueryDto materialQueryDto) {
        Page<Material> page = new Page(pageNo, pageSize);
        Page<Material> materialPage =  materialMapper.queryBypage(page,materialQueryDto);
        materialPage.getRecords().forEach(material -> {
            //统计物料入库数量
//            BigDecimal materialNum = materialInFormMapper.countMaterialInNum(material.getId());
//            material.setMaterialInNum(materialNum);
        });
        return materialPage;
    }

    @Override
    public MaterialTotalCountVo countMaterialTotal(Integer proId) {
        MaterialTotalCountVo materialTotalCountVo = materialMapper.countMaterialTotal(proId);
        return materialTotalCountVo;
    }

    @Override
    public List<MaterialTotalCountTypeVo> countMaterialTypeTotal(Integer proId) {
        List<MaterialTotalCountTypeVo> materialTotalCountTypeVos = new ArrayList<>();

        List<MaterialNumInfoVo> materialTodayNumInfos = materialMapper.countMaterialTypeTodayTotal(proId);
        List<MaterialNumInfoVo> materialNumInfos = materialMapper.countMaterialTypeTotal(proId);

        MaterialTotalCountTypeVo materialTodayCountTypeVo = new MaterialTotalCountTypeVo();
        materialTodayCountTypeVo.setDataMark("0");
        materialTodayCountTypeVo.setMaterialNumInfoList(materialTodayNumInfos);
        materialTotalCountTypeVos.add(materialTodayCountTypeVo);

        MaterialTotalCountTypeVo materialCountTypeVo = new MaterialTotalCountTypeVo();
        materialCountTypeVo.setDataMark("1");
        materialCountTypeVo.setMaterialNumInfoList(materialNumInfos);
        materialTotalCountTypeVos.add(materialCountTypeVo);
        return materialTotalCountTypeVos;
    }

    @Override
    public List<MaterialInventoryCountVo> countMaterialTypeNum(Integer proId) {
        List<MaterialInventoryCountVo> materialInventoryCountVos = materialMapper.countMaterialTypeNum(proId);
        return materialInventoryCountVos;
    }

    @Override
    public List<MaterialWarehouseLogVo> warehouseLog(MaterialLogQueryDto materialLogQueryDto) {
        //List<MaterialWarehouseLogVo> materialIoMaxTimeVos =  materialMapper.warehouseLogForIn();
        List<MaterialWarehouseLogVo> materialIoMaxTimeVos =  materialMapper.warehouseLogForInList(materialLogQueryDto);
        materialIoMaxTimeVos.forEach(materialWarehouseLogVo -> {
            //MaterialInForm materialInForm = materialMapper.selectMaterialInFormMaxTimeList(materialWarehouseLogVo.getMaterialId(),materialWarehouseLogVo.getIoTime());
            Material material = materialMapper.selectMaterialInfoById(materialWarehouseLogVo.getMaterialId());
//            materialWarehouseLogVo.setApplicant(materialInForm.getApplicant());
//            materialWarehouseLogVo.setCreateName(materialInForm.getCreateName());
//            materialWarehouseLogVo.setInNumber(materialInForm.getRealNumber());
//            materialWarehouseLogVo.setRemark(materialInForm.getRemark());
//            materialWarehouseLogVo.setReviewer(materialInForm.getReviewer());
//            materialWarehouseLogVo.setType(null!=material?material.getType():null);
//            materialWarehouseLogVo.setUnit(null!=material?material.getUnit():null);
//            materialWarehouseLogVo.setMaterialSn(null!=material?material.getMaterialSn():null);
//            materialWarehouseLogVo.setModel(null!=material?material.getModel():null);
            materialWarehouseLogVo.setModelName(null!=material?material.getModelName():null);
            materialWarehouseLogVo.setTypeName(null!=material?material.getTypeName():null);
            materialWarehouseLogVo.setHashCode(null!=material?material.getHashCode():null);
        });

        //List<MaterialWarehouseLogVo> materialOutMaxTimeVos =  materialMapper.warehouseLogForOut();
        List<MaterialWarehouseLogVo> materialOutMaxTimeVos =  materialMapper.warehouseLogForOutList(materialLogQueryDto);
        materialOutMaxTimeVos.forEach(materialWarehouseLogVo -> {
            //MaterialOutForm materialOutForm = materialMapper.selectMaterialOutFormMaxTimeList(materialWarehouseLogVo.getMaterialId(),materialWarehouseLogVo.getIoTime());
            Material material = materialMapper.selectMaterialInfoById(materialWarehouseLogVo.getMaterialId());
//            materialWarehouseLogVo.setApplicant(materialOutForm.getApplicant());
//            materialWarehouseLogVo.setCreateName(materialOutForm.getCreateName());
//            materialWarehouseLogVo.setOutNumber(materialOutForm.getNumber());
//            materialWarehouseLogVo.setRemark(materialOutForm.getRemark());
//            materialWarehouseLogVo.setReviewer(materialOutForm.getReviewer());
//            materialWarehouseLogVo.setType(null!=material?material.getType():null);
//            materialWarehouseLogVo.setUnit(null!=material?material.getUnit():null);
//            materialWarehouseLogVo.setMaterialSn(null!=material?material.getMaterialSn():null);
//            materialWarehouseLogVo.setModel(null!=material?material.getModel():null);
            materialWarehouseLogVo.setModelName(null!=material?material.getModelName():null);
            materialWarehouseLogVo.setTypeName(null!=material?material.getTypeName():null);
            materialWarehouseLogVo.setHashCode(null!=material?material.getHashCode():null);
        });

        materialOutMaxTimeVos.addAll(materialIoMaxTimeVos);

//        Map<Integer,List<MaterialWarehouseLogVo>> map = materialOutMaxTimeVos.stream().collect(Collectors.groupingBy(MaterialWarehouseLogVo::getMaterialId));
//        List<MaterialWarehouseLogVo> materialWarehouseLogVoList = new ArrayList<>();
//        map.forEach((k,v)->{
//            MaterialWarehouseLogVo materialWarehouseLogVo = v.stream().max(Comparator.comparing(MaterialWarehouseLogVo::getIoTime)).get();
//            materialWarehouseLogVoList.add(materialWarehouseLogVo);
//        });
        return materialOutMaxTimeVos;
    }

    @Override
    public List<MaterialInfoAnalyseVo> countMaterialInfoAnalyse(Integer proId) {
        List<MaterialInfoAnalyseVo> materialInfoAnalyseVos = materialMapper.countMaterialInfoAnalyse(proId);
        materialInfoAnalyseVos.forEach(materialInfoAnalyseVo -> {
            BigDecimal inNum = materialInFormMapper.countMaterialInNum(materialInfoAnalyseVo.getId());
            BigDecimal outNum = materialOutFormMapper.countMaterialOutNum(materialInfoAnalyseVo.getId());
            materialInfoAnalyseVo.setInNum(inNum);
            materialInfoAnalyseVo.setUseNum(outNum);
        });
        return materialInfoAnalyseVos;
    }
}
