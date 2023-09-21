package com.mitesofor.smartsite.warehouse.api.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description 大屏物料统计分析vo
 * @Author jchen
 * @Date 2023/8/9 14:43
 */
@Data
public class MaterialInfoAnalyseVo {
    @ApiModelProperty("物料id")
    private Integer id;
    @ApiModelProperty("物料名称")
    private String materialName;
    @ApiModelProperty("物料型号名称")
    private String materialModelName;
    @ApiModelProperty("累计总量")
    private BigDecimal inNum;
    @ApiModelProperty("使用量")
    private BigDecimal useNum;
    @ApiModelProperty("剩余量")
    private BigDecimal inventoryNum;
    @ApiModelProperty("hash码")
    private String hashCode;
}
