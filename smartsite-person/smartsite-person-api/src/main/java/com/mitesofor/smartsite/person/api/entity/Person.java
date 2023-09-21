package com.mitesofor.smartsite.person.api.entity;

//import com.baomidou.mybatisplus.annotation.TableName;
import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ApiModel(value = "人员信息", description = "人员基本信息")
public class Person extends Model<Person> {
    /**
     * id
     */
    @ApiModelProperty(value = "主键", required = true)
    private Integer id;
    /**
     * 身份证id
     */
    @ApiModelProperty(value = "身份证号", required = true)
    private String personId;
    /**
     * 人员名字
     */
    @ApiModelProperty(value = "姓名")
    private String personName;
    /**
     * 年龄
     */
    @ApiModelProperty(value = "年龄")
    private Integer age;
    /**
     * 性别 男0 女1
     */
    @ApiModelProperty(value = "性别，男0女1")
    private Integer gender;
    /**
     * 电话
     */
    @ApiModelProperty(value = "电话")
    private String phone;
    /**
     * 民族
     */
    @ApiModelProperty(value = "民族")
    private String nationality;
    /**
     * 籍贯
     */
    @ApiModelProperty(value = "籍贯")
    private String birthplace;
    /**
     * 家庭住址
     */
    @ApiModelProperty(value = "家庭住址")
    private String homeAddress;
    /**
     * 所属企业
     */
    @ApiModelProperty(value = "所属企业")
    private String company;
    /**
     * 所属班组
     */
    @ApiModelProperty(value = "所属班组")
    private String team;
    /**
     * 政治面貌
     */
    @ApiModelProperty(value = "政治面貌")
    private String politicalStatus;
    /**
     * 学历
     */
    @ApiModelProperty(value = "学历")
    private String education;
    /**
     * 岗位
     */
    @ApiModelProperty(value = "人员工种")
    private String job;
    /**
     * 岗位类型
     */
    @ApiModelProperty(value = "人员类型")
    private String jobType;
    /**
     * 人脸照片
     */
    @ApiModelProperty(value = "人脸照片")
    private String facePhoto;
    /**
     * 身份证正面
     */
    @ApiModelProperty(value = "身份证正面")
    private String idCardPhotoFront;

    /**
     * 身份证反面
     */
    @ApiModelProperty(value = "身份证反面")
    private String idCardPhotoBack;
    /**
     * 位置状态
     */
    @ApiModelProperty(value = "位置状态")
    private String location;

    /**
     * 紧急联系人电话
     */
    @ApiModelProperty(value = "紧急联系人电话")
    private String emergencyContactNumber;



    @ApiModelProperty(value = "证件类型，塔吊1，升降机2，搅拌机3，渣土车4，挖掘机5，其他6")
    private Integer licenseType;

    @ApiModelProperty(value = "证件Id")
    private String licenseId;

    @ApiModelProperty(value = "证件照")
    private String licensePhoto;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @ApiModelProperty(value = "有效期")
    private Date validityPeriod;

    @ApiModelProperty(value = "是否验证，是1，否0")
    private Integer isChecked;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String memo;

    /**
     * 所属项目id
     */
    @ApiModelProperty(value = "所属项目id")
    private String projectId;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;



    @ApiModelProperty(value = "是否删除")
    private Integer isDelete;
}
