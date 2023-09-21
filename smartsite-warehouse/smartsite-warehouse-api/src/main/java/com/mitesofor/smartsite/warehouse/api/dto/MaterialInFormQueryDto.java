package com.mitesofor.smartsite.warehouse.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Description
 * @Author jchen
 * @Date 2023/7/11 17:06
 */
@Data
public class MaterialInFormQueryDto {
    @ApiModelProperty(value="物料名称")
    private String materialName;
    @ApiModelProperty(value="生产厂家")
    private String manufacturer;
    @ApiModelProperty(value="购买来源")
    private String buySource;
    @ApiModelProperty(value="审核状态（待审核：0，通过：1，拒绝：2）")
    private List<String> reviewStateList;
}
