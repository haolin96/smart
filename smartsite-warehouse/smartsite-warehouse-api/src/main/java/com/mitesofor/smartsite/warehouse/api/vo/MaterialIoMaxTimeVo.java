package com.mitesofor.smartsite.warehouse.api.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Description
 * @Author jchen
 * @Date 2023/7/24 16:47
 */
@Data
public class MaterialIoMaxTimeVo {
    private Integer materialId;
    private LocalDateTime ioTime;
}
