package com.mitesofor.smartsite.person.api.vo;

import com.mitesofor.smartsite.person.api.entity.PersonInAndOut;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PersonInAndOutVO extends PersonInAndOut {

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
     * 人脸照片
     */
    @ApiModelProperty(value = "人脸照片")
    private String facePhoto;

    /**
     * 紧急联系人电话
     */
    @ApiModelProperty(value = "紧急联系人电话")
    private String emergencyContactNumber;

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

}
