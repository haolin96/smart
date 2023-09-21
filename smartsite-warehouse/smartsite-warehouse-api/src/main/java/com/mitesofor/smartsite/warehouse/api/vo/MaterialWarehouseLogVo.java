package com.mitesofor.smartsite.warehouse.api.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Description 仓储日志
 * @Author jchen
 * @Date 2023/7/20 11:26
 */
@Data
public class MaterialWarehouseLogVo {
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
    @ApiModelProperty("出库数量")
    private BigDecimal inNumber;
    @ApiModelProperty("入库数量")
    private BigDecimal outNumber;
    @ApiModelProperty("经办人id")
    private Integer createBy;
    @ApiModelProperty("经办人")
    private String createName;
    @ApiModelProperty("领料人")
    private String applicant;
    @ApiModelProperty("入/出库时间")
    private LocalDateTime ioTime;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("审核人")
    private String reviewer;
    @ApiModelProperty("哈希码")
    private String hashCode;
}
