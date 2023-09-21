package com.mitesofor.smartsite.warehouse.api.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description 入库单审核dto
 * @Author jchen
 * @Date 2023/7/12 10:10
 */
@Data
public class MaterialInFormReviewerDto {
    /**
     *
     */
    @TableId
    @ApiModelProperty(value="")
    private Integer id;
    /**
     * 物料id
     */
    @ApiModelProperty(value="物料id")
    private Integer materialId;
    /**
     * 实际数量
     */
    @ApiModelProperty(value="实际数量")
    private BigDecimal realNumber;
    /**
     * 货单数量
     */
    @ApiModelProperty(value="货单数量")
    private BigDecimal formNumber;
    /**
     * 单价
     */
    @ApiModelProperty(value="单价")
    private BigDecimal unitPrice;
    /**
     * 审核人
     */
    @ApiModelProperty(value="审核人")
    private String reviewer;
    /**
     * 审核人id
     */
    @ApiModelProperty(value="审核人id")
    private Integer reviewerId;
    /**
     * 审核状态（待审核：0，通过：1，拒绝：2）
     */
    @ApiModelProperty(value="审核状态（待审核：0，通过：1，拒绝：2）")
    private String reviewState;
}
