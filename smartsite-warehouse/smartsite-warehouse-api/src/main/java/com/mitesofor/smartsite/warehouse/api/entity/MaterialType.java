package com.mitesofor.smartsite.warehouse.api.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.beans.Transient;
import java.util.List;

/**
 * 物料类型
 *
 * @author jchen
 * @date 2023-07-12 09:19:47
 */
@Data
@TableName("b_material_type")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "物料类型")
public class MaterialType extends Model<MaterialType> {
private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @TableId
    @ApiModelProperty(value="")
    private Integer id;
    /**
     * 父级id
     */
    @ApiModelProperty(value="父级id")
    private Integer parentId;
    /**
     * 类型名称
     */
    @ApiModelProperty(value="类型名称")
    private String name;

    @ApiModelProperty(value = "子级类型",hidden = true)
    @TableField(exist = false)
    private List<MaterialType> childList;
}
