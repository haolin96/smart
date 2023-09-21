package com.mitesofor.smartsite.warehouse.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mitesofor.smartsite.common.core.util.R;
import com.mitesofor.smartsite.warehouse.api.dto.MaterialOutFormQueryDto;
import com.mitesofor.smartsite.warehouse.api.dto.MaterialOutFormReviewerDto;
import com.mitesofor.smartsite.warehouse.api.entity.MaterialOutForm;

import java.time.LocalDateTime;

/**
 * 物料出库单
 *
 * @author jchen
 * @date 2023-07-11 14:37:55
 */
public interface MaterialOutFormService extends IService<MaterialOutForm> {

    IPage<MaterialOutForm> getMaterialOutFormPage(Integer pageNo, Integer pageSize, MaterialOutFormQueryDto materialOutFormQueryDto);

    R reviewerMaterialOutForm(MaterialOutFormReviewerDto materialOutFormReviewerDto);

    R queryMyReviewer(Integer pageNo, Integer pageSize, String reviewState, Integer reviewerId,Integer materialId, LocalDateTime startTime, LocalDateTime endTime);

    R queryMyCreate(Integer pageNo, Integer pageSize, String reviewState, Integer createBy, Integer materialId, LocalDateTime startTime,LocalDateTime endTime);

    boolean saveMaterialOutForm(MaterialOutForm materialOutForm);
}
