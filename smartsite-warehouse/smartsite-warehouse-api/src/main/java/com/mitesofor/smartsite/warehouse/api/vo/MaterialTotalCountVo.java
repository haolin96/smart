package com.mitesofor.smartsite.warehouse.api.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description
 * @Author jchen
 * @Date 2023/7/12 16:17
 */
@Data
public class MaterialTotalCountVo {
    private BigDecimal todayNum;

    private BigDecimal totalNum;
}
