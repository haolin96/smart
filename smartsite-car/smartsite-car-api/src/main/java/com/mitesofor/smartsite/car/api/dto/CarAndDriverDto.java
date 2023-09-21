package com.mitesofor.smartsite.car.api.dto;

import com.mitesofor.smartsite.car.api.entity.Car;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CarAndDriverDto extends Car {
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
