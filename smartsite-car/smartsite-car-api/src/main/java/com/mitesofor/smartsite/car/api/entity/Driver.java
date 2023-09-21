package com.mitesofor.smartsite.car.api.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "车主信息", description = "车主信息")
public class Driver {

    @ApiModelProperty(value = "驾驶证号码", required = true)
    private String driverId;

    @ApiModelProperty(value = "车主姓名")
    private String driverName;

    @ApiModelProperty(value = "驾驶证照片")
    private String driverLicensePhoto;

    @ApiModelProperty(value = "单位或组织")
    private String driverCompany;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "家庭地址")
    private String address;
}
