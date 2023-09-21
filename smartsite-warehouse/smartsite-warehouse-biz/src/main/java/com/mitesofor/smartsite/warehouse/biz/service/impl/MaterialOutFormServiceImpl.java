
package com.mitesofor.smartsite.warehouse.biz.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mitesofor.smartsite.common.core.constant.CommonConstants;
import com.mitesofor.smartsite.common.core.util.R;
import com.mitesofor.smartsite.common.security.service.IntelligenceUser;
import com.mitesofor.smartsite.common.security.util.SecurityUtils;
import com.mitesofor.smartsite.warehouse.api.dto.MaterialOutFormQueryDto;
import com.mitesofor.smartsite.warehouse.api.dto.MaterialOutFormReviewerDto;
import com.mitesofor.smartsite.warehouse.api.entity.Material;
import com.mitesofor.smartsite.warehouse.api.entity.MaterialInForm;
import com.mitesofor.smartsite.warehouse.api.entity.MaterialOutForm;
import com.mitesofor.smartsite.warehouse.api.vo.MaterialMyReviewerVo;
import com.mitesofor.smartsite.warehouse.biz.mapper.MaterialMapper;
import com.mitesofor.smartsite.warehouse.biz.mapper.MaterialOutFormMapper;
import com.mitesofor.smartsite.warehouse.biz.service.MaterialOutFormService;
import org.hibernate.validator.internal.constraintvalidators.bv.time.futureorpresent.AbstractFutureOrPresentJavaTimeValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 物料出库单
 *
 * @author jchen
 * @date 2023-07-11 14:37:55
 */
@Service
public class MaterialOutFormServiceImpl extends ServiceImpl<MaterialOutFormMapper, MaterialOutForm> implements MaterialOutFormService {

    @Autowired
    private MaterialOutFormMapper materialOutFormMapper;

    @Autowired
    private MaterialMapper materialMapper;

    @Override
    public IPage<MaterialOutForm> getMaterialOutFormPage(Integer pageNo, Integer pageSize, MaterialOutFormQueryDto materialOutFormQueryDto) {
        Page<MaterialOutForm> page = new Page(pageNo, pageSize);
        Page<MaterialOutForm> materialOutFormPage =  materialOutFormMapper.getMaterialOutFormPage(page,materialOutFormQueryDto);
        return materialOutFormPage;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public R reviewerMaterialOutForm(MaterialOutFormReviewerDto materialOutFormReviewerDto) {
        MaterialOutForm materialOutForm = materialOutFormMapper.selectById(materialOutFormReviewerDto.getId());
        if(null==materialOutForm){
            return R.failed("出库单不存在");
        }

        IntelligenceUser user = SecurityUtils.getUser();
        Assert.notNull(user,"请先登录");

        BeanUtils.copyProperties(materialOutFormReviewerDto,materialOutForm,"id","materialId");
        //通过
        if(materialOutFormReviewerDto.getReviewState().equals(CommonConstants.PASS)){
            //查询剩余库存
            Material material = materialMapper.selectById(materialOutForm.getMaterialId());
            if(material.getInventoryNum().compareTo(materialOutForm.getNumber())<0){
                return R.failed("当前库存不足");
            }

            materialOutForm.setReviewState(CommonConstants.PASS);
            materialOutForm.setOutFormTime(LocalDateTime.now());
            materialOutForm.setReviewerId(null==materialOutFormReviewerDto.getReviewerId()?user.getId():materialOutFormReviewerDto.getReviewerId());
            materialOutForm.setReviewer(StringUtils.isEmpty(materialOutFormReviewerDto.getReviewer())?user.getUsername():materialOutFormReviewerDto.getReviewer());
            materialOutForm.setReviewTime(LocalDateTime.now());
            //添加库存
            if(null!=material){
                material.setInventoryNum(material.getInventoryNum().subtract(materialOutFormReviewerDto.getNumber()));
            }
            materialOutFormMapper.updateById(materialOutForm);
            materialMapper.updateById(material);
            return R.ok();
        }
        //拒绝
        if(materialOutFormReviewerDto.getReviewState().equals(CommonConstants.REFUSE)){
            materialOutForm.setReviewState(CommonConstants.REFUSE);
            materialOutFormMapper.updateById(materialOutForm);
            return R.ok();
        }
        return R.failed();
    }

    @Override
    public R queryMyReviewer(Integer pageNo, Integer pageSize, String reviewState, Integer reviewerId,Integer materialId, LocalDateTime startTime,LocalDateTime endTime) {
        IntelligenceUser user = SecurityUtils.getUser();
        Assert.notNull(user,"请先登录");
        if(null==reviewerId){
            reviewerId = user.getId();
        }
        List<String> reviewStates = new ArrayList<>();
        if(!StringUtils.isEmpty(reviewState)){
            reviewStates = Arrays.asList(reviewState.split(","));
        }
        Page<MaterialMyReviewerVo> page = new Page(pageNo, pageSize);
        Page<MaterialMyReviewerVo> materialMyReviewerVos = materialOutFormMapper.queryMyReviewer(page,reviewStates,null,reviewerId,materialId,startTime,endTime);
        materialMyReviewerVos.getRecords().forEach(materialMyReviewerVo -> {
            Material material = materialMapper.selectMaterialInfoById(materialMyReviewerVo.getMaterialId());
            materialMyReviewerVo.setMaterialSn(null!=material?material.getMaterialSn():null);
            materialMyReviewerVo.setLossRatio(null!=material?material.getLossRatio():null);
            materialMyReviewerVo.setInventoryNum(null!=material?material.getInventoryNum():null);
            materialMyReviewerVo.setMaxWarningInventoryNum(null!=material?material.getMaxWarningInventoryNum():null);
            materialMyReviewerVo.setMinWarningInventoryNum(null!=material?material.getMinWarningInventoryNum():null);
            materialMyReviewerVo.setType(null!=material?material.getType():null);
            materialMyReviewerVo.setTypeName(null!=material?material.getTypeName():null);
            materialMyReviewerVo.setModel(null!=material?material.getModel():null);
            materialMyReviewerVo.setModelName(null!=material?material.getModelName():null);
            materialMyReviewerVo.setPicture(null!=material?material.getPicture():null);
            materialMyReviewerVo.setUnit(null!=material?material.getUnit():null);
        });
        return R.ok(materialMyReviewerVos);
    }

    @Override
    public R queryMyCreate(Integer pageNo, Integer pageSize, String reviewState, Integer createBy, Integer materialId, LocalDateTime startTime,LocalDateTime endTime) {
        IntelligenceUser user = SecurityUtils.getUser();
        Assert.notNull(user,"请先登录");
        if(null==createBy){
            createBy = user.getId();
        }
        List<String> reviewStates = new ArrayList<>();
        if(!StringUtils.isEmpty(reviewState)){
            reviewStates = Arrays.asList(reviewState.split(",")) ;
        }
        Page<MaterialMyReviewerVo> page = new Page(pageNo, pageSize);
        Page<MaterialMyReviewerVo> materialMyReviewerVos = materialOutFormMapper.queryMyReviewer(page,  reviewStates,  createBy, null, materialId,  startTime, endTime);
        materialMyReviewerVos.getRecords().forEach(materialMyReviewerVo -> {
            Material material = materialMapper.selectMaterialInfoById(materialMyReviewerVo.getMaterialId());
            materialMyReviewerVo.setMaterialSn(null!=material?material.getMaterialSn():null);
            materialMyReviewerVo.setLossRatio(null!=material?material.getLossRatio():null);
            materialMyReviewerVo.setInventoryNum(null!=material?material.getInventoryNum():null);
            materialMyReviewerVo.setMaxWarningInventoryNum(null!=material?material.getMaxWarningInventoryNum():null);
            materialMyReviewerVo.setMinWarningInventoryNum(null!=material?material.getMinWarningInventoryNum():null);
            materialMyReviewerVo.setType(null!=material?material.getType():null);
            materialMyReviewerVo.setTypeName(null!=material?material.getTypeName():null);
            materialMyReviewerVo.setModel(null!=material?material.getModel():null);
            materialMyReviewerVo.setModelName(null!=material?material.getModelName():null);
            materialMyReviewerVo.setPicture(null!=material?material.getPicture():null);
            materialMyReviewerVo.setUnit(null!=material?material.getUnit():null);
        });
        return R.ok(materialMyReviewerVos);
    }

    @Override
    public boolean saveMaterialOutForm(MaterialOutForm materialOutForm) {
        IntelligenceUser user = SecurityUtils.getUser();
        Assert.notNull(user,"请先登录");
        materialOutForm.setCreateBy(null==materialOutForm.getCreateBy()?user.getId():materialOutForm.getCreateBy());
        materialOutForm.setApplicant(StringUtils.isEmpty(materialOutForm.getApplicant())?user.getUsername():materialOutForm.getApplicant());
        materialOutForm.setApplicantId(null==materialOutForm.getApplicantId()?user.getId():materialOutForm.getApplicantId());
        materialOutForm.setReviewState(CommonConstants.AUDIT);
        materialOutForm.setCreateTime(LocalDateTime.now());
        materialOutForm.setUpdateTime(LocalDateTime.now());
        int i = materialOutFormMapper.insert(materialOutForm);
        if(i>0){
            return true;
        }
        return false;
    }

}
