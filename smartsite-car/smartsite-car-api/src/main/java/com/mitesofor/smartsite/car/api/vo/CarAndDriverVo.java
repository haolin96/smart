package com.mitesofor.smartsite.car.api.vo;

import com.mitesofor.smartsite.car.api.entity.Car;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CarAndDriverVo extends Car {
    /**
     * id
     */
    @ApiModelProperty(value = "主键", required = true)
    private Integer id;


    @ApiModelProperty(value = "车主姓名")
    private String driverName;

    @ApiModelProperty(value = "驾驶证号码")
    private String driverId;

    @ApiModelProperty(value = "驾驶证照片")
    private String driverLicensePhoto;

    @ApiModelProperty(value = "单位或组织")
    private String driverCompany;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "家庭地址")
    private String address;

    @ApiModelProperty(value = "项目名称")
    private String projectName;
}
