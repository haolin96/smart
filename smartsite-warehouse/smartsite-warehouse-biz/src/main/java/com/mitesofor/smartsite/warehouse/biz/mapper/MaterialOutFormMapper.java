package com.mitesofor.smartsite.warehouse.biz.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mitesofor.smartsite.common.data.datascope.SmartsiteBaseMapper;
import com.mitesofor.smartsite.warehouse.api.dto.MaterialOutFormQueryDto;
import com.mitesofor.smartsite.warehouse.api.entity.MaterialOutForm;
import com.mitesofor.smartsite.warehouse.api.vo.MaterialMyReviewerVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 物料出库单
 *
 * @author jchen
 * @date 2023-07-11 14:37:55
 */
@Mapper
public interface MaterialOutFormMapper extends SmartsiteBaseMapper<MaterialOutForm> {

    Page<MaterialOutForm> getMaterialOutFormPage(Page<MaterialOutForm> page,@Param("dto") MaterialOutFormQueryDto materialOutFormQueryDto);

    Page<MaterialMyReviewerVo> queryMyReviewer(Page<MaterialMyReviewerVo> page, @Param("reviewStates") List<String> reviewStates,@Param("createBy") Integer createBy,

                                               @Param("reviewer") Integer reviewer,@Param("materialId") Integer materialId,@Param("startTime") LocalDateTime startTime, @Param("endTime")LocalDateTime endTime);

    BigDecimal countMaterialOutNum( @Param("materialId") Integer materialId);

    //Page<MaterialMyReviewerVo> queryMyCreate(Page<MaterialMyReviewerVo> page,@Param("reviewStates") List<String> reviewStates,@Param("createBy") Integer createBy,@Param("materialId") Integer materialId,@Param("startTime") LocalDateTime startTime,@Param("endTime") LocalDateTime endTime);
}
