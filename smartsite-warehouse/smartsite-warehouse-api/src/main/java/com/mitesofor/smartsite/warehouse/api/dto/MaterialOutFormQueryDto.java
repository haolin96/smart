package com.mitesofor.smartsite.warehouse.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description
 * @Author jchen
 * @Date 2023/7/12 9:52
 */
@Data
public class MaterialOutFormQueryDto {
    @ApiModelProperty(value="物料名称")
    private String materialName;

    /**
     * 出库时间
     */
    @ApiModelProperty(value="出库开始时间")
    private LocalDateTime outFormStartTime;

    /**
     * 出库时间
     */
    @ApiModelProperty(value="出库结束时间")
    private LocalDateTime outFormEndTime;

    /**
     * 审核状态
     */
    @ApiModelProperty(value="审核状态")
    private List<String> reviewStateList;
}
