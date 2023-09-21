package com.mitesofor.smartsite.warehouse.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description
 * @Author jchen
 * @Date 2023/7/11 17:44
 */
@Data
public class MaterialQueryDto {
    @ApiModelProperty(value = "物料名称")
    private String name;

    @ApiModelProperty(value = "物料编号")
    private String materialSn;

    @ApiModelProperty(value = "物料类型（水泥、钢筋、沙子）")
    private Integer type;

    @ApiModelProperty(value = "项目id")
    private Integer proId;
}
