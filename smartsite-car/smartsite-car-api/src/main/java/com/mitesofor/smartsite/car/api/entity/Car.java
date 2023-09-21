package com.mitesofor.smartsite.car.api.entity;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ApiModel(value = "车辆基本信息", description = "车辆基本信息")
public class Car extends Model<Car> {

    /**
     * id
     */
    @ApiModelProperty(value = "主键", required = true)
    private Integer id;

    /**
     * carId 车牌号
     */
    @ApiModelProperty(value = "车牌号", required = true)
    private String carId;

    /**
     * company 所属公司
     */
    @ApiModelProperty(value = "所属公司")
    private String carCompany;

    /**
     * driver 驾驶员
     */
    @ApiModelProperty(value = "驾驶员")
    private String driver;

    /**
     * driverId 驾驶员Id
     */
    @ApiModelProperty(value = "驾驶员Id")
    private String driverId;

    /**
     * carBrand 车辆品牌
     */
    @ApiModelProperty(value = "车辆品牌")
    private String carBrand;

    /**
     * carType 车辆种类
     */
    @ApiModelProperty(value = "车辆种类, 1货车，2洒水车")
    private Integer carType;

    /**
     * carRegisterPlace 车辆注册地点
     */
    @ApiModelProperty(value = "车辆注册地点")
    private String carRegisterPlace;

    /**
     * carRegisterDate 车辆注册日期
     */

    @ApiModelProperty(value = "车辆注册日期")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date carRegisterDate;

    /**
     * carRegisterId 车辆注册号码
     */
    @ApiModelProperty(value = "车辆注册号码")
    private String carRegisterId;

//    /**
//     * owner 车辆所有人
//     */
//    @ApiModelProperty(value = "车辆所有人")
//    private String owner;
    /**
     * length 车辆长度
     */
    @ApiModelProperty(value = "车辆长度")
    private Double lengths;

    /**
     * heights 车辆高度
     */
    @ApiModelProperty(value = "车辆高度")
    private Double heights;

    /**
     * axles 车轴数
     */
    @ApiModelProperty(value = "车轴数")
    private Integer axles;

    /**
     * wheels 车轮数
     */
    @ApiModelProperty(value = "车轮数")
    private Integer wheels;

    /**
     * loadCapacity 载重能力
     */
    @ApiModelProperty(value = "载重能力")
    private Double loadCapacity;
    /**
     * carLicensePhoto 行驶证照片
     */
    @ApiModelProperty(value = "行驶证照片")
    private String carLicensePhoto;

    /**
     * licensePlatePhoto 车牌照片
     */
    @ApiModelProperty(value = "车牌照片")
    private String licensePlatePhoto;
    /**
     * carHeadPhoto 车头照片
     */
    @ApiModelProperty(value = "车头照片")
    private String carHeadPhoto;
    /**
     * carTailPhoto 车尾照片
     */
    @ApiModelProperty(value = "车尾照片")
    private String carTailPhoto;
    /**
     * carBodyPhoto 车身全景图
     */
    @ApiModelProperty(value = "车身全景图")
    private String carBodyPhoto;
    /**
     * memo 备注
     */
    @ApiModelProperty(value = "备注")
    private String memo;

//    /**
//     * location 位置状态
//     */
//    @ApiModelProperty(value = "位置状态")
//    private String location;

    /**
     * projectId 项目id
     */
    @ApiModelProperty(value = "项目id")
    private Integer projectId;

    /**
     * createTime 创建时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;


    @ApiModelProperty(value = "是否删除，是1，否0")
    private Integer isDelete;
}
