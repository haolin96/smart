package com.mitesofor.smartsite.warehouse.api.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 物料出库单
 *
 * @author jchen
 * @date 2023-07-11 14:37:55
 */
@Data
@TableName("b_material_out_form")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "物料出库单")
public class MaterialOutForm extends Model<MaterialOutForm> {
private static final long serialVersionUID = 1L;

    /**
     * 1
     */
    @TableId
    @ApiModelProperty(value="1")
    private Integer id;
    /**
     * 物料id
     */
    @ApiModelProperty(value="物料id")
    private Integer materialId;
    /**
     * 数量
     */
    @ApiModelProperty(value="数量")
    private BigDecimal number;
    /**
     * 备注
     */
    @ApiModelProperty(value="备注")
    private String remark;
    /**
     * 申请人
     */
    @ApiModelProperty(value="申请人")
    private String applicant;
    /**
     * 申请人id
     */
    @ApiModelProperty(value="申请人id")
    private Integer applicantId;
    /**
     * 审核人
     */
    @ApiModelProperty(value="审核人")
    private String reviewer;
    /**
     * 审核状态（待审核：0，通过：1，拒绝：2）
     */
    @ApiModelProperty(value="审核状态（待审核：0，通过：1，拒绝：2）")
    private String reviewState;
    /**
     * 审核人id
     */
    @ApiModelProperty(value="审核人id")
    private Integer reviewerId;
    /**
     * 审核时间
     */
    @ApiModelProperty(value="审核时间")
    private LocalDateTime reviewTime;
    /**
     * 项目id
     */
    @ApiModelProperty(value="项目id")
    private Integer proId;
    /**
     * 出入库时间
     */
    @ApiModelProperty(value="出入库时间")
    private LocalDateTime outFormTime;
    /**
     * 创建人
     */
    @ApiModelProperty(value="创建人")
    private Integer createBy;

    /**
     * 创建人
     */
    @ApiModelProperty(value="创建人",hidden = true)
    @TableField(exist = false)
    private String createName;
    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间")
    private LocalDateTime createTime;
    /**
     * 更新人
     */
    @ApiModelProperty(value="更新人")
    private Integer updateBy;
    /**
     * 更新时间
     */
    @ApiModelProperty(value="更新时间")
    private LocalDateTime updateTime;

    }
