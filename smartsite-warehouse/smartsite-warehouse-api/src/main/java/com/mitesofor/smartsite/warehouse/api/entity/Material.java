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
import java.time.LocalDateTime;
import java.util.List;

/**
 * 标准化设定（特种设备）
 */
@Data
@ApiModel(value = "标准化设置")
@TableName("b_material_info")
@EqualsAndHashCode(callSuper = true)
public class Material extends Model<Material> {
    @TableId
    @ApiModelProperty(value = "主键id")
    private Integer id;

    @ApiModelProperty(value = "物料编号")
    private String materialSn;

    @ApiModelProperty(value = "物料类型（水泥、钢筋、沙子）")
    private Integer type;

    @ApiModelProperty(value = "物料类型",hidden = true)
    @TableField(exist = false)
    private String typeName;

    @ApiModelProperty(value = "型号")
    private Integer model;

    @ApiModelProperty(value = "型号",hidden = true)
    @TableField(exist = false)
    private String modelName;

    @ApiModelProperty(value = "损耗比例")
    private BigDecimal lossRatio;

    @ApiModelProperty(value = "图片")
    private String picture;

    @ApiModelProperty(value = "剩余库存")
    private BigDecimal inventoryNum;

    @ApiModelProperty(value = "最小预警库存")
    private BigDecimal minWarningInventoryNum;

    @ApiModelProperty(value = "最大预警库存")
    private BigDecimal maxWarningInventoryNum;

    @ApiModelProperty(value = "计量单位")
    private String unit;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "哈希码")
    private String hashCode;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "修改人")
    private String updateBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "项目id")
    private Integer proId;

    @ApiModelProperty(value = "入库总数量",hidden = true)
    @TableField(exist = false)
    private BigDecimal materialInNum;
}
