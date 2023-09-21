package com.mitesofor.smartsite.person.api.entity;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ApiModel(value = "人员进出信息", description = "包含进出时间")
public class PersonInAndOut extends Model<PersonInAndOut> {
    /**
     * id
     */
    @ApiModelProperty(value = "主键", required = true)
    private String id;
    /**
     * 人员id
     */
    @ApiModelProperty(value = "人员id", required = true)
    private String personId;
    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String personName;
    /**
     * 时间
     */
    @ApiModelProperty(value = "日期时间")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date inOrOutTime;
    /**
     * 闸机口号
     */
    @ApiModelProperty(value = "闸机口号")
    private Integer entranceId;
    /**
     * 出/入，入为1，出为-1
     */
    @ApiModelProperty(value = "出/入，入1，出0")
    private Integer inOrOut;

    @ApiModelProperty(value = "拍摄人脸照片")
    private String faceInoutPhoto;

    @ApiModelProperty(value = "是否是在册人员，是1，否0")
    private Integer isRegistered;

    @ApiModelProperty(value = "出勤是否正常，1正常，0迟到，2早退")
    private Integer presenceStatus;

    @ApiModelProperty(value = "是否删除，是1， 否0")
    private Integer isDelete;

    @ApiModelProperty(value = "相似度")
    private String similarity;

    @ApiModelProperty(value = "抓拍地点")
    private String photoPlace;
}
