package com.mitesofor.smartsite.car.api.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CarMaterial {

    @ApiModelProperty(value = "主键", required = true)
    private Integer id;

    @ApiModelProperty(value = "车牌号")
    private String carId;

    @ApiModelProperty(value = "驾驶员")
    private String driver;

    @ApiModelProperty(value = "物料id")
    private Integer materialId;

    @ApiModelProperty(value = "物料种类id")
    private Integer materialTypeId;

    @ApiModelProperty(value = "物料种类")
    private String materialTypeName;

    @ApiModelProperty(value = "毛重")
    private Double weight;

    @ApiModelProperty(value = "进1, 出0")
    private Integer inOrOut;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "物料重量")
    private Double materialWeight;
}
