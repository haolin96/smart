package com.mitesofor.smartsite.warehouse.api.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Description
 * @Author jchen
 * @Date 2023/7/20 14:52
 */
@Data
public class MaterialMyReviewerVo {
    @ApiModelProperty("物料id")
    private Integer materialId;
    @ApiModelProperty("物料编号")
    private String materialSn;
    @ApiModelProperty("物料名称")
    private Integer type;
    @ApiModelProperty("物料名称")
    private String typeName;
    @ApiModelProperty("物料类型")
    private Integer model;
    @ApiModelProperty("物料类型")
    private String modelName;
    @ApiModelProperty("物料单位")
    private String unit;
    @ApiModelProperty("物料库存")
    private BigDecimal inventoryNum;
    @ApiModelProperty("出库入库标识")
    private String picture;
    @ApiModelProperty("审核表单id")
    private Integer id;
    @ApiModelProperty("申请人id")
    private Integer createBy;
    @ApiModelProperty("申请时间")
    private LocalDateTime createTime;
    @ApiModelProperty("审核状态")
    private String reviewState;
    @ApiModelProperty("审核人id")
    private Integer reviewerId;
    @ApiModelProperty("审核人")
    private String reviewer;
    @ApiModelProperty("审批时间")
    private LocalDateTime reviewTime;
    @ApiModelProperty("出库入库标识")
    private String mark;
    @ApiModelProperty("申请人名称")
    private String createName;
    @ApiModelProperty("损耗比例")
    private BigDecimal lossRatio;
    @ApiModelProperty("最小预警库存")
    private BigDecimal minWarningInventoryNum;
    @ApiModelProperty("最大预警库存")
    private BigDecimal maxWarningInventoryNum;
}
