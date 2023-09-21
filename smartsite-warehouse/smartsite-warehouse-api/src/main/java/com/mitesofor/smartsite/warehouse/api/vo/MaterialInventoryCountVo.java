package com.mitesofor.smartsite.warehouse.api.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description
 * @Author jchen
 * @Date 2023/7/13 16:01
 */
@Data
public class MaterialInventoryCountVo {
    private String materialName;

    private BigDecimal inventoryNum;
}
