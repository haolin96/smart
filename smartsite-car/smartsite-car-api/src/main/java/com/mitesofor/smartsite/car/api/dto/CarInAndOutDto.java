package com.mitesofor.smartsite.car.api.dto;

import cn.hutool.core.date.DateTime;
import com.mitesofor.smartsite.car.api.entity.Car;
import com.mitesofor.smartsite.car.api.entity.CarInAndOut;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class CarInAndOutDto extends Car {
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
     * time 时间
     */
    @ApiModelProperty(value = "进出时间")
    private Date inOrOutTime;
    /**
     * inOrOut 出/入，入为1，出为-1
     */
    @ApiModelProperty(value = "进/出，进1，出-1")
    private Integer inOrOut;
    /**
     * channelId 通道口
     */
    @ApiModelProperty(value = "通道口")
    private String channelId;



    @ApiModelProperty(value = "是否注册，是1，否0")
    private Integer isRegistered;

    @ApiModelProperty(value = "是否清洗，是1，否0")
    private Integer isCleaned;

    @ApiModelProperty(value = "是否清洗，是1，否0")
    private String location;

    /**
     * licensePlatePhoto 车牌照片
     */
    @ApiModelProperty(value = "车牌照片")
    private String licensePlateInoutPhoto;
    /**
     * carHeadPhoto 车头照片
     */
    @ApiModelProperty(value = "车头照片")
    private String carHeadInoutPhoto;
    /**
     * carTailPhoto 车尾照片
     */
    @ApiModelProperty(value = "车尾照片")
    private String carTailInoutPhoto;
    /**
     * carBodyPhoto 车身全景图
     */
    @ApiModelProperty(value = "车身全景图")
    private String carBodyInoutPhoto;

    /**
     * isDelete 是否删除，是1， 否0
     */
    @ApiModelProperty(value = "是否删除，是1，否0")
    private Integer isDelete;
}
