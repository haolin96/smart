package com.mitesofor.smartsite.warehouse.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 预警试图对象
 */
@Data
@ApiModel(value = "预警试图对象")
public class WarningVO {
    /**
     * 预警时间
     */
    @ApiModelProperty(value = "预警时间")
    private LocalDateTime WarningTime;
    /**
     * 预警信息
     */
    @ApiModelProperty(value = "预警信息")
    private String WarningInfo;

    /**
     * 预警状态
     */
    @ApiModelProperty(value = "预警状态")
    private String status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String note;
}
