package com.mitesofor.smartsite.warehouse.api.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @Description
 * @Author jchen
 * @Date 2023/7/13 10:12
 */
@Data
public class MaterialTotalCountTypeVo {

    @ApiModelProperty(value = "0今日 1累计")
    private String dataMark;

    private List<MaterialNumInfoVo> materialNumInfoList;
}
