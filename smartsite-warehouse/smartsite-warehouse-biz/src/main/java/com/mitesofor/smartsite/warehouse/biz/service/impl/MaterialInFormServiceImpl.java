
package com.mitesofor.smartsite.warehouse.biz.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mitesofor.smartsite.common.core.constant.CommonConstants;
import com.mitesofor.smartsite.common.core.util.R;
import com.mitesofor.smartsite.common.security.service.IntelligenceUser;
import com.mitesofor.smartsite.common.security.util.SecurityUtils;
import com.mitesofor.smartsite.message.api.feign.SendNoticeService;
import com.mitesofor.smartsite.message.api.model.NoticeSendModel;
import com.mitesofor.smartsite.warehouse.api.dto.MaterialInFormQueryDto;
import com.mitesofor.smartsite.warehouse.api.dto.MaterialInFormReviewerDto;
import com.mitesofor.smartsite.warehouse.api.entity.Material;
import com.mitesofor.smartsite.warehouse.api.entity.MaterialInForm;
import com.mitesofor.smartsite.warehouse.api.entity.MaterialType;
import com.mitesofor.smartsite.warehouse.biz.mapper.MaterialInFormMapper;
import com.mitesofor.smartsite.warehouse.biz.mapper.MaterialMapper;
import com.mitesofor.smartsite.warehouse.biz.mapper.MaterialTypeMapper;
import com.mitesofor.smartsite.warehouse.biz.service.MaterialInFormService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

/**
 * 物料出库单
 *
 * @author jchen
 * @date 2023-07-11 16:44:14
 */
@Service
public class MaterialInFormServiceImpl extends ServiceImpl<MaterialInFormMapper, MaterialInForm> implements MaterialInFormService {

    @Autowired
    private MaterialInFormMapper materialInFormMapper;

    @Autowired
    private MaterialMapper materialMapper;

    @Autowired
    SendNoticeService sendNoticeService;

    @Autowired
    private MaterialTypeMapper materialTypeMapper;

    @Override
    public IPage<MaterialInForm> getMaterialInFormPage2(Integer pageNo, Integer pageSize, MaterialInFormQueryDto materialInFormQueryDto) {
        Page<MaterialInForm> page = new Page(pageNo, pageSize);
        Page<MaterialInForm> materialInFormPage =  materialInFormMapper.selectMaterialInFormByPage(page,materialInFormQueryDto);
        return materialInFormPage;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public R reviewerMaterialInForm(MaterialInFormReviewerDto materialInFormReviewerDto) {
        MaterialInForm materialInForm = materialInFormMapper.selectById(materialInFormReviewerDto.getId());
        if(null==materialInForm){
            return R.failed("入库单不存在");
        }
        IntelligenceUser user = SecurityUtils.getUser();
        Assert.notNull(user,"请先登录");

        BeanUtils.copyProperties(materialInFormReviewerDto,materialInForm,"id","materialId","formNumber");
        //通过
        if(materialInFormReviewerDto.getReviewState().equals(CommonConstants.PASS)){
            materialInForm.setReviewState(CommonConstants.PASS);
            materialInForm.setInTime(LocalDateTime.now());
            Material material = materialMapper.selectById(materialInForm.getMaterialId());
            MaterialType materialType = materialTypeMapper.selectById(material.getType());
            if(null==material){
                return R.failed("物料信息错误");
            }
            //添加库存
            material.setInventoryNum(material.getInventoryNum().add(materialInFormReviewerDto.getRealNumber()));

            materialInForm.setReviewerId(null==materialInFormReviewerDto.getReviewerId()?user.getId():materialInFormReviewerDto.getReviewerId());
            materialInForm.setReviewer(StringUtils.isEmpty(materialInFormReviewerDto.getReviewer())?user.getUsername():materialInFormReviewerDto.getReviewer());
            materialInForm.setReviewTime(LocalDateTime.now());
            materialInFormMapper.updateById(materialInForm);
            materialMapper.updateById(material);

            NoticeSendModel noticeSendModel = new NoticeSendModel();
            noticeSendModel.setBizType("入库单");
            noticeSendModel.setContent("你有"+materialInFormReviewerDto.getRealNumber()+"件"+materialType.getName()+"入库");
            noticeSendModel.setUserId(1);
            //暂不开放
            R<Boolean> r = sendNoticeService.sendNotice(noticeSendModel);
            return R.ok();
        }
        //拒绝
        if(materialInFormReviewerDto.getReviewState().equals(CommonConstants.REFUSE)){
            materialInForm.setReviewState(CommonConstants.REFUSE);
            materialInFormMapper.updateById(materialInForm);
            return R.ok();
        }
        return R.failed();
    }

    @Override
    public Boolean saveMaterialInForm(MaterialInForm materialInForm) {
        IntelligenceUser user = SecurityUtils.getUser();
        Assert.notNull(user,"请先登录");
        materialInForm.setCreateBy(null==materialInForm.getCreateBy()?user.getId():materialInForm.getCreateBy());
        materialInForm.setApplicant(StringUtils.isEmpty(materialInForm.getApplicant())?user.getUsername():materialInForm.getApplicant());
        materialInForm.setApplicantId(null==materialInForm.getApplicantId()?user.getId():materialInForm.getApplicantId());
        materialInForm.setReviewState(CommonConstants.AUDIT);
        materialInForm.setApplyTime(LocalDateTime.now());
        materialInForm.setCreateTime(LocalDateTime.now());
        materialInForm.setUpdateTime(LocalDateTime.now());;
        int i = materialInFormMapper.insert(materialInForm);
        if(i>0){
            return true;
        }
        return false;
    }
}
