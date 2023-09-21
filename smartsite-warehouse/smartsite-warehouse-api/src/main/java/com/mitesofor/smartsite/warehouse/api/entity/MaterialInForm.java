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
 * @date 2023-07-11 16:44:14
 */
@Data
@TableName("b_material_in_form")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "物料出库单")
public class MaterialInForm extends Model<MaterialInForm> {
private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @TableId
    @ApiModelProperty(value="")
    private Integer id;
    /**
     * 物料id
     */
    @ApiModelProperty(value="物料id")
    private Integer materialId;
    /**
     * 实际数量
     */
    @ApiModelProperty(value="实际数量")
    private BigDecimal realNumber;
    /**
     * 货单数量
     */
    @ApiModelProperty(value="货单数量")
    private BigDecimal formNumber;

    /**
     * 损耗比例
     */
    @ApiModelProperty(value="损耗比例")
    private BigDecimal lossRatio;

    /**
     * 单价
     */
    @ApiModelProperty(value="单价")
    private BigDecimal unitPrice;

    /**
     * 供应商地址
     */
    @ApiModelProperty(value="供应商地址")
    private String manufacturerAddress;

    /**
     * 供应商名称
     */
    @ApiModelProperty(value="供应商名称")
    private String manufacturer;

    /**
     * 联系电话
     */
    @ApiModelProperty(value="联系电话")
    private String phone;

    /**
     * 采购人
     */
    @ApiModelProperty(value="采购人")
    private String procureUser;

    /**
     * 出厂日期
     */
    @ApiModelProperty(value="出厂日期")
    private LocalDateTime materialOutTime;
    /**
     * 申请入库时间
     */
    @ApiModelProperty(value="申请入库时间")
    private LocalDateTime applyTime;
    /**
     * 物品照片
     */
    @ApiModelProperty(value="物品照片")
    private String picture;
    /**
     * 入库时间
     */
    @ApiModelProperty(value="入库时间")
    private LocalDateTime inTime;
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
     * 审核状态（待审核：0，通过：1，拒绝：2）
     */
    @ApiModelProperty(value="审核状态（待审核：0，通过：1，拒绝：2）")
    private String reviewState;
    /**
     * 哈希码
     */
    @ApiModelProperty(value="哈希码")
    private String hashCode;
    /**
     * 备注
     */
    @ApiModelProperty(value="备注")
    private String remark;
    /**
     * 创建人
     */
    @ApiModelProperty(value="创建人(经办人-填表单的人)")
    private Integer createBy;

    /**
     * 创建人
     */
    @ApiModelProperty(value="创建人(经办人-填表单的人)",hidden = true)
    @TableField(exist = false)
    private String createName;

    /**
     * 更新人
     */
    @ApiModelProperty(value="更新人")
    private Integer updateBy;
    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间")
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @ApiModelProperty(value="更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "项目id")
    private int proId;
    }
