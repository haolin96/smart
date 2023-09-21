package com.mitesofor.smartsite.car.api.vo;

import com.mitesofor.smartsite.car.api.entity.CarMaterial;
import lombok.Data;

@Data
public class CarMaterialVo extends CarMaterial {
    private Double minWarningInventoryNum;
    private Double maxWarningInventoryNum;
}
