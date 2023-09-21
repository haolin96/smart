package com.mitesofor.smartsite.warehouse.api.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description
 * @Author jchen
 * @Date 2023/7/13 10:38
 */
@Data
public class MaterialNumInfoVo {
    private String materialName;

    private BigDecimal totalRealNum;


    private BigDecimal totalFormNum;
}
