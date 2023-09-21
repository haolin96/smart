package com.mitesofor.smartsite.warehouse.api.dto;

import lombok.Data;

/**
 * @Description
 * @Author jchen
 * @Date 2023/7/12 9:31
 */
@Data
public class MaterialTypeQueryDto {
    private Integer id;
    private Integer parentId;
    private String name;

}
