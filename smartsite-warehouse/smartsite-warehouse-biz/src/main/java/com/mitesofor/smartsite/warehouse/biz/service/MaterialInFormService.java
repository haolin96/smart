package com.mitesofor.smartsite.warehouse.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mitesofor.smartsite.common.core.util.R;
import com.mitesofor.smartsite.warehouse.api.dto.MaterialInFormQueryDto;
import com.mitesofor.smartsite.warehouse.api.dto.MaterialInFormReviewerDto;
import com.mitesofor.smartsite.warehouse.api.entity.MaterialInForm;

/**
 * 物料出库单
 *
 * @author jchen
 * @date 2023-07-11 16:44:14
 */
public interface MaterialInFormService extends IService<MaterialInForm> {
    IPage<MaterialInForm> getMaterialInFormPage2(Integer pageNo, Integer pageSize, MaterialInFormQueryDto materialInFormQueryDto);

    R reviewerMaterialInForm(MaterialInFormReviewerDto materialInFormReviewerDto);

    Boolean saveMaterialInForm(MaterialInForm materialInForm);
}
