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
@ApiModel(value = "车辆进出信息", description = "车辆进出信息")
public class CarInAndOut extends Model<CarInAndOut> {
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
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
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

    @ApiModelProperty(value = "位置状态")
    private String location;
    /**
     * isDelete 是否删除，是1， 否0
     */
    @ApiModelProperty(value = "是否删除，是1，否0")
    private Integer isDelete;

}
